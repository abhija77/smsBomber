package com.mobile.smsbomber.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.smsbomber.R;

import java.util.List;

import androidx.annotation.NonNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mobile.smsbomber.R;

import java.util.List;

public class ParamSmsAdapter extends ArrayAdapter {
    private List smsParamsList;
    private Context context;

    public ParamSmsAdapter(@NonNull Context context, int resource,List objects) {
        super(context, resource,objects);
        this.smsParamsList = objects;
        this.context = context;
    }

    private class ViewHolder {
        TextView smsReceived;
        TextView smsToReply;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ParamSmsAdapter.ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.parameter_sms_item, null);

            holder = new ViewHolder();
            holder.smsReceived = (TextView) convertView.findViewById(R.id.sms_received);
            holder.smsToReply = (TextView) convertView.findViewById(R.id.sms_to_reply);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ParamSmsItem paramSmsItem = (ParamSmsItem) smsParamsList.get(position);
        holder.smsReceived.setText("SMS Recu : "+paramSmsItem.getSmsReceived());
        holder.smsToReply.setText("SMS Automatique : "+paramSmsItem.getSmsToReturn());

        return convertView;
    }
}
