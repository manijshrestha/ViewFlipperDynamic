package com.example.viewflipperdynamic;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class DynamicViewFlipper extends Activity {

	/**
	 * List of Image URL that will populate the ViewFlipper
	 */
	private List<String> imageURLs = Arrays.asList(new String[] { "http://manijshrestha.github.io/android-icon.png",
			"http://manijshrestha.github.io/iPhone-icon.png", "http://manijshrestha.github.io/windows-icon.png" });

	private int index = 0;

	private TextView mTextView;
	
	private ViewFlipper mViewFlipper;

	private Button mNextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_flipper);
		mTextView = (TextView) findViewById(R.id.title);
		mNextButton = (Button) findViewById(R.id.nextBtn);
		mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		
		ImageView startImage = getNewImageView();
		startImage.setImageResource(R.drawable.unknown);
		mViewFlipper.addView(startImage);

		mNextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ImageView image = getNewImageView();
				UrlImageViewHelper.setUrlDrawable(image, getNextImage(), R.drawable.loading);
				mTextView.setText("Showing: " + index + "# of View in flipper " + mViewFlipper.getChildCount());
				mViewFlipper.addView(image);
				mViewFlipper.showNext();
				mViewFlipper.removeViewAt(0);
			}
		});
		
		mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
		mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_out_right));
	}

	protected ImageView getNewImageView() {
		ImageView image = new ImageView(getApplicationContext());
		image.setScaleType(ScaleType.CENTER_INSIDE);
		return image;
	}
	
	protected String getNextImage() {
		if (index == imageURLs.size())
			index = 0;
		return imageURLs.get(index++);
	}
}
