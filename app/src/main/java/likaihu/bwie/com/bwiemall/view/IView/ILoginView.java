package likaihu.bwie.com.bwiemall.view.IView;

public interface ILoginView<T> {

    void requestSuccess(T t);

    void requestFailure(int errCode, String errMsg);
}
