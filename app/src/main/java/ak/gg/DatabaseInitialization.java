package ak.gg;

import android.database.Cursor;
import android.widget.TextView;

import java.io.IOException;

import Akshika.goodguy.R;

/**
 * Created by akshika47 on 8/13/2014.
 */
public class DatabaseInitialization {

    public DatabaseInitialization(TaskWindow1 MA)
    {

        DatabaseHelper DBH = new DatabaseHelper(MA);

        TextView textviewObject = (TextView) MA.findViewById(R.id.TV);
        String[] data = new String[2];
        int count =0;
        try {
            DBH.creatDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DBH.openDatabase();
        Cursor c = DBH.query("select * FROM mother");             //reading from the table named "mother"
        if(c.moveToFirst())                                       //go to the first index
        {
            do{
                data[count] = c.getString(1);
               //textviewObject.setText(c.getString(1));              //showing it in the screen
               count++;
            }while(c.moveToNext());                               //checking whether the table is over
            c.close();                                               //closing the Cursor
            DBH.close();                                             //closing the DataBase handler
        
        }
        int a = (int)(Math.random()*10);
        if(a>5)
        {
            a=1;
        }
        else
        {
            a=0;
        }
            textviewObject.setText(data[a]);              //showing it in the screen

        }


}
