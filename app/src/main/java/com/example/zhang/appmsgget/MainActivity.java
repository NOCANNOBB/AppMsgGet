package com.example.zhang.appmsgget;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    ContentResolver cr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cr = this.getContentResolver();
        Button b =(Button)this.findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stuname = "Android";
                Cursor cur = cr.query(
                        Uri.parse("content://com.bn.pp4.provider.student/stu"),
                        new String[]{"sno","stuname","sage","sclass"},
                        "stuname=?",
                        new String[]{stuname},
                        "sage ASC"
                );
                while(cur.moveToNext()){
                    String sno = cur.getString(0);
                    String sname = cur.getString(1);
                    int sage = cur.getInt(2);
                    String sclass= cur.getString(3);
                    appendMessage(sno + "\t" + sname + "\t\t" + sage + "\t" + sclass);
                }
            }
        });
    }

    public void appendMessage(String Msg){
        EditText et = (EditText)this.findViewById(R.id.textShow);
        et.append(Msg);
    }
}
