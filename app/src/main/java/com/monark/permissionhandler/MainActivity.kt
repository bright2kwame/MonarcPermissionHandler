package com.monark.permissionhandler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.monarc.permissionhandler.PermissionCallBack
import com.monarc.permissionhandler.PermissionHandler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val permission = ArrayList<String>()
        permission.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        PermissionHandler(this).askPermission(permission, object : PermissionCallBack {
            override fun onPermissionCancelled() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPermissionDenied() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPermissionGranted() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPermissionsGranted() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPermissionsDenied(permissions: ArrayList<String>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun permissionRequestError(error: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}
