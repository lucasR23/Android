package longynuss.com.whatsappclone.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * FireBase Config
 */

public class FirebaseConfig {
    private static DatabaseReference firebaseDatabase;
    public static DatabaseReference getFirebaseDatabaseReference(){
        if(firebaseDatabase==null){
            firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        }
        return firebaseDatabase;
    }

    private static FirebaseAuth auth;
    public static FirebaseAuth getFirebaseAuth(){
        if(auth==null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
