package nakamd.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by danielnakamura on 2/17/15.
 */
public class TopicListAdapter extends ArrayAdapter<Topic> {
        Context context;
        int layoutResource;
        ArrayList<Topic> info = null;

        public TopicListAdapter(Context context, int layoutResource, ArrayList<Topic> info) {
            super(context, layoutResource, info);
            this.layoutResource = layoutResource;
            this.context = context;
            this.info = info;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            TopicListHolder holder = null;

            if(row == null)
            {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResource, parent, false);

                holder = new TopicListHolder();
                holder.topicImage = (ImageView)row.findViewById(R.id.image);
                holder.topicName = (TextView)row.findViewById(R.id.title);
                holder.topicDesc = (TextView)row.findViewById(R.id.description);

                row.setTag(holder);
            }
            else
            {
                holder = (TopicListHolder)row.getTag();
            }

            Topic topic = info.get(position);
            holder.topicName.setText(topic.getTitle());
            holder.topicDesc.setText(topic.getShortDescript());
            holder.topicImage.setImageResource(topic.getImage());

            return row;
        }


        static class TopicListHolder {
            ImageView topicImage;
            TextView topicName;
            TextView topicDesc;
        }
}
