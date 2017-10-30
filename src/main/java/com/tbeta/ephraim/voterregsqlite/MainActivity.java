package com.tbeta.ephraim.voterregsqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    //Declare view variables
    EditText mName, mEmail, mId;
    Button mSave, mView, mSearch, mDelete;
    ProgressBar load;

    //DB variable
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = (EditText) findViewById(R.id.nameEdt);
        mEmail = (EditText) findViewById(R.id.emailEdt);
        mId = (EditText) findViewById(R.id.idEdt);
        mSave = (Button) findViewById(R.id.saveBtn);
        mView = (Button) findViewById(R.id.viewBtn);
        mSearch = (Button) findViewById(R.id.searchBtn);
        mDelete = (Button) findViewById(R.id.delBtn);
        load = (ProgressBar) findViewById(R.id.progressBar);

        //Method for creating a database(Create/View)
        db = openOrCreateDatabase("voterDB", MODE_PRIVATE, null );

        // Query to create a TABLE with three columns
        db.execSQL("CREATE TABLE IF NOT EXISTS voterreg(name VARCHAR, email VARCHAR, idNo VARCHAR)");

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if user has filled in all the values
                if(mName.getText().toString().trim().length() == 0) {
                    errorMessage("NAME ERROR", "Kindly fill in your name");
                } else if(mEmail.getText().toString().trim().length() == 0){
                    errorMessage("EMAIL ERROR", "Please enetr your email address");
                }else if(mId.getText().toString().trim().length() == 0){
                    errorMessage("ID ERROR", "Kindly eneter your ID");
                }


                else{
                    // Insert data to DB
                    db.execSQL("INSERT INTO voterreg VALUES('"+mName.getText()+"', '"+mEmail.getText()+"', '"+mId.getText()+"')");
                    errorMessage("QUERY SUCCESS", "Data was succesfully saved");
                }
            }
        });
    }

    public  void errorMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}
