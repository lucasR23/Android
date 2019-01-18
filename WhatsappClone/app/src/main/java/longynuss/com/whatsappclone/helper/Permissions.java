package longynuss.com.whatsappclone.helper;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Set up all the permissions that is needed for the application
 */

public class Permissions {

    public static boolean validatePermissions(Activity activity,String[] permissions, int resquestCode){
        ArrayList<String> permissionsToValidate = new ArrayList<>();

        if(Build.VERSION.SDK_INT>=23){
            for (String permission: permissions) {
                boolean validated = ContextCompat.checkSelfPermission(activity,permission)==PackageManager.PERMISSION_GRANTED;
                if(!validated){
                    permissionsToValidate.add(permission);
                }
            }

            if (permissionsToValidate.isEmpty()){
                return true;
            }

            String[] newPermissions = new String[permissionsToValidate.size()];
            permissionsToValidate.toArray(newPermissions);

            ActivityCompat.requestPermissions(activity,newPermissions,resquestCode);
        }

        return true;
    }
}