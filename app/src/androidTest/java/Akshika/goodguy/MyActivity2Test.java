package Akshika.goodguy;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;
import android.widget.Button;

import ak.gg.MyActivity2;
import ak.gg.TaskWindow1;

import static android.app.PendingIntent.getActivity;

/**
 * Created by akshika47 on 8/14/2014.
 */
public class MyActivity2Test extends InstrumentationTestCase {

    public  MyActivity2Test ()
    {
               testOpenNextActivity();

    }

    public void testOpenNextActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(TaskWindow1.class.getName(), null, false);

        // open current activity.
        MyActivity2 myActivity =   new MyActivity2();
        final Button button = (Button)myActivity.findViewById(R.id.ib);
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // click button and open next activity.
                button.performClick();
            }
        });

        TaskWindow1 nx = (TaskWindow1) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 1000);
       // NextActivity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
        // next activity is opened and captured.
        assertNotNull(nx);
        nx.finish();
    }
}
