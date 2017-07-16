package likaihu.bwie.com.bwiemall.presenter;

public class BasePresenter<V> {

    private V mV;

    public void attach(V v) {
        this.mV = v;
    }

    public V getMvpView() {
        return mV;
    }

    //回收无用的view
    public void deAttach() {
        mV = null;
    }
}
