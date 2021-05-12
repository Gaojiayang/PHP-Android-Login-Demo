package com.example.gaoming.byphplogin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final String cg = "登录成功";
    private final String sb = "登录失败";
    private EditText photo, pwd;
    private Button btn;
    private String photostring,pwdstring;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);

        photo = (EditText) findViewById(R.id.photo);
        pwd = (EditText) findViewById(R.id.pwd);
        btn = (Button) findViewById(R.id.btn_log);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                photostring = photo.getText().toString().trim();
                pwdstring = pwd.getText().toString().trim();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = GetUtils.LoginByGet(photostring, pwdstring);
                    }
                }).start();

                while (result == null)
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(Objects.equals(result, "1"))
                {
                    Toast.makeText(MainActivity.this, cg, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, MainView.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this, sb, Toast.LENGTH_LONG).show();
                }

                result = null;

            }
        });
    }
}
