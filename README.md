# MonarcPermissionHandler
Android Runtime Activity Permission Handler

Its easy to use
Follow this tep to call the library


#Import the following first
import com.monarc.permissionhandler.PermissionCallBack
import com.monarc.permissionhandler.PermissionHandler

#The use as follow
val permission = ArrayList<String>()
        permission.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        PermissionHandler(this).askPermission(permission, object : PermissionCallBack {
            override fun onPermissionCancelledError() {
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
