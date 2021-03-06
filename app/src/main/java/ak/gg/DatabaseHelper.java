package ak.gg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by akshika47 on 8/12/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/Akshika.goodguy/databases/";

    private static String DB_NAME = "family";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null,1);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void creatDataBase() throws IOException
    {

        boolean dbExit  = checkDataBase();

        if(dbExit)
        {

            //do nothing , Database is still here
        }
        else
        {
            this.getWritableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {

                throw new Error("There was error copying the Database");
            }
        }
    }

    private boolean checkDataBase()
    {

        SQLiteDatabase checkDB = null;

        try{

            File dbFile = new File(DB_PATH+DB_NAME);
            return dbFile.exists();

        }catch(SQLiteException e)
        {

            //database does not exist yet.
        }

        if(checkDB != null)
        {
            checkDB.close();
        }
        return checkDB != null ? true : false;
     }

    public void copyDataBase() throws IOException{

        InputStream myInput  = myContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;

        while((length = myInput.read(buffer))>0){

            myOutput.write(buffer,0,length);
        }
              myOutput.flush();
              myOutput.close();
              myInput.close();



    }

    public void openDatabase() throws SQLiteException{

        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close()
    {
        if(myDataBase != null)
          myDataBase.close();

        super.close();
    }

    public Cursor query(String query)
    {
        openDatabase();

        Cursor cursor = myDataBase.rawQuery(query,null);
        return cursor;

    }
}
