package com.domain.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {
	private RestTemplate restTemplate = RestServiceFactory.makeService();
	private TextView     resultText;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		resultText = findViewById( R.id.resultText );
	}

	@Override protected void onResume() {
		super.onResume();
		new AsyncTask<Void, Void, String>() {
			@Override protected void onPreExecute() {
				resultText.setText( "Please wait..." );
			}

			@Override protected String doInBackground( final Void... voids ) {
				return restTemplate.getForObject( "https://api.github.com/repos/square/okhttp/branches", String.class );
			}

			@Override protected void onPostExecute( final String bufferooResponse ) {
				resultText.setText( bufferooResponse );
			}
		}.execute();
	}
}
