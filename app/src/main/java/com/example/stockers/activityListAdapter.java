package com.example.stockers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by Ryan Kellerman on 4/23/17.
 */

public class activityListAdapter extends ArrayAdapter<String> {

    String[] items;
    Context c;
    LayoutInflater inflater;

    public activityListAdapter(Context context, String[] items) {
        super(context, R.layout.activity_row_model, items);

        this.c = context;
        this.items = items;

    }

    public class ViewHolder
    {
        TextView item;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_row_model, null);
        }
        Typeface supportfont = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-UltraLight.otf");
        // VIEWHOLDER OBJECT
        final ViewHolder holder = new ViewHolder();

        //INITIALIZE VIEWS
        holder.item = (TextView) convertView.findViewById(R.id.list_activity);
        holder.item.setTypeface(supportfont);

        //ASSIGN VIEWS
        holder.item.setText(items[position]);

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
