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

import apps.auth.septiyadii.firebaseloginregister.MainActivity;
import apps.auth.septiyadii.firebaseloginregister.R;
import apps.auth.septiyadii.firebaseloginregister.data.DataFirebaseActivity;
import apps.auth.septiyadii.firebaseloginregister.function.Function;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginEmailActivity extends Function {

    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.pgLoading)
    ProgressBar pgLoading;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginemail);
        ButterKnife.bind(this);

        auth=FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user=firebaseAuth.getCurrentUser();
                if (user!=null){
                    if (user.isEmailVerified()){
                        toastService("Congratulation! anda berhasil login dengan email " + user.getEmail() + ".");
                        intentService(DataFirebaseActivity.class);
                        finish();
                    }else {
                        toastService("Maaf! anda gagal login, silahkan coba lagi, coba cek email apakah sudah di verifikasi?");
                        FirebaseAuth.getInstance().signOut();
                    }
                }
            }
        };
    }

    @OnClick({R.id.btnSignIn, R.id.btnSignUp})
    public void onViewClicked(View view) {
        final String userName = edtUsername.getText().toString();
        String passKey = edtPassword.getText().toString();
        switch (view.getId()) {
            case R.id.btnSignIn:
                if (TextUtils.isEmpty(userName)) {
                    edtUsername.setError("Put email is empty!");
                    edtUsername.requestFocus();
                } else if (TextUtils.isEmpty(passKey)) {
                    edtPassword.setError("Put password is empty!");
                    edtPassword.requestFocus();
                } else {
                    pgLoading.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(userName, passKey).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pgLoading.setVisibility(View.GONE);
                            if (!task.isSuccessful()){
                                toastService("Maaf! anda gagal login silahkan cek " + task.getException() + ".");
                                intentService(LoginEmailActivity.class);
                            } else {
                                intentService(DataFirebaseActivity.class);
                                finish();
                            }
                        }
                    });
                }
                break;
            case R.id.btnSignUp:
                intentService(SignUpEmailActivity.class);
                break;
        }
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
