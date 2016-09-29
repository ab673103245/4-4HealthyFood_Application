package qianfeng.a4_4healthyfood_application;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = ((TabLayout) findViewById(R.id.tablayout));
        viewPager = ((ViewPager) findViewById(R.id.viewPager));

        new MyAsyncTask().execute("http://www.tngou.net/api/cook/classify");



    }


    private class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String http = getHttp(params[0]);
            return http;
        }

        private String getHttp(String param) {

//            URL url = null;
//            HttpURLConnection con = null;
//            StringBuffer buffer = new StringBuffer();
//            try {
//                url = new URL(param);
//                con = (HttpURLConnection) url.openConnection();
//                con.setConnectTimeout(5 * 1000);
//                con.connect();
//                if (con.getResponseCode() == 200) {
//                    String str = "";
//                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    while ((str = br.readLine()) != null) {
//                        buffer.append(str);
//                    }
//                }
//                return buffer.toString();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            return null;
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

        @Override
        protected void onPostExecute(String s) {

            List<ClassfyBean> classfyBeen = parseJson(s);
            List<Fragment> fragments = new ArrayList<>();

            for (int i = 0; i < classfyBeen.size(); i++) {
//                fragments.add()
                MyFragment instance = MyFragment.getInstance(classfyBeen.get(i).getId());
                fragments.add(instance);
            }

            viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),classfyBeen,fragments));
            tabLayout.setupWithViewPager(viewPager);


        }

//        private List<ClassfyBean> parseJson(String s)
//        {
//            List<ClassfyBean> list = new ArrayList<>();
//            try {
//                JSONObject object = new JSONObject(s);
//                JSONArray tngou = object.getJSONArray("tngou");
//                for (int i = 0; i < tngou.length(); i++) {
//                    JSONObject jsonObject = tngou.getJSONObject(i);
//                    String id = jsonObject.getString("id");
//                    String name = jsonObject.getString("name");
//                    list.add(new ClassfyBean(id,name));
//
//
//                }
//                return list;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
private List<ClassfyBean> parseJson(String jsonStr) {
    List<ClassfyBean> list = new ArrayList<>();
    try {
        JSONArray tngou = new JSONObject(jsonStr).getJSONArray("tngou");
        for (int i = 0; i < tngou.length(); i++) {
            JSONObject data = tngou.getJSONObject(i);
            String name = data.getString("name");
            String  id = data.getString("id");
            ClassfyBean bean = new ClassfyBean(id,name);
            bean.setId(id);
            bean.setName(name);
            list.add(bean);
        }
        return list;
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return null;
}


    }




}
