package com.monarc.permissionhandler

import java.util.*


/**
 * Created by Monarchy on 08/10/2017.
 */
interface PermissionCallBack {
    fun onPermissionCancelledError()

    fun onPermissionDenied()

    fun onPermissionGranted()

    fun onPermissionsGranted()

    fun onPermissionsDenied(permissions: ArrayList<String>)

    fun permissionRequestError(error: String)
}