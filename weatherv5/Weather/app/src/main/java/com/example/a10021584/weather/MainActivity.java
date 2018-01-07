package com.example.a10021584.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tvhi2;
    TextView tvlo2;
    TextView time2TV;
    TextView tvhi3;
    TextView tvlo3;
    TextView time3TV;
    TextView tvhi4;
    TextView tvlo4;
    TextView time4TV;
    TextView tvhi5;
    TextView tvlo5;
    TextView time5TV;
    TextView tvhi6;
    TextView tvlo6;
    TextView time6TV;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    ImageView iv6;
    TextView enterzipcode;
    TextView quote;
    TextView city;
    EditText zipcode;
    Button button;
    Button reload;

    JSONObject weather;
    String weatherString;
    String zip ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (TextView)findViewById(R.id.city);
        enterzipcode = (TextView)findViewById(R.id.enterzipcode);
        enterzipcode.setText("Enter Zipcode: ");
        zipcode = (EditText)findViewById(R.id.zipcode);
        button = (Button)findViewById(R.id.button);
        reload = (Button)findViewById(R.id.button2);
        quote = (TextView)findViewById(R.id.TVQuote);
        tv1 = (TextView) findViewById(R.id.TVMainTemp);
        tvhi2 = (TextView) findViewById(R.id.TVHi2);
        tvlo2 = (TextView) findViewById(R.id.TVLo2);
        time2TV = (TextView)findViewById(R.id.time2);
        tvhi3 = (TextView) findViewById(R.id.TVHi3);
        tvlo3 = (TextView) findViewById(R.id.TVLo3);
        time3TV = (TextView)findViewById(R.id.time3);
        tvhi4 = (TextView) findViewById(R.id.TVHi4);
        tvlo4 = (TextView) findViewById(R.id.TVLo4);
        time4TV = (TextView)findViewById(R.id.time4);
        tvhi5 = (TextView) findViewById(R.id.TVHi5);
        tvlo5 = (TextView) findViewById(R.id.TVLo5);
        time5TV = (TextView)findViewById(R.id.time5);
        tvhi6 = (TextView) findViewById(R.id.TVHi6);
        tvlo6 = (TextView) findViewById(R.id.TVLo6);
        time6TV = (TextView)findViewById(R.id.time6);
        iv1 = (ImageView)findViewById(R.id.IV1);
        iv2 = (ImageView)findViewById(R.id.IV2);
        iv3 = (ImageView)findViewById(R.id.IV3);
        iv4 = (ImageView)findViewById(R.id.IV4);
        iv5 = (ImageView)findViewById(R.id.IV5);
        iv6 = (ImageView)findViewById(R.id.IV6);


        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zip = zipcode.getText()+"";
                AsyncThread dataThread = new AsyncThread();
                dataThread.execute(zip);
            }
        });

    }
    public class AsyncThread extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... s) {
            try {

                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?zip="+s[0]+"&APPID=359e801bb06bab72926aa927bc215354");
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String reader;
                while ((reader = bufferedReader.readLine()) != null) {
                    weatherString += reader;
                }
                String test = weatherString.substring(4);
                weather = new JSONObject(test);
                Log.d("TAG",weather.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", "error "+e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void v) {
            try {
              for(int i=0;i<5;i++){
                  JSONArray jsonRoot = weather.getJSONArray("list");
                  JSONObject jsonTime = jsonRoot.getJSONObject(0);
                  JSONObject jsonMain = jsonTime.getJSONObject("main");
                  JSONArray jsonWeather = jsonTime.getJSONArray("weather");
                  String time1 = jsonTime.getString("dt_txt");
                  double minTemp1 = (double)(jsonMain.get("temp_min"));
                  double maxTemp1 = (double)(jsonMain.get("temp_max"));
                  JSONObject conditionList1 = jsonWeather.getJSONObject(0);
                  String condition1 = conditionList1.getString("main");

                  Log.d("TAGMAIN" , jsonMain.toString());
                  Log.d("TAGMIN", minTemp1+"");
                  Log.d("TAGMAX", maxTemp1+"");
                  Log.d("TAGTIME", time1+"");
                  Log.d("TAGCONDITION", condition1);

                  JSONObject jsonTime2 = jsonRoot.getJSONObject(1);
                  JSONObject jsonMain2 = jsonTime2.getJSONObject("main");
                  JSONArray jsonWeather2 = jsonTime2.getJSONArray("weather");
                  String time2 = jsonTime2.getString("dt_txt");
                  JSONObject conditionList2 = jsonWeather2.getJSONObject(0);
                  String condition2 = conditionList2.getString("main");
                  double minTemp2 = (double)(jsonMain2.get("temp_min"));
                  double maxTemp2 = (double)(jsonMain2.get("temp_max"));

                  JSONObject jsonTime3 = jsonRoot.getJSONObject(2);
                  JSONObject jsonMain3 = jsonTime3.getJSONObject("main");
                  JSONArray jsonWeather3 = jsonTime3.getJSONArray("weather");
                  String time3 = jsonTime3.getString("dt_txt");
                  JSONObject conditionList3 = jsonWeather3.getJSONObject(0);
                  String condition3 = conditionList3.getString("main");
                  double minTemp3 = (double)(jsonMain3.get("temp_min"));
                  double maxTemp3 = (double)(jsonMain3.get("temp_max"));

                  JSONObject jsonTime4 = jsonRoot.getJSONObject(3);
                  JSONObject jsonMain4 = jsonTime4.getJSONObject("main");
                  JSONArray jsonWeather4 = jsonTime4.getJSONArray("weather");
                  String time4 = jsonTime4.getString("dt_txt");
                  JSONObject conditionList4 = jsonWeather4.getJSONObject(0);
                  String condition4 = conditionList4.getString("main");
                  double minTemp4 = (double)(jsonMain4.get("temp_min"));
                  double maxTemp4 = (double)(jsonMain4.get("temp_max"));

                  JSONObject jsonTime5 = jsonRoot.getJSONObject(4);
                  JSONObject jsonMain5 = jsonTime5.getJSONObject("main");
                  JSONArray jsonWeather5 = jsonTime5.getJSONArray("weather");
                  String time5 = jsonTime5.getString("dt_txt");
                  JSONObject conditionList5 = jsonWeather5.getJSONObject(0);
                  String condition5 = conditionList5.getString("main");
                  double minTemp5 = (double)(jsonMain5.get("temp_min"));
                  double maxTemp5 = (double)(jsonMain5.get("temp_max"));

                  JSONObject jsonCity = weather.getJSONObject("city");
                  String cityName = jsonCity.getString("name");
                  city.setText("Weather for "+cityName);

                  tv1.setText(kelvinFarenheit(maxTemp1)+"°F");

                  tvlo2.setText(kelvinFarenheit(minTemp1)+"°F");
                  tvhi2.setText(kelvinFarenheit(maxTemp1)+"°F");
                  time2TV.setText(timeConvert(time1));

                  tvhi3.setText(kelvinFarenheit(maxTemp2)+"°F");
                  tvlo3.setText(kelvinFarenheit(minTemp2)+"°F");
                  time3TV.setText(timeConvert(time2));

                  tvhi4.setText(kelvinFarenheit(maxTemp3)+"°F");
                  tvlo4.setText(kelvinFarenheit(minTemp3)+"°F");
                  time4TV.setText(timeConvert(time3));

                  tvhi5.setText(kelvinFarenheit(maxTemp4)+"°F");
                  tvlo5.setText(kelvinFarenheit(minTemp4)+"°F");
                  time5TV.setText(timeConvert(time4));

                  tvhi6.setText(kelvinFarenheit(maxTemp5)+"°F");
                  tvlo6.setText(kelvinFarenheit(minTemp5)+"°F");
                  time6TV.setText(timeConvert(time5));

                  switch (condition1) {
                      case "Clear":iv1.setImageResource(R.drawable.sun);iv2.setImageResource(R.drawable.sun);quote.setText("Charizard used Flamethrower!");break;
                      case "Rain":iv1.setImageResource(R.drawable.rain);iv2.setImageResource(R.drawable.rain);quote.setText("Blastoise used Hydro Pump!");break;
                      case "Snow":iv1.setImageResource(R.drawable.snow);iv2.setImageResource(R.drawable.snow);quote.setText("Lapras used Blizzard!");break;
                      case "Thunderstorms":iv1.setImageResource(R.drawable.thunder);iv2.setImageResource(R.drawable.thunder);quote.setText("Pikachu used Thunderbolt!");break;
                      default:iv1.setImageResource(R.drawable.cloudy);iv2.setImageResource(R.drawable.cloudy);quote.setText("Snorlax used Haze!");break;
                  }
                  switch (condition2) {
                      case "Clear":iv3.setImageResource(R.drawable.sun);break;
                      case "Rain":iv3.setImageResource(R.drawable.rain);break;
                      case "Snow":iv3.setImageResource(R.drawable.snow);break;
                      case "Thunderstorms":iv3.setImageResource(R.drawable.thunder);break;
                      default:iv3.setImageResource(R.drawable.cloudy);break;
                  }
                  switch (condition3) {
                      case "Clear":iv4.setImageResource(R.drawable.sun);break;
                      case "Rain":iv4.setImageResource(R.drawable.rain);break;
                      case "Snow":iv4.setImageResource(R.drawable.snow);break;
                      case "Thunderstorms":iv4.setImageResource(R.drawable.thunder);break;
                      default:iv4.setImageResource(R.drawable.cloudy);break;
                  }
                  switch (condition4) {
                      case "Clear":iv5.setImageResource(R.drawable.sun);break;
                      case "Rain":iv5.setImageResource(R.drawable.rain);break;
                      case "Snow":iv5.setImageResource(R.drawable.snow);break;
                      case "Thunderstorms":iv5.setImageResource(R.drawable.thunder);break;
                      default:iv5.setImageResource(R.drawable.cloudy);break;
                  }
                  switch (condition5) {
                      case "Clear":iv6.setImageResource(R.drawable.sun);break;
                      case "Rain":iv6.setImageResource(R.drawable.rain);break;
                      case "Snow":iv6.setImageResource(R.drawable.snow);break;
                      case "Thunderstorms":iv6.setImageResource(R.drawable.thunder);break;
                      default:iv6.setImageResource(R.drawable.cloudy);break;
                  }

            }
        } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int kelvinFarenheit(double temperature){
            temperature -= 273.15;
            temperature *=1.8;
            temperature +=32;
            return (int)temperature;
        }
        public String timeConvert(String time){
            String str = time.substring(11,13);
            int i = Integer.parseInt(str);
            if(i==0)
                return "12:00 AM";
            if(i>12)
                    return (i-=12)+":00 PM";
            else
                return i+":00 AM";
        }

    }

}




