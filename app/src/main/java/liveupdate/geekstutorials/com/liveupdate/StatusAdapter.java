package liveupdate.geekstutorials.com.liveupdate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by CoolerMaster on 9/10/2015.
 */
public class StatusAdapter extends ArrayAdapter<ParseObject> {
    protected Context mContext;
    protected List<ParseObject> mStatus;

    public StatusAdapter (Context context, List<ParseObject> status){
        super(context,R.layout.homepagecustomlayout,status);
        mContext = context;
        mStatus = status;
    }


    //inflate each row
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.homepagecustomlayout,null);
            holder = new ViewHolder();
            holder.usernameHomePage = (TextView) convertView.findViewById(R.id.usernameHP);
            holder.statustHomePage = (TextView) convertView.findViewById(R.id.statusHP);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ParseObject statusObject = mStatus.get(position);
        //set the username
        String username = statusObject.getString("username");
        holder.usernameHomePage.setText(username);
        //set the status
        String status = statusObject.getString("newStatus");
        holder.statustHomePage.setText(status);

        return convertView;
    }

    public static class ViewHolder {
        TextView usernameHomePage;
        TextView statustHomePage;
    }
}
