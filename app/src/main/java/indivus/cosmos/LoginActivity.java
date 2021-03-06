package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import indivus.cosmos.presenter.LoginPresenter;
import indivus.cosmos.presenter.ResponseCallBack;

/**
 * Created by Administrator on 2017-06-27.
 */

public class LoginActivity extends Activity {

    EditText email_edit;
    EditText password_edit;

    Button login_btn;
    Button sign_up_btn;

    LoginPresenter login_presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_login);
        startActivity(new Intent(this, SplashActivity.class)); //activity_splash

        email_edit = (EditText)findViewById(R.id.login_email_edit);
        password_edit = (EditText)findViewById(R.id.login_password_edit);
        Log.i("aa", "aa");
        login_btn = (Button)findViewById(R.id.login_btn);
        sign_up_btn = (Button)findViewById(R.id.signup_btn);

        login_presenter = new LoginPresenter();


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_edit.getText().toString();
                String password = password_edit.getText().toString();

                login_presenter.tryLogin(email, password, new ResponseCallBack() {
                    @Override
                    public void onSuccess(int result) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class)); //main 화면
                        finish();
                    }

                    @Override
                    public void onError(int result) {
                        Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 잘못 되었습니다", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

    }

}
