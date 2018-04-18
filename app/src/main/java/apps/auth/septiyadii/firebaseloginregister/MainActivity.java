package apps.auth.septiyadii.firebaseloginregister;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import apps.auth.septiyadii.firebaseloginregister.activity.LoginEmailActivity;
import apps.auth.septiyadii.firebaseloginregister.activity.SignInGoogleActivity;
import apps.auth.septiyadii.firebaseloginregister.activity.SignInPhoneActivity;
import apps.auth.septiyadii.firebaseloginregister.activity.SignUpEmailActivity;
import apps.auth.septiyadii.firebaseloginregister.function.Function;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Function {

    @BindView(R.id.btnGoogle)
    Button btnGoogle;
    @BindView(R.id.btnEmail)
    Button btnEmail;
    @BindView(R.id.btnPhone)
    Button btnPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btnGoogle, R.id.btnEmail, R.id.btnPhone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGoogle:
                intentService(SignInGoogleActivity.class);
                break;
            case R.id.btnEmail:
                intentService(LoginEmailActivity.class);
                break;
            case R.id.btnPhone:
                intentService(SignInPhoneActivity.class);
                break;
        }
    }
}
