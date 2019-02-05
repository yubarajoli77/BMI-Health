package com.amniltech.com.mvp;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amniltech.com.MainActivity;
import com.amniltech.com.R;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class MainView extends ConstraintLayout {

    @BindView(R.id.tv_top_msg_info)
    TextView tvTopMsgInfo;

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_height)
    EditText etHeight;

    @BindView(R.id.et_weight)
    EditText etWeight;

    @BindView(R.id.btn_show)
    Button btnShow;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fabBtn;

    private Activity activity;

    public MainView(Activity activity) {
        super(activity);
        this.activity = activity;
        inflate(getContext(), R.layout.activity_main,this);
        ButterKnife.bind(this);

        ((MainActivity) getContext()).setSupportActionBar(toolbar);

    }



    public Observable<Object> observableShowButton(){return RxView.clicks(btnShow);}
    public Observable<Object> observableFab(){return RxView.clicks(fabBtn);}

//    @OnClick(R.id.btn_show)
//    public void doClick(){
//        Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
//    }


    public void setHeightError(String errorMsg){
        etHeight.requestFocus();
        etHeight.setError(errorMsg);
    }

    public void setWeightError(String errorMsg){
        etWeight.requestFocus();
        etWeight.setError(errorMsg);
    }

    public void showMessage(String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    public void showSnackMsg(String msg){
        Snackbar.make(btnShow, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }



}
