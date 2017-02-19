package com.example.jiajia.mytestdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et_acc,et_pwd;
    CheckBox chb_rempwd;
    Button btn_login;
    SharedPreferences  shef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        et_acc = (EditText)findViewById(R.id.et_acoount);
        et_pwd = (EditText)findViewById(R.id.et_pwd);
        chb_rempwd = (CheckBox)findViewById(R.id.chb_rempwd);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);

        isRemb();
    }

    /**
     * 判断之前登录是否有保存账号密码，若有，显示
     * */
  public void isRemb(){
      shef = getSharedPreferences("account",MODE_PRIVATE);
      if(shef.getBoolean("isRemb",false)){
          et_acc.setText(shef.getString("account",""));
          et_pwd.setText(shef.getString("pwd",""));
          chb_rempwd.setSelected(true);

      }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                SharedPreferences.Editor editor = shef.edit();
                if (chb_rempwd.isSelected()){

                    editor.putString("account",et_acc.getText().toString());
                    editor.putString("pwd",et_pwd.getText().toString());
                    editor.putBoolean("isRemb",true);
                }else{
                    editor.clear();
                }
                editor.apply();
                break;
            default:
                break;
        }
    }
}
