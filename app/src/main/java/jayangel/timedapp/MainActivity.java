package jayangel.timedapp;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    //Declare our View variables
    private TextView mTextView;
    private Button mButton1;
    private Button mButton2;
    private CountDown mCountDown;
    private CountDownTimerActivity butter;
    private long mMilisLeft = 5*1000;
    boolean mPause = false;
    boolean mStart = false;
    int counter = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<String> appleButter = appNames();

        //Assign the view from the layout file to the corresponding values
        mCountDown = new CountDown();
        mTextView = (TextView) findViewById(R.id.textView1);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);

        final Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.andrewshu.android.reddit");
       final LocalService ls = new LocalService();


//------------------------------------------------------------------------------------------------//
//                                  What button1 does                                             //
//------------------------------------------------------------------------------------------------//

        View.OnClickListener listen1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(mPause && !mStart)
                {
                     butter = new CountDownTimerActivity(mMilisLeft, 1000);
                     butter.start();
                }
                else
                {
                    butter.cancel();
                    mPause = true;
                    mStart = false;
                }*/

                // mTextView.setText(appleButter.get(counter++));
                //mTextView.setText(Integer.toString(appleButter.size()));
              /*  for(String place: appleButter)
                {
                    String[] stuff = place.split( "[.]" );
                    for(String that: stuff)
                    {
                        if(that.toLowerCase() == "reddit"|| that.toLowerCase() == "redditisfun")
                        {
                            mTextView.setText(appleButter.indexOf(stuff)+ "_" + place);
                        }

                    }
                }
                */


            ls.startService(launchIntent);








            }

        };

        mButton1.setOnClickListener(listen1);

//------------------------------------------------------------------------------------------------//
//                                  What button2 does                                             //
//------------------------------------------------------------------------------------------------//


        View.OnClickListener listen2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //butter = new CountDownTimerActivity(10*1000,1000);
                //butter.start();
                ls.stopService(launchIntent);

            }
        };
        mButton2.setOnClickListener(listen2);
    }

//------------------------------------------------------------------------------------------------//
//                                  Allows me to run things in the background                     //
//------------------------------------------------------------------------------------------------//

    public class LocalService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

@Override
public void onCreate()
{

}

       public int onStartCommand(Intent intent, int one, int two)
        {


            return 1;
        }

        @Override
        public void onDestroy()
        {



        }
    }

//------------------------------------------------------------------------------------------------//
//                                  CountDowner                                                   //
//------------------------------------------------------------------------------------------------//


    public class CountDownTimerActivity extends CountDownTimer
    {
        public CountDownTimerActivity(long startTime, long interval) {
            super(startTime, interval);
            mStart = true;
        }

        @Override
        public void onFinish() {
            mTextView.setText("Time's up!");
            finish();

        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            mTextView.setText(mCountDown.time(millisUntilFinished));
            mMilisLeft = millisUntilFinished;
        }
    }


//------------------------------------------------------------------------------------------------//
//                          makes a string list of all of the installed apps                      //
//------------------------------------------------------------------------------------------------//


    public List<String> appNames() {
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> allPackages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> bubbles = new ArrayList<>();

        for (ApplicationInfo apInfo : allPackages) {
            if (apInfo.className != null) {
                bubbles.add(apInfo.className);
            }
        }

        return bubbles;
    }


}
