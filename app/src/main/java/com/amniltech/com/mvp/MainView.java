package com.amniltech.com.mvp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amniltech.com.MainActivity;
import com.amniltech.com.R;
import com.jakewharton.rxbinding2.view.RxView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


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

    private static final String TAG = "MainView";

    public MainView(Activity activity) {
        super(activity);
        this.activity = activity;
        inflate(getContext(), R.layout.activity_main, this);
        ButterKnife.bind(this);

        ((MainActivity) getContext()).setSupportActionBar(toolbar);

    }


    public Observable<Object> observeShowButton() {
        return RxView.clicks(btnShow);
    }

    public Observable<Object> observeFab() {
        return RxView.clicks(fabBtn);
    }


    public void setHeightError(String errorMsg) {
        etHeight.requestFocus();
        etHeight.setError(errorMsg);
    }

    public void setWeightError(String errorMsg) {
        etWeight.requestFocus();
        etWeight.setError(errorMsg);
    }

    public void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    public void showSnackbar(String msg) {
        Snackbar.make(btnShow, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        AlertDialog alertDialog = builder.create();
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", (dialogInterface, i) -> alertDialog.dismiss());

        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    public void calCulateHealthStatus() {
        if (TextUtils.isEmpty(etWeight.getText().toString().trim())) {
            setWeightError("Weight Required!!!");
        } else if (TextUtils.isEmpty(etHeight.getText().toString().trim())) {
            setHeightError("Height Required!!!");
        } else {
            Double heightInFeet = Double.valueOf(etHeight.getText().toString().trim());
            Double weight = Double.valueOf(etWeight.getText().toString().trim());
            Double heightInMeter = heightInFeet * 0.3048;

            Double BMI = weight / Math.pow(heightInMeter, 2);

            checkBMIAndDisplayResult(BMI);
        }

    }

    private void checkBMIAndDisplayResult(Double bmi) {
        StringBuilder msgBuilder = new StringBuilder("Hello ");
        StringBuilder titleBuilder = new StringBuilder();

        if (TextUtils.isEmpty(etName.getText().toString().trim()))
            msgBuilder.append("User");
        else
            msgBuilder.append(etName.getText().toString().trim());

        msgBuilder.append(", According to your height and weight ")
                .append("you are ");
        if (bmi < 16) {
            msgBuilder.append("too thin :(");
            titleBuilder.append("Too Thin !!!");
        } else if (bmi < 17) {
            msgBuilder.append("moderate thin.");
            titleBuilder.append("Moderate Thin");
        } else if (bmi < 18.5) {
            msgBuilder.append("mild thin.");
            titleBuilder.append("Mild Thin");
        } else if (bmi < 25) {
            msgBuilder.append("fit and fine :)");
            titleBuilder.append("Fit and Fine");
        } else if (bmi < 30) {
            msgBuilder.append("Weighted");
            titleBuilder.append("Weighted");
        } else {
            msgBuilder.append("Over wighted :(");
            titleBuilder.append("Over Weighted!!!");
        }

        msgBuilder.append("\n\nBMI (Body Mass Index) Detail:\n");
        msgBuilder.append("Your BMI : ").append(bmi.toString());
        msgBuilder.append("\nFit BMI: ").append("Between 18.5 and 25");
        showAlertDialog(titleBuilder.toString(),msgBuilder.toString());
    }

}
