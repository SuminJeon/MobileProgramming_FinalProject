package com.sopt25.sunnni.mobileprogramming_finalproject.feature.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sopt25.sunnni.mobileprogramming_finalproject.R;

public class SigninActivity extends AppCompatActivity {

    private EditText mEdtID, mEdtPW;
    private Button mBtnLogin;
    private LinearLayout mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mEdtID = (EditText) findViewById(R.id.edt_id);
        mEdtPW = (EditText) findViewById(R.id.edt_password);
        mBtnLogin = (Button) findViewById(R.id.btn_signin);
        mBtnRegister = (LinearLayout) findViewById(R.id.btn_signup);


    }
}
