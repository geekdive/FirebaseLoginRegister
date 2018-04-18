package apps.auth.septiyadii.firebaseloginregister.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import apps.auth.septiyadii.firebaseloginregister.R;
import apps.auth.septiyadii.firebaseloginregister.function.Function;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpEmailActivity extends Function {

    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.pgLoading)
    ProgressBar pgLoading;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupemail);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    toastService("Congratulation! cek email anda sekarang.");
                    verifikasiEmail();
                }
            }
        };
    }

    @OnClick({R.id.btnSignUp, R.id.btnSignIn})
    public void onViewClicked(View view) {
        final String userName = edtUsername.getText().toString();
        String passKey = edtPassword.getText().toString();
        switch (view.getId()) {
            case R.id.btnSignUp:
                if (TextUtils.isEmpty(userName)) {
                    edtUsername.setError("Email is empty!");
                    edtUsername.requestFocus();
                } else if (TextUtils.isEmpty(passKey)) {
                    edtPassword.setError("Fill the passkey!");
                    edtPassword.requestFocus();
                } else {
                    pgLoading.setVisibility(View.VISIBLE);
                    auth.createUserWithEmailAndPassword(userName, passKey).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pgLoading.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                toastService("Selamat! " + userName + "\n anda berhasil terdaftar.");
                            } else {
                                toastService("Maaf, gagal register! coba cek " + task.getException());
                            }
                        }
                    });
                }
                break;
            case R.id.btnSignIn:
                intentService(LoginEmailActivity.class);
                break;
        }
    }

    private void verifikasiEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FirebaseAuth.getInstance().signOut();
                    intentService(LoginEmailActivity.class);
                    finish();
                } else {
                    overridePendingTransition(0, 0);
                    finish();
                    overridePendingTransition(0, 0);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener!=null){
            auth.removeAuthStateListener(listener);
        }
    }
}
