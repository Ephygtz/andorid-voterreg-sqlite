package com.tbeta.ephraim.voterregsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteRecordActivity extends AppCompatActivity {
    EditText idInput;
    Button delete;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_record);

        idInput = (EditText) findViewById(R.id.idEdt);
        delete = (Button) findViewById(R.id.deleteBtn);

        db = openOrCreateDatabase("voterDB", MODE_PRIVATE, null);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idInput.getText().toString().trim().length() == 0) {
                    errorMessage("Empty ID", "Kindly type in an ID..");
                }

                Cursor cursor = db.rawQuery("SELECT * FROM voterreg WHERE idNo = '"+idInput.getText()+"' ", null);

                if(cursor.moveToFirst()){
                    db.execSQL("DELETE FROM voterreg WHERE idNo = '"+idInput.getText()+"'");
                    errorMessage("DELETED", "Record Deleted Succesfully");
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
