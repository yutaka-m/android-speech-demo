package jp.alumican.speechdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnClickListener ,RecognitionListener {
	private String TAG = "MainActivity";
	SpeechRecognizer sr;
	int video_src = R.raw.m1;
	VideoView vv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		vv = (VideoView)findViewById(R.id.videoView1);
		vv.setVideoPath("android.resource://jp.alumican.speechdemo/"+R.raw.m1);
		vv.start();
		vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				vv.setVideoPath("android.resource://jp.alumican.speechdemo/"+video_src);
				vv.seekTo(0);
				vv.start();

			}
		});
		
		sr = SpeechRecognizer.createSpeechRecognizer(this);
		sr.setRecognitionListener(this);
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
				getPackageName());
		sr.startListening(intent);
	}

	@Override
	public void onBeginningOfSpeech() {
		Log.d(TAG,"onBeginningOfSpeech");
	}

	@Override
	public void onBufferReceived(byte[] buffer) {
		Log.d(TAG,"onBufferReceived");
	}

	@Override
	public void onEndOfSpeech() {
		Log.d(TAG,"onEndOfSpeech");
	}

	@Override
	public void onError(int error) {
		Log.d(TAG,"onError");
		switch (error){
		case SpeechRecognizer.ERROR_AUDIO:
			Log.d(TAG,"error audio");
			Toast.makeText(this,"error audio.",Toast.LENGTH_LONG);
			break;
		case SpeechRecognizer.ERROR_CLIENT:
			Log.d(TAG,"error client");
			Toast.makeText(this,"error client.",Toast.LENGTH_LONG);
			break;
		case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
			Log.d(TAG,"error insufficient permissions");
			Toast.makeText(this,"error insufficient permissions.",Toast.LENGTH_LONG);
			break;
		case SpeechRecognizer.ERROR_NETWORK:
			Log.d(TAG,"error network");
			Toast.makeText(this,"error network.",Toast.LENGTH_LONG);
			break;
		case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
			Log.d(TAG,"error network timeout");
			Toast.makeText(this,"error network timeout.",Toast.LENGTH_LONG);
			break;
		case SpeechRecognizer.ERROR_NO_MATCH:
			Log.d(TAG,"error no match");
			Toast.makeText(this,"error no match.",Toast.LENGTH_LONG);
			break;
		case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
			Log.d(TAG,"error recognizer busy");
			Toast.makeText(this,"error recognizer busy.",Toast.LENGTH_LONG);
			break;
		case SpeechRecognizer.ERROR_SERVER:
			Log.d(TAG,"error server");
			Toast.makeText(this,"error server.",Toast.LENGTH_LONG);
			break;
		case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
			Log.d(TAG,"error timeout");
			Toast.makeText(this,"timeout.",Toast.LENGTH_LONG);
			break;
		default:
			break;
		}
		
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
				getPackageName());
		sr.startListening(intent);
	}

	@Override
	public void onEvent(int eventType, Bundle params) {
		Log.d(TAG,"onEvent");
	}

	@Override
	public void onPartialResults(Bundle partialResults) {
		Log.d(TAG,"onPartialResults");
	}

	@Override
	public void onReadyForSpeech(Bundle params) {
		Log.d(TAG,"onReadyForSpeech");
	}

	@Override
	public void onResults(Bundle results) {
		Log.d(TAG,"onResults");
		ArrayList<String> recData = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
		String getData = new String();
		for (String s : recData) {
			if("しょぼーん".equals(s)) {video_src = R.raw.m1;}
			if("しゃきーん".equals(s)) {video_src = R.raw.m2;}
			if("きえろ".equals(s)) {video_src = R.raw.m3;}
			if("あらわれろ".equals(s)) {video_src = R.raw.m4;}
			if("とべ".equals(s)) {video_src = R.raw.m5;}
			if("てすと".equals(s)) {video_src = R.raw.m6;}
			getData += s + ",";
		}
		Toast.makeText(this,getData, Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
				getPackageName());
		sr.startListening(intent);
	}

	@Override
	public void onRmsChanged(float rmsdB) {
		Log.d(TAG,"onRmsChanged");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
			
	
}
