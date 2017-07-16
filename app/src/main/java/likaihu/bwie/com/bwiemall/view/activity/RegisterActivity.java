package likaihu.bwie.com.bwiemall.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import likaihu.bwie.com.bwiemall.R;
import likaihu.bwie.com.bwiemall.model.bean.RegisterBean;
import likaihu.bwie.com.bwiemall.presenter.RegisterPresenter;
import likaihu.bwie.com.bwiemall.utils.ToastUtils;
import likaihu.bwie.com.bwiemall.view.IView.IRegisterView;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterView<RegisterBean> {

    private ImageView back;
    private EditText userNum;
    private EditText userPwd;
    private EditText commitPwd;
    private EditText email;
    private TextView register;
    private String url = "http://169.254.222.198/mobile/index.php";
    private HashMap<String, String> hashMap = new HashMap<>();;
    private String userNumber;
    private String userPassword;
    private String commitPassword;
    private String userEmail;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        initData();
    }

    private void initData() {
        registerPresenter = new RegisterPresenter();
        registerPresenter.attach(this);

//        hashMap.put("act", "login");
//        hashMap.put("op", "register");
//        hashMap.put("username", "nihao");
//        hashMap.put("password", "123456");
//        hashMap.put("password_confirm", "123456");
//        hashMap.put("email", "753159654@qq.com");
//        hashMap.put("client", "android");


    }

    private void initView() {
        back = (ImageView) findViewById(R.id.iv_back_register);
        userNum = (EditText) findViewById(R.id.userNum_register);
        userPwd = (EditText) findViewById(R.id.userPwd_register);
        commitPwd = (EditText) findViewById(R.id.commitPwd_register);
        email = (EditText) findViewById(R.id.email_register);
        register = (TextView) findViewById(R.id.register);
        back.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_register:
                finish();
                overridePendingTransition(R.anim.activity_left_in, R.anim.activity_right_out);
                break;

            case R.id.register:

                userNumber = userNum.getText().toString().trim();
                userPassword = userPwd.getText().toString().trim();
                commitPassword = commitPwd.getText().toString().trim();
                userEmail = email.getText().toString().trim();

                hashMap.put("act", "login");
                hashMap.put("op", "register");
                hashMap.put("username", userNumber);
                hashMap.put("password", userPassword);
                hashMap.put("password_confirm", commitPassword);
                hashMap.put("email", userEmail);
                hashMap.put("client", "android");

                Log.e("=====", "initData: " + hashMap.size());
                if (!TextUtils.isEmpty(userNumber)
                        && !TextUtils.isEmpty(userPassword)
                        && !TextUtils.isEmpty(commitPassword)
                        && !TextUtils.isEmpty(userEmail)) {

                } else {
                    ToastUtils.show(this, "用户名、密码、邮箱都不能为空！请重新输入");
                    register.setEnabled(false);
                }

                registerPresenter.loadDataFromServer(url, RegisterBean.class, hashMap);
                break;

        }
    }

    @Override
    public void requestSuccess(RegisterBean registerBean) {

        Log.e("=====", "requestSuccess: ");

        ToastUtils.show(this, "注册成功！");
        finish();
    }

    @Override
    public void requestFailure(int errCode, String errMsg) {

    }
}
