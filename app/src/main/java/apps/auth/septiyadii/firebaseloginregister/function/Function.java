package apps.auth.septiyadii.firebaseloginregister.function;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import apps.auth.septiyadii.firebaseloginregister.R;

public class Function extends AppCompatActivity {

    Context contextfunction;
    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextfunction = Function.this;
    }

    public void intentService(Class kelastujuan) {
        startActivity(new Intent(contextfunction, kelastujuan));
        finish();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showProgressDialog(String tittle) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle(tittle);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void toastService(String isipesan){
        Toast.makeText(contextfunction, isipesan, Toast.LENGTH_SHORT).show();
    }
}
