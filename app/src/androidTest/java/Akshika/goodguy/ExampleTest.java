package Akshika.goodguy;

import android.database.Cursor;
import android.test.InstrumentationTestCase;

import java.io.IOException;

import ak.gg.DatabaseHelper;
import ak.gg.TaskWindow1;

/**
 * Created by akshika47 on 8/13/2014.
 */
public class ExampleTest extends InstrumentationTestCase {
    public void test() throws Exception {
        TaskWindow1 TW = new TaskWindow1();
        DatabaseHelper DBH = new DatabaseHelper(TW);
        try {
            DBH.creatDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] data = new String[2];
        DBH.openDatabase();
        Cursor c = DBH.query("select * FROM mother");             //reading from the table named "mother"
        if(c.moveToFirst())                                       //go to the first index
        {

            do{
                 data[0] = c.getString(1);
                //textviewObject.setText(c.getString(1));              //showing it in the screen
            }while(c.moveToNext());                               //checking whether the table is over
            c.close();                                               //closing the Cursor
            DBH.close();                                             //closing the DataBase handler
        }
        assertEquals("Test Passed",data[0],"Can you remember what your mother did for your birthdays? What are you planning to do for your mother's birth day? Get flowers from \"Second Chance\" for your mother");

    }


       /* final int expected = 5;
        final int reality = 5;
        assertEquals(expected, reality);*/
    }

