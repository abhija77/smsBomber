package com.mobile.smsbomber.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.smsbomber.Model.ParamSmsAdapter;
import com.mobile.smsbomber.Model.ParamSmsItem;
import com.mobile.smsbomber.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.RequiresApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public class ParametersActivity extends Activity {

    ListView listView;
    Map<String,?> smsMap;
    List<ParamSmsItem> smsList = new ArrayList<>();
    ParamSmsAdapter dataAdapter = null;
    private static final int DIALOG_ALERT = 10;
    Button btn;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameter_sms_activity);

        SharedPreferences data =  getSharedPreferences("params", MODE_PRIVATE);
        SharedPreferences.Editor editor= data.edit();
        editor.putString("salutt", "salut, cava ?");
        editor.putString("tu vas bien ?", "Comme sur des roulettes et toi ?");
        editor.apply();

        this.smsMap = data.getAll();

        this.smsMap.forEach((cle,valeur) -> {
            ParamSmsItem paramSmsItem = new ParamSmsItem();
            paramSmsItem.setSmsReceived(cle);
            paramSmsItem.setSmsToReturn((String)valeur);
            this.smsList.add(paramSmsItem);
        });

        this.dataAdapter = new ParamSmsAdapter(this,R.layout.parameter_sms_item,this.smsList);

        listView =  findViewById(R.id.list_params);
        listView.setAdapter(dataAdapter);


    }


    public void onClick(View view) {
        showDialog(DIALOG_ALERT);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ALERT:
                // Create out AlterDialog
                Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Ajouter un message automatique");
                builder.setCancelable(true);
                builder.setView(R.layout.dialog_add_sms);

                builder.setPositiveButton("Ajouter ce message",new OkOnClickListener());
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onCreateDialog(id);
    }

    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), "Activity will continue",
                    Toast.LENGTH_LONG).show();
        }
    }

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            String sms_rec = "";
            String sms_reply = "";

            Dialog view = Dialog.class.cast(dialog);



            String a = ((EditText)view.findViewById(R.id.sms_rec)).getText().toString();
            String b = ((EditText)view.findViewById(R.id.sms_reply)).getText().toString();

            SharedPreferences.Editor editor = getSharedPreferences("params",MODE_PRIVATE).edit();
            editor.putString(a,b);
            editor.apply();
            ParametersActivity.this.finish();
        }
    }
}
