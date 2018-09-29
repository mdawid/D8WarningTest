package com.domain.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {
	private RestTemplate        restTemplate = RestServiceFactory.makeService();
	private TextView            resultText;
	private CompositeDisposable compositeDisposable;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		resultText = findViewById( R.id.resultText );

		compositeDisposable = new CompositeDisposable();
	}

	@Override protected void onResume() {
		super.onResume();
		compositeDisposable
				.add( Single.fromCallable( () -> restTemplate.getForObject( "https://api.github.com/repos/square/okhttp/branches", String.class ) )
							  .doOnSubscribe( disposable -> resultText.setText( "Please wait..." ) )
							  .subscribeOn( Schedulers.newThread() )
							  .observeOn( AndroidSchedulers.mainThread() )
							  .subscribe( s -> resultText.setText( s ),
										  throwable -> resultText.setText( throwable.getMessage() ) ) );
	}

	@Override protected void onDestroy() {
		super.onDestroy();
		compositeDisposable.dispose();
	}
}
