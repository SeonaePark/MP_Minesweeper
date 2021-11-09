package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton flag = (ToggleButton) findViewById(R.id.toggleButton);

        TableLayout table;
        table = (TableLayout)findViewById(R.id.tableLayout);

//        Button[][] buttons = new Button[9][9];
        BlockButton[][] buttons = new BlockButton[9][9];


        for(int i=0; i<9; i++){
            TableRow tableRow = new TableRow(this);

            for(int j=0; j<9; j++){
//                buttons[i][j] = new Button(this);
                buttons[i][j] = new BlockButton(this, i, j);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(flag.isChecked())  //토글 버튼이 on이면
                            ((BlockButton)view).toggleFlag();  //깃발 추가/삭제
                        else  //토글버튼이 break면
                            ((BlockButton)view).breakBlock(view);  //칸 열기
                    }
                });

                TableRow.LayoutParams layoutParams =
                        new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT,
                                1.0f
                        );
                buttons[i][j].setLayoutParams(layoutParams);
                tableRow.addView(buttons[i][j]);
            }
            table.addView(tableRow);
        }
    }
}

