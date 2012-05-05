package jp.alumican.speechdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class SpeechDemoActivity extends Activity {
	private String TAG = "SpeechDemoActivity";
	private int bootflag = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	Log.d(TAG,"onStart");
    	bootflag = 0;
    }
    @Override
    public void onStop() {
    	super.onStop();
    	Log.d(TAG,"onStop");
    }
    @Override
    public void onRestart() {
    	super.onRestart();
    	Log.d(TAG,"onRestart");
    }
    @Override
    public void onResume() {
    	super.onResume();
    	Log.d(TAG,"onResume");
    }
    @Override
    public void onPause() {
    	super.onPause();
    	Log.d(TAG,"onPause");
    }
    @Override
	public void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG,"onDestroy");
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent e) {
    	if(bootflag == 0) {
    		Intent intent = new Intent(this,MainActivity.class);
    		startActivity(intent);
    		bootflag = 1;
    	}
    	return super.onTouchEvent(e);
    }
}