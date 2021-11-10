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
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static BlockButton[][] buttons = new BlockButton[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton flag = (ToggleButton) findViewById(R.id.toggleButton);
        TextView mineCount = (TextView) findViewById(R.id.MineCount);
        TableLayout table;
        table = (TableLayout)findViewById(R.id.tableLayout);

//        Button[][] buttons = new Button[9][9];

        Boolean mines[][] = new Boolean[9][9];
        for(Boolean[] arr : mines){
            Arrays.fill(arr, false);
        }

        Random rand = new Random();
        for(int i=0; i<10; i++){
            int x=0, y=0;
            x = rand.nextInt(9);
            y = rand.nextInt(9);
            mines[x][y] = true;
        }

        for(int i=0; i<9; i++){
            TableRow tableRow = new TableRow(this);

            for(int j=0; j<9; j++){
//                buttons[i][j] = new Button(this);
                buttons[i][j] = new BlockButton(this, i, j, mines[i][j]);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (flag.isChecked()) { //토글 버튼이 on이면
                            ((BlockButton) view).toggleFlag();  //깃발 추가/삭제
                            mineCount.setText(String.valueOf(BlockButton.flags));
                        }
                        else  //토글버튼이 break면
                            if (((BlockButton) view).breakBlock()) { // 칸열기
//                                ((BlockButton) view).open();

                            }
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


        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                int neighbMines = 0;
                if(!mines[i][j]){
                  if(i>0 && j>0 && mines[i-1][j-1]){ // 대각선 왼쪽 아래
                      neighbMines++;
                  } if(i<8 && j<8 && mines[i+1][j+1]){ // 대각선 오른쪽 위
                      neighbMines++;
                  } if(i>0 && j<8 && mines[i-1][j+1]){ // 대각선 왼쪽 위
                      neighbMines++;
                  } if(j>0 && i<8 && mines[i+1][j-1]){ // 대각선 오른쪽 아래
                      neighbMines++;
                  } if(i<8 && mines[i+1][j]){ // 오른쪽
                      neighbMines++;
                  } if(i>0 && mines[i-1][j]){ // 왼쪽
                      neighbMines++;
                  } if(j<8 && mines[i][j+1]){ // 위
                      neighbMines++;
                  } if(j>0 && mines[i][j-1]){ // 아래
                      neighbMines++;
                  }
                }
                buttons[i][j].neighborMines(neighbMines);
            }
        }
    }
}

