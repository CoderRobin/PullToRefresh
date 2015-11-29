package com.coderrobin.customview.RippleVIew;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.coderrobin.customview.R;

public class RippleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ripple_layout);
		((RippleView)findViewById(R.id.ripple_view)).startRippleAnimation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
