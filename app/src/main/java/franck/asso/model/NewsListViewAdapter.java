package franck.asso.model;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import franck.asso.R;

/**
 * Created by franc on 04/12/2015.
 */
public class NewsListViewAdapter extends ArrayAdapter<News> {
    protected Context context;
    protected LayoutInflater inflater;
    protected List<News> newsList;
    protected SparseBooleanArray selectedItemsIds;

    public NewsListViewAdapter(Context context, int resourceId, List<News> newsList) {
        super(context, resourceId, newsList);
        selectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.newsList = newsList;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView title;
        TextView content;
        TextView date;
        TextView author;
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.memberinlist, null);
            holder.title = (TextView) view.findViewById(R.id.newsTitle);
            holder.content = (TextView) view.findViewById(R.id.newsContent);
            holder.date = (TextView) view.findViewById(R.id.newsDate);
            holder.author = (TextView) view.findViewById(R.id.newsAuthor);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(newsList.get(position).getTitle());
        holder.content.setText(newsList.get(position).getContent());
        holder.date.setText(newsList.get(position).getDate());
        holder.author.setText("by " + newsList.get(position).getAuthor().getFirstName() + " " + newsList.get(position).getAuthor().getLastName());
        return view;
    }

    @Override
    public void remove(News news) {
        newsList.remove(news);
        notifyDataSetChanged();
    }
}