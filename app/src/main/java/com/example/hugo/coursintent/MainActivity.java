package com.example.hugo.coursintent;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.hugo.coursintent.DbContract.*;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText champTexte;
    Button btnEnvoi;
    Spinner spinnerUser;
    TextView historique;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        champTexte = (EditText) findViewById(R.id.champTexte);
        btnEnvoi = (Button) findViewById(R.id.btnEnvoi);
        spinnerUser = (Spinner) findViewById(R.id.spinnerUser);
        historique = (TextView) findViewById(R.id.historique);

        btnEnvoi.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.users, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUser.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEnvoi:
                if (spinnerUser.getSelectedItemId() == 0){
                    historique.append("USER 1 a dit : "+ champTexte.getText()+"\n\n");
                }
                if (spinnerUser.getSelectedItemId() == 1){
                    historique.append("USER 2 a dit : "+ champTexte.getText()+"\n\n");
                }
                champTexte.getText().clear();
                sendMessage(spinnerUser);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    private void sendMessage(Spinner spinnerUser){
        ContentValues cv = new ContentValues();

        cv.put(MessageEntries.USERNAME, spinnerUser.getSelectedItem().toString());
        db.insert(MessageEntries.TABLE_NAME,null,cv);
    }
}
