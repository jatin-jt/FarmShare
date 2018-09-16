package com.noobsquad.farmshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.auth.core.SignInStateChangeListener;
import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;

public class AuthActivity extends AppCompatActivity {

    private static final String TAG = "AuthActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        AWSMobileClient.getInstance().initialize(this).execute();

        IdentityManager.getDefaultIdentityManager().addSignInStateChangeListener(new SignInStateChangeListener() {
            @Override
            public void onUserSignedIn() {
                Log.d(TAG, "User Signed In");
            }

            // Sign-out listener
            @Override
            public void onUserSignedOut() {
                Log.d(TAG, "User Signed Out");
                showSignIn();
            }
        });

        showSignIn();
    }
    private void showSignIn() {

        Log.d(TAG, "showSignIn");

        SignInUI signin = (SignInUI) AWSMobileClient.getInstance().getClient(AuthActivity.this, SignInUI.class);
        signin.login(AuthActivity.this, StartActivity.class).execute();
    }
}
