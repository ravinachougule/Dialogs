package com.example.dialogsjava;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import android.view.View;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppDialog extends DialogFragment {

    public static final String TAG_ALERT="alert";
    public static final String TAG_DATE="date";
    public static final String TAG_TIME="time";
    public static final String TAG_PROGRESS="progress";
    public static final String TAG_CUSTOM="custom";
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog=new Dialog(getActivity());
        if(getTag().equals(AppDialog.TAG_ALERT))
            dialog=alert();
        else if (getTag().equals(AppDialog.TAG_DATE))
            dialog=datePicker();
        else if (getTag().equals(AppDialog.TAG_TIME))
            dialog=timePicker();
        else if (getTag().equals(AppDialog.TAG_PROGRESS))
            dialog=progressDialog();
        else if (getTag().equals(AppDialog.TAG_CUSTOM))
            dialog=custom();
        return dialog;
    }
   private AlertDialog alert() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.title))
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(getResources().getString(R.string.msg))
                .setPositiveButton(R.string.btnYes,((dialogInterface, i) ->mt("YES")))
                .setNegativeButton(R.string.btnNo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mt("NO");
                    }
                });
        return builder.create();
    }
    private DatePickerDialog datePicker(){
        return new DatePickerDialog(getActivity(),(view, year, month, dayOfMonth)->mt(""+dayOfMonth+" - "+(month+1)+" - "+year),2018,3,15);
    }
    private TimePickerDialog timePicker(){
        return new TimePickerDialog(getActivity(),(view,hourOfDay,minute)->mt(""+hourOfDay+" : "+minute),6,03,false);
    }
    private ProgressDialog progressDialog(){
        ProgressDialog dialog=new ProgressDialog(getActivity());
        dialog.setTitle(getString(R.string.title));
        dialog.setMessage(getString(R.string.msg));
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dialog;
    }

    private Dialog custom(){
        final View view=getActivity().getLayoutInflater().inflate(R.layout.custom_dialog,null,false);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
    void mt(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }
}
