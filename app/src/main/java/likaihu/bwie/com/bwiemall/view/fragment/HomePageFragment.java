package likaihu.bwie.com.bwiemall.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import likaihu.bwie.com.bwiemall.R;


public class HomePageFragment extends Fragment implements View.OnClickListener {

    private ImageView scanner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homepage_fragment_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        scanner = (ImageView) getView().findViewById(R.id.scanner_code);
        scanner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
