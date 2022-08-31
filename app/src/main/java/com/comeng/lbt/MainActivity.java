package com.comeng.lbt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText book_title, book_author;
    private Button popup_save, popup_cancel;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DB = new DBHelper(this);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ImageButton plusbutton = findViewById(R.id.plusbutton);
        ImageButton rbk1 = findViewById(R.id.RomBk1);
        ImageButton rbk2 = findViewById(R.id.RomBk2);
        ImageButton rbk3 = findViewById(R.id.RomBk3);
        ImageButton rbk4 = findViewById(R.id.RomBk4);
        ImageButton rbk5 = findViewById(R.id.RomBk5);
        ImageButton cbk1 = findViewById(R.id.ComBk1);
        ImageButton cbk2 = findViewById(R.id.ComBk2);
        ImageButton cbk3 = findViewById(R.id.ComBk3);
        ImageButton cbk4 = findViewById(R.id.ComBk4);
        ImageButton cbk5 = findViewById(R.id.ComBk5);
        ImageButton mbk1 = findViewById(R.id.MysBk1);
        ImageButton mbk2 = findViewById(R.id.MysBk2);
        ImageButton mbk3 = findViewById(R.id.MysBk3);
        ImageButton mbk4 = findViewById(R.id.MysBk4);
        ImageButton mbk5 = findViewById(R.id.MysBk5);

        Intent popup = new Intent(getApplicationContext(), BkDescription.class);

        plusbutton.setOnClickListener(view -> createNewPopupDialog());
        rbk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT);
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Book Title: " + res.getString(0) + "\n");
                    buffer.append("Book Author: " + res.getString(1) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Book Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        rbk2.setOnClickListener(view -> startActivity(popup));
        rbk3.setOnClickListener(view -> startActivity(popup));
        rbk4.setOnClickListener(view -> startActivity(popup));
        rbk5.setOnClickListener(view -> startActivity(popup));
        cbk1.setOnClickListener(view -> startActivity(popup));
        cbk2.setOnClickListener(view -> startActivity(popup));
        cbk3.setOnClickListener(view -> startActivity(popup));
        cbk4.setOnClickListener(view -> startActivity(popup));
        cbk5.setOnClickListener(view -> startActivity(popup));
        mbk1.setOnClickListener(view -> startActivity(popup));
        mbk2.setOnClickListener(view -> startActivity(popup));
        mbk3.setOnClickListener(view -> startActivity(popup));
        mbk4.setOnClickListener(view -> startActivity(popup));
        mbk5.setOnClickListener(view -> startActivity(popup));
    }

    public void createNewPopupDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View bookPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        book_title = (EditText) bookPopupView.findViewById(R.id.newpopup_booktitle);
        book_author = (EditText) bookPopupView.findViewById(R.id.newpopup_bookauthor);

        popup_save = (Button) bookPopupView.findViewById(R.id.saveButton);
        popup_cancel = (Button) bookPopupView.findViewById(R.id.cancelButton);

        dialogBuilder.setView(bookPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        popup_save.setOnClickListener(v -> {
            String booktitleTXT = book_title.getText().toString();
            String bookauthorTXT = book_author.getText().toString();

            Boolean checksavedata = DB.insertbookdata(booktitleTXT, bookauthorTXT);
            if (checksavedata ==  true) {
                Toast.makeText(MainActivity.this, "New Book Entry Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "New Book Not Entry Inserted", Toast.LENGTH_SHORT).show();
            }
            // define save button
        });

        popup_cancel.setOnClickListener(v -> {

            // define cancel button

            dialog.dismiss();
        });
    }

}