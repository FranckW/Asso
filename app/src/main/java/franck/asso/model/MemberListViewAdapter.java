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
 * Created by franc on 20/10/2015.
 */
public class MemberListViewAdapter extends ArrayAdapter<Member> {
    protected Context context;
    protected LayoutInflater inflater;
    protected List<Member> members;
    protected SparseBooleanArray selectedItemsIds;

    public MemberListViewAdapter(Context context, int resourceId, List<Member> members) {
        super(context, resourceId, members);
        selectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.members = members;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView firstName;
        TextView lastName;
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_member, null);
            holder.firstName = (TextView) view.findViewById(R.id.firstName);
            holder.lastName = (TextView) view.findViewById(R.id.lastName);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.firstName.setText(members.get(position).getFirstName());
        holder.lastName.setText(members.get(position).getLastName());
        return view;
    }

    @Override
    public void remove(Member member) {
        members.remove(member);
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !selectedItemsIds.get(position));
    }

    public void removeSelection() {
        selectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            selectedItemsIds.put(position, value);
        else
            selectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedIds() {
        return selectedItemsIds;
    }
}
