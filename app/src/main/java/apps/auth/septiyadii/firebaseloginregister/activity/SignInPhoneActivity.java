package apps.auth.septiyadii.firebaseloginregister.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import apps.auth.septiyadii.firebaseloginregister.R;
import apps.auth.septiyadii.firebaseloginregister.function.Function;

public class SignInPhoneActivity extends Function implements View.OnClickListener{

    //TODO 1: Deklarasi View ID pada Layout
    private ViewGroup viewGroupLine1, viewGroupLine2;
    private TextView tvStatus, tvDetail;
    private EditText edKodeNegara, edNoTelp, edKodeVerifikasi;
    private Button btSentCode, btVerifyCode, btSentCodeAgain, btSignOut;

    //TODO 2: Deklarasi Firebase Auth
    private FirebaseAuth auth;

    //TODO 3: Deklarasi Auth Phone
    private PhoneAuthProvider.ForceResendingToken resendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    //TODO 4: Deklarasi ID & verfikasi state
    private boolean veficationProgress = false;
    private String verificationID;

    //TODO 5: Deklarasi Variable telusur Log
    private static final String TAG = "PhoneAuthActivity";
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    //TODO 14: Deklarasikan Variable
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinphone);

        //TODO 6: Instansiasikan variable view
        viewGroupLine1 = (ViewGroup)findViewById(R.id.groupLine1);
        viewGroupLine2 = (ViewGroup)findViewById(R.id.groupLine2);

        tvStatus = (TextView)findViewById(R.id.txtStatus);
        tvDetail = (TextView)findViewById(R.id.txtDetail);

        edKodeNegara = (EditText)findViewById(R.id.edtKodeNegara);
        edNoTelp = (EditText)findViewById(R.id.edtNomorTelp);
        edKodeVerifikasi = (EditText)findViewById(R.id.kdVerifikasi);

        btSentCode = (Button)findViewById(R.id.btnKirimKode);
        btVerifyCode = (Button)findViewById(R.id.btnVerifikasiNomor);
        btSentCodeAgain = (Button)findViewById(R.id.btnSentCodeAgain);
        btSignOut = (Button)findViewById(R.id.btnSignOut);

        //TODO 7: Inisialisasikan Firebase Auth
        auth = FirebaseAuth.getInstance();

        //TODO 8: Assign Click on Button
        btSentCode.setOnClickListener(this);
        btVerifyCode.setOnClickListener(this);
        btSentCodeAgain.setOnClickListener(this);
        btSignOut.setOnClickListener(this);

        //TODO 9: Create Callback methods override
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                //TODO 11:
                Log.d(TAG, "onVerfificationCompleted" + phoneAuthCredential);
                veficationProgress = false;
                updateUI(STATE_VERIFY_SUCCESS, phoneAuthCredential);
                signInWithPhoneAuthCridential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            //TODO 10:
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
            }
        };
    }

    @Override
    public void onClick(View v) {

    }

    //TODO 12:
    private void updateUI(int uiState, PhoneAuthCredential authCredential) {
        updateUI(uiState, null, authCredential);
    }

    //TODO 13:
    private void updateUI(int uiState, FirebaseUser user, PhoneAuthCredential authCredential) {
        switch (uiState) {
            case STATE_INITIALIZED:
                break;
            case STATE_CODE_SENT:
                break;
            case STATE_VERIFY_FAILED:
                break;
            case STATE_VERIFY_SUCCESS:
                break;
            case STATE_SIGNIN_FAILED:
                break;
            case STATE_SIGNIN_SUCCESS:
                break;
        }
    }

    //TODO 15: Buat Method untuk Enable & Disable View ketika ada informasi dalam satu hal
    //misalkan ketika klik tombol sign out ada beberapa view yang di disable yaitu sebagai berikut

    private void enableViews(View... views) {
        for (View view : views) {
            view.setEnabled(true);
        }
    }

    private void disableViews(View... views) {
        for (View view : views) {
            view.setEnabled(false);
        }
    }
}
