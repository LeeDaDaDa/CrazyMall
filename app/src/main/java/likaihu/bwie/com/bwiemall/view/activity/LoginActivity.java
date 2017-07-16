package likaihu.bwie.com.bwiemall.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import likaihu.bwie.com.bwiemall.R;
import likaihu.bwie.com.bwiemall.model.bean.LoginBean;
import likaihu.bwie.com.bwiemall.presenter.LoginPresenter;
import likaihu.bwie.com.bwiemall.utils.ToastUtils;
import likaihu.bwie.com.bwiemall.view.IView.ILoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ILoginView<LoginBean> {

    private ImageView back;
    private TextView register;
    private TextView login;
    private HashMap<String, String> hashMap = new HashMap<>();
    private LoginPresenter loginPresenter;
    private EditText userNum;
    private EditText userPwd;
    private String url="http://169.254.222.198/mobile/index.php";
    private String userNumber;
    private String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
    }

    private void initData() {
        loginPresenter = new LoginPresenter();
        loginPresenter.attach(this);

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.iv_back);
        register = (TextView) findViewById(R.id.register_login);
        login = (TextView) findViewById(R.id.login);
        userNum = (EditText) findViewById(R.id.userNum_login);
        userPwd = (EditText) findViewById(R.id.userPwd_login);
        register.setOnClickListener(this);
        back.setOnClickListener(this);
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_back:
                finish();
                overridePendingTransition(R.anim.activity_left_in, R.anim.activity_right_out);
                break;
            case R.id.register_login:

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityWithAnim(intent);

            case R.id.login:

                userNumber = userNum.getText().toString().trim();
                userPassword = userPwd.getText().toString().trim();

                hashMap.put("act", "login");
                hashMap.put("username", userNumber);
                hashMap.put("password", userPassword);
                hashMap.put("client","android");

                loginPresenter.loadDataFromServer(url,LoginBean.class,hashMap);

                break;
        }

    }

    /**
     * 跳转Activity时右进左出的动画
     * @param paramIntent
     */
    public void startActivityWithAnim(Intent paramIntent) {

        startActivity(paramIntent);
        overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
    }

    @Override
    public void requestSuccess(LoginBean loginBean) {


        Log.e("=====", "requestSuccess: ");

        ToastUtils.show(this, "登陆成功！");
        finish();

    }

    @Override
    public void requestFailure(int errCode, String errMsg) {

    }
}
