package longynuss.com.whatsappclone.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import longynuss.com.whatsappclone.R;
import longynuss.com.whatsappclone.adapter.TabAdapter;
import longynuss.com.whatsappclone.config.FirebaseConfig;
import longynuss.com.whatsappclone.helper.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    private static final int LOGIN_REQUEST = 1000;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser fuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //config toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("WhatsApp");
        setSupportActionBar(toolbar);

        //config TAB
        SlidingTabLayout slidingTabLayout= findViewById(R.id.stl_tabs);
        ViewPager viewPager = findViewById(R.id.viewPager);

        //Config sliding tabs
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));

        //Config adapter
        String[] titles = {getString(R.string.conversationTitle),getString(R.string.contactsTitle)};
        TabAdapter tabAdapter = new TabAdapter( getSupportFragmentManager(), titles);
        viewPager.setAdapter(tabAdapter);
        slidingTabLayout.setViewPager(viewPager);


        firebaseAuth = FirebaseConfig.getFirebaseAuth();
        fuser = firebaseAuth.getCurrentUser();

        //user not logged in
        if(fuser == null){
            Intent intentLogin = new Intent(this,LoginActivity.class);
            startActivityForResult(intentLogin,LOGIN_REQUEST);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == LOGIN_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                //get user
                fuser = firebaseAuth.getCurrentUser();
                if(fuser!=null){
                    Log.i("LoginFlow","User logged"+"\nName: "+fuser.getDisplayName()+
                            "\nEmail: "+fuser.getEmail()+"\nPhone: "+fuser.getPhoneNumber()+"\n");
                }

            }else{
                //Notify user of the error
                Toast.makeText(MainActivity.this,
                        R.string.loginErrorNotify,Toast.LENGTH_SHORT).show();

                //try again
                Intent intentLogin = new Intent(this,LoginActivity.class);
                startActivityForResult(intentLogin,LOGIN_REQUEST);
            }
        }
    }

    //region permissions
//    private String[] permissionsNeeded = new String[]{
//            Manifest.permission.INTERNET,
//            Manifest.permission.SEND_SMS};
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        for (int result : grantResults){
//            if (result == PackageManager.PERMISSION_DENIED){
//                alertUserPermissionDenied();
//            }
//        }
//    }
//
//    private void alertUserPermissionDenied(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.PermissionDeniedTitle);
//        builder.setMessage(R.string.PermissionDeniedMessage);
//
//        builder.setPositiveButton(R.string.PermissionDeniedButton, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //end application
//                finish();
//            }
//        });
//    }
    //endregion
}

