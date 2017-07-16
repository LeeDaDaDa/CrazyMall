package likaihu.bwie.com.bwiemall.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import likaihu.bwie.com.bwiemall.R;
import likaihu.bwie.com.bwiemall.view.activity.LoginActivity;


public class UserFragment extends Fragment {

    private TextView login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.user_fragment_layout,container,false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        initView();

    }

    private void initView() {
        login = (TextView) getView().findViewById(R.id.tv_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityWithAnim(intent);
            }
        });
    }

    /**
     * 跳转Activity时右进左出的动画
     * @param paramIntent
     */
    public void startActivityWithAnim(Intent paramIntent) {

        startActivity(paramIntent);
        getActivity().overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
    }
}
