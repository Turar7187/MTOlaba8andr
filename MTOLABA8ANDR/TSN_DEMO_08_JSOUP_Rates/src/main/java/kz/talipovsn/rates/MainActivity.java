package kz.talipovsn.rates;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        JSOUPRatesTask task = new JSOUPRatesTask();
        task.execute();
    }


    public void onClick(View view) {
        new JSOUPRatesTask().execute();
    }


    @SuppressLint("StaticFieldLeak")
    private class JSOUPRatesTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            return RatesReader.getRatesData();
        }
        // ----------------------------------------------------------------------------


        @Override
        protected void onPostExecute(final String rates) {
            super.onPostExecute(rates);


            textView.post(new Runnable() {
                @Override
                public void run() {
                    if (rates != null) {
                        textView.setText(rates);
                    } else {
                        textView.setText("");
                        textView.append("Нет данных!" + "\n");
                        textView.append("Проверьте доступность Интернета");
                    }
                }
            });

        }
        // ------------------------------------------------------------------------------------

    }

}