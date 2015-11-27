package upc.edu.pe.desaplg.adapter;

import android.app.Activity;
import android.app.ListActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.connectsdk.device.ConnectableDevice;

import upc.edu.pe.desaplg.R;
import upc.edu.pe.desaplg.helpers.FontHelper;

public class TVAdapter extends BaseAdapter {

    ListAdapter adapter;
    Activity activity;

    public TVAdapter(ListAdapter adapter, Activity activity){

        this.adapter = adapter;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return adapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        return adapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return adapter.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_item_tv, null);

        ConnectableDevice device = (ConnectableDevice) adapter.getItem(position);
        TextView lblNombreTV = (TextView)v.findViewById(R.id.itemTV);
        FontHelper.setFont(activity.getApplicationContext(), FontHelper.DOSIS_BOLD, lblNombreTV);
        lblNombreTV.setText(device.getFriendlyName());
        return v;
    }
}