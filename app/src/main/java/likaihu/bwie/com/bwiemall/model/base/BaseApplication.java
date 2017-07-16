package likaihu.bwie.com.bwiemall.model.base;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
