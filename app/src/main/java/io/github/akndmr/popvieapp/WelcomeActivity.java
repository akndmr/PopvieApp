package io.github.akndmr.popvieapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.ChangeBounds;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WelcomeActivity extends AppCompatActivity {

    private final ConstraintSet mConstraintSet1 = new ConstraintSet();
    private final ConstraintSet mConstraintSet2 = new ConstraintSet();
    private TextView appName;
    private ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConstraintSet2.clone(this, R.layout.activity_welcome2);
        setContentView(R.layout.activity_welcome);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        appName = findViewById(R.id.tv_app_name);

        if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/pacifico.otf");
            appName.setTypeface(typeface);
        }

        mConstraintLayout = findViewById(R.id.cl_welcome_1);
        mConstraintSet1.clone(mConstraintLayout);

        animate();
        goToMovies();
    }

    private void animate() {
        final Transition transition = new ChangeBounds();
        transition.setInterpolator(new OvershootInterpolator());
        transition.setDuration(1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(mConstraintLayout, transition);
                mConstraintSet2.applyTo(mConstraintLayout);
            }
        }, 500);
    }

    private void goToMovies() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MoviesActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        animate();
        goToMovies();
    }
}
