package com.example.jsonlistviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jsonlistviewpractice.model.Model_Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonTask Jtask = new jsonTask();
        Jtask.execute();
        listView = findViewById(R.id.list_view_id);

    }

    public class jsonTask extends AsyncTask<String,String, List<Model_Student>>{

        @Override
        protected  List<Model_Student> doInBackground(String... strings) {
            HttpsURLConnection httpsURLConnection = null;
            BufferedReader bufferedReader = null;
            String Jsonfile;
            try {
                URL url = new URL("https://api.myjson.com/bins/jls74");
                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.connect();
                InputStream inputStream = httpsURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";

                while ((line=bufferedReader.readLine()) != null){

                    stringBuffer.append(line);
                }
                Jsonfile = stringBuffer.toString();
                JSONObject jsonObject = new JSONObject(Jsonfile);
                JSONArray jsonArray = jsonObject.getJSONArray("student");
                List<Model_Student> model_studentList = new ArrayList<>();

                for(int i = 0; i<jsonArray.length(); i++) {
                    JSONObject ArryObject = jsonArray.getJSONObject(i);

                    Model_Student model_student = new Model_Student();
                    model_student.setName(ArryObject.getString("name"));
                    model_student.setDepartment(ArryObject.getString("department"));
                    model_student.setDistrict(ArryObject.getString("district"));

                    model_studentList.add(model_student);
                }
                return  model_studentList;



//                return stringBuffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                httpsURLConnection.disconnect();
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute( List<Model_Student> s) {
            super.onPostExecute(s);


            CustomAdapter customAdapter = new  CustomAdapter(getApplicationContext(),R.layout.simple,s) ;
            listView.setAdapter(customAdapter);
        }
    }
}
