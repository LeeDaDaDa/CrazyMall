package likaihu.bwie.com.bwiemall.presenter;

import java.util.HashMap;

import likaihu.bwie.com.bwiemall.model.net.HttpUtils;
import likaihu.bwie.com.bwiemall.view.IView.IRegisterView;

public class RegisterPresenter extends BasePresenter<IRegisterView> {


    private HttpUtils httpUtils;

    public <T> void loadDataFromServer(String url, Class<T> t,HashMap<String,String> hashMap) {

        httpUtils = new HttpUtils();
        httpUtils.getDataPostFromServer(url, new HttpUtils.NetDataCallBackIFace<T>() {
            @Override
            public void requestSuccess(T o) {
                //回调到p层
                getMvpView().requestSuccess(o);
            }

            @Override
            public void requestFailure(int errCode, String errMsg) {
                getMvpView().requestFailure(500,errMsg);
            }
        },hashMap,t);

    }
}
