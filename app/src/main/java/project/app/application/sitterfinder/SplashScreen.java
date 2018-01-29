package project.app.application.sitterfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;


public class SplashScreen extends FragmentActivity {

    private static final int SPLASH_SHOW_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final ImageView imageView = (ImageView) findViewById(R.id.splashimg);
        imageView.setImageResource(R.drawable.splash);

                new BackgroundSplashTask().execute();

            }

            /**
             * Async Task: can be used to load DB, images during which the splash screen
             * is shown to user
             */
            @SuppressLint("NewApi")
            private class BackgroundSplashTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected Void doInBackground(Void... arg0) {

                    // I have just give a sleep for this thread
                    // if you want to load database, make
                    // network calls, load images
                    // you can do here and remove the following
                    // sleep

                    // do not worry about this Thread.sleep
                    // this is an async task, it will not disrupt the UI
                    try {
                        Thread.sleep(SPLASH_SHOW_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                @SuppressLint("NewApi")
                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    Intent i = new Intent(SplashScreen.this,
                            MainActivity.class);
                    // any info loaded can during splash_show
                    // can be passed to main activity using
                    // below
                    i.putExtra("loaded_info", " ");
                    startActivity(i);
                    finish();
                }

            }

        }