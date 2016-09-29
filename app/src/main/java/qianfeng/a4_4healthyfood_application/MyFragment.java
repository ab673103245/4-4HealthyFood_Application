package qianfeng.a4_4healthyfood_application;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class MyFragment extends Fragment {
    private ListView lv;
    private String id;

    //

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview, container, false);


        TextView tv = new TextView(getActivity());
        tv.setText(getArguments().getString("id"));
        tv.setTextSize(32);

        lv = (ListView) view.findViewById(R.id.lv);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        id = getArguments().getString("id");
        new MyTask().execute("http://www.tngou.net/api/cook/list?id=?", id); // 两个Fragment之间的通信
    }

    public static MyFragment getInstance(String id) // 工厂方法生成Fragment实例
    {
        MyFragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("id",id);
        myFragment.setArguments(args);
        return myFragment;
    }

    public class MyTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... params) {
            return getHttp(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            List<FoodBean> foodBeanList = parseJson(s);


            if(id.equals("1"))
            {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.header, null);
                lv.addHeaderView(view);
                ViewPager viewPager = (ViewPager) view.findViewById(R.id.header_viewPager);
                List<String> list = new ArrayList<>();
                list.add("http://tnfs.tngou.net/image/cook/150802/1340f07baad474a757825191701d5e1e.jpg");
                list.add("http://tnfs.tngou.net/image/cook/150802/d93e6aa700243192ca4f78f26bf14fe9.jpg");
                list.add("http://tnfs.tngou.net/image/cook/150802/0c8f93ae57fbf175361d6949932a285e.jpg");
                list.add("http://tnfs.tngou.net/image/cook/150802/2914d4174a8fb57f1b974879537f1b6b.jpg");
                list.add("http://tnfs.tngou.net/image/cook/150802/1ab358cb54759f7f83928c4d5b97be87.jpg");

                viewPager.setAdapter(new MyPagerAdapter(getActivity(),list));
            }

            lv.setAdapter(new MyBaseAdapter(MyFragment.this,foodBeanList));
        }

        private List<FoodBean> parseJson(String s) {

            List<FoodBean> foodBeanList = new ArrayList<>();
            JSONArray tngou = null;
            try {
                tngou = new JSONObject(s).getJSONArray("tngou");
                for (int i = 0; i < tngou.length(); i++) {
                    JSONObject jsonObject = tngou.getJSONObject(i);
                    String description = jsonObject.getString("description");
                    String img = "http://tnfs.tngou.net/image" + jsonObject.getString("img");
                    String keywords = jsonObject.getString("keywords");
                    foodBeanList.add(new FoodBean(description,img,keywords));
                }
                return foodBeanList;
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

    private String getHttp(String param) {
        HttpURLConnection connection;
        StringBuffer result = new StringBuffer();
        BufferedReader br;
        try {
            URL url = new URL(param);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String str;
                while ((str = br.readLine()) != null) {
                    result.append(str);
                }
                return result.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
