package com.monarc.permissionhandler

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import java.util.*


class PermissionHandler(var activity: Activity) : AppCompatActivity() {
    //MARK: requesting permission code
    private var permissionRequestCode = 100
    //MARK: all permissions requested for for
    private var allPermissions = ArrayList<String>()
    //MARK: handle the permission callback
    private var permissionCallBack: PermissionCallBack? = null


    //MARK: check if permission is granted
    private fun isPermissionGranted(permission: String): Boolean {
        return ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED))
    }

    //MARK: check id multiple permissions are granted
    fun arePermissionsGranted(permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true
        var granted = true
        for (permission in permissions) {
            granted = isPermissionGranted(permission)
        }
        return granted
    }


    //MARK: asking permissions
    fun askPermission(permissions: ArrayList<String>, callBack: PermissionCallBack) {
        allPermissions = permissions
        permissionCallBack = callBack
        val permissionsNeeded = ArrayList<String>()
        val permissionsList = ArrayList<String>()

        //MARK: empty permission list
        if (permissions.isEmpty()){
           permissionCallBack?.permissionRequestError("You have to request at least one permission")
        }

        for (permission in permissions) {
            if (!addPermission(permissionsList, permission))
                permissionsNeeded.add(permission)
        }

        if (permissionsList.size > 0) {
            if (permissionsNeeded.size > 0) {
                ActivityCompat.requestPermissions(activity, permissionsList.toTypedArray(), permissionRequestCode)
                return
            }
            ActivityCompat.requestPermissions(activity, permissionsList.toTypedArray(), permissionRequestCode)
        }
    }

    //MARK: adding permission
    private fun addPermission(permissionsList: MutableList<String>, permission: String): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission)
                if (!activity.shouldShowRequestPermissionRationale(permission))
                    return false
            }
        }
        return true
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            permissionRequestCode -> {
                val permissionDenied = ArrayList<String>()
                var granted = true
                for (i in grantResults.indices) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false
                        permissionDenied.add(permissions[i])
                    }
                }
                if (permissionCallBack != null) {
                    if (granted) {
                        if (allPermissions.size == 1) {
                            permissionCallBack?.onPermissionGranted()
                        } else {
                            permissionCallBack?.onPermissionsGranted()
                        }
                    } else {
                        if (allPermissions.size == 1) {
                            permissionCallBack?.onPermissionDenied()
                        } else {
                            permissionCallBack?.onPermissionsDenied(permissionDenied)
                        }
                    }
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


}