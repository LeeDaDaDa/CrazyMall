package likaihu.bwie.com.bwiemall.presenter;

import java.util.HashMap;

import likaihu.bwie.com.bwiemall.model.net.HttpUtils;
import likaihu.bwie.com.bwiemall.view.IView.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> {

    private HttpUtils httpUtils;

    public <T> void loadDataFromServer(String url, Class<T> t,HashMap<String,String> hashMap) {
        httpUtils = new HttpUtils();

         httpUtils.getDataPostFromServer(url, new HttpUtils.NetDataCallBackIFace<T>() {
             @Override
             public void requestSuccess(T o) {
                 getMvpView().requestSuccess(o);
             }

             @Override
             public void requestFailure(int errCode, String errMsg) {

                 getMvpView().requestFailure(errCode,errMsg);
             }
         },hashMap,t);
    }

}
