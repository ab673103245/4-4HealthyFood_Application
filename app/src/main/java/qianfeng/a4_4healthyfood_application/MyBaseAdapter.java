package qianfeng.a4_4healthyfood_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<FoodBean> list;

    public MyBaseAdapter(Context context, List<FoodBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    public MyBaseAdapter(MyFragment myFragment, List<FoodBean> list) {
        context = myFragment.getActivity();
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
         convertView = inflater.inflate(R.layout.listview_item,parent,false);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.keywords = (TextView) convertView.findViewById(R.id.keywords);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        FoodBean foodBean = list.get(position);
        Picasso.with(context).load(foodBean.getImg()).into(holder.iv);

        holder.description.setText(foodBean.getDescription());
        holder.keywords.setText(foodBean.getKeywords());



        // 可i啊his处理 "http://tnfs.tngou.net/image"

        return convertView;
    }

    class ViewHolder{
        ImageView iv;
        TextView description;
        TextView keywords;

        /*
        private String description;
    private String img;
    private String keywords;
         */
    }
}
