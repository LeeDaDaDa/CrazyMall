package likaihu.bwie.com.bwiemall.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import likaihu.bwie.com.bwiemall.R;
import likaihu.bwie.com.bwiemall.view.fragment.CataGoryFragment;
import likaihu.bwie.com.bwiemall.view.fragment.HomePageFragment;
import likaihu.bwie.com.bwiemall.view.fragment.ShoppingCartFragment;
import likaihu.bwie.com.bwiemall.view.fragment.UserFragment;
/**
 * @类用途:
 * @author: likaihu
 * @date: 2017/7/10  11:23
 */

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout container;
    private RadioGroup rg;
    private HomePageFragment homePage;
    private CataGoryFragment cataGory;
    private ShoppingCartFragment shoppingCart;
    private UserFragment user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //初始化视图
        initView();
        //默认显示首页
        addFragment(new HomePageFragment());
    }

    private void initView() {
        container = (FrameLayout) findViewById(R.id.container_id);
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
    }

    //动态添加Fragment的方法
    public void addFragment(Fragment ft) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (!ft.isAdded()) {
            transaction.replace(R.id.container_id, ft);
        }
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (checkedId) {

            case R.id.rb_homepage:
                if (homePage == null) {
                    homePage = new HomePageFragment();
                }
                addFragment(homePage);
                break;

            case R.id.rb_catagory:
                if (cataGory == null) {
                    cataGory = new CataGoryFragment();
                }
                addFragment(cataGory);
                break;
            case R.id.rb_shoppingcart:
                if (shoppingCart == null) {
                    shoppingCart = new ShoppingCartFragment();
                }
                addFragment(shoppingCart);
                break;
            case R.id.rb_user:
                if (user == null) {
                    user = new UserFragment();
                }
                addFragment(user);
                break;
        }
    }
}
