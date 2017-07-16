package likaihu.bwie.com.bwiemall.utils;

import android.app.Activity;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast = null;

    public static void show(Activity activity, String content) {

        if (toast == null) {
            toast = Toast.makeText(activity, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
