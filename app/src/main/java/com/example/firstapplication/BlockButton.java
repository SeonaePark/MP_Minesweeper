package com.example.firstapplication;

import android.content.Context;
import android.graphics.Color;
import android.text.style.BackgroundColorSpan;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

class BlockButton extends AppCompatButton {
    int x,y;
    boolean mine;  // 지뢰인지
    boolean flag;  // 깃발인지
    int neighborMines;  //근처 지뢰 갯수
    static public int flags = 10;  //남은 깃발
    static int blocks = 81;  //남은 블록

    public int getBlocks() {
        return blocks;
    }

    public BlockButton(Context context, int x, int y, boolean mine) {
        super(context);
        this.x = x;
        this.y = y;
        this.mine = mine;
        this.flag = false;
        this.neighborMines = 0;
        setBackgroundResource(R.drawable.button_style);
    }

    public void toggleFlag(){
        if(flag){
            flag = false;
            flags++;
            setText("");
        } else {
            flag = true;
            flags--;
            setText("+");
        }
    }

    public boolean breakBlock() {
        if(flag)  //깃발이 있으면 열지 못함
            return false;
        if(!isClickable())
            return false;
        setClickable(false);
        setEnabled(false);
//        setBackgroundColor(Color.parseColor("#808080"));
        blocks--;
        if(mine){
            setText("*"); // 지뢰 표현
            return true;
        }
        else{
            //represent the uncovered block
            //여기서 이웃들까지 열어야할 듯

            if(neighborMines == 0){
                //여기서 재귀함수 구현하기..?
//                MainActivity.buttons[x-1][y-1].breakBlock(); // 대각선 왼쪽 아래
//                MainActivity.buttons[x+1][y-1].breakBlock(); // 대각선 오른쪽 아래
//                MainActivity.buttons[x-1][y+1].breakBlock(); // 대각선 왼쪽 위
//                MainActivity.buttons[x+1][y+1].breakBlock(); // 대각선 오른쪽 위
//                MainActivity.buttons[x][y-1].breakBlock(); // 아래
//                MainActivity.buttons[x-1][y].breakBlock(); // 왼쪽
//                MainActivity.buttons[x][y+1].breakBlock(); // 위
//                MainActivity.buttons[x+1][y].breakBlock(); // 오른쪽

                if(x>0 && y>0){ // 대각선 왼쪽 아래
                    MainActivity.buttons[x-1][y-1].breakBlock();
                } if(x<8 && y<8){ // 대각선 오른쪽 위
                    MainActivity.buttons[x+1][y+1].breakBlock();
                } if(x>0 && y<8){ // 대각선 왼쪽 위
                    MainActivity.buttons[x-1][y+1].breakBlock();
                } if(y>0 && x<8){ // 대각선 오른쪽 아래
                    MainActivity.buttons[x+1][y-1].breakBlock();
                } if(x<8) { // 오른쪽
                    MainActivity.buttons[x+1][y].breakBlock();
                } if(x>0){ // 왼쪽
                    MainActivity.buttons[x-1][y].breakBlock();
                } if(y<8){ // 위
                    MainActivity.buttons[x][y+1].breakBlock();
                } if(y>0){ // 아래
                    MainActivity.buttons[x][y-1].breakBlock();
                }
            }
            else {
                setText(String.valueOf(neighborMines)); //근처 지뢰 수 표현
            }
            return false;
        }
    }

    public void neighborMines(int neighborMines){
        this.neighborMines = neighborMines;
    }

    public void open(){
//        if(!isClickable())
//            return;
//        setClickable(false);
//        setBackgroundColor(Color.parseColor("#FFFFFF"));
//        setText(String.valueOf(neighborMines)); //근처 지뢰 수 표현

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                MainActivity.buttons[i][j].flag = false;
                MainActivity.buttons[i][j].breakBlock();
            }
        }

//        if(x>0 && y>0){ // 대각선 왼쪽 아래
//            MainActivity.buttons[x-1][y-1].open();
//        } if(x<8 && y<8){ // 대각선 오른쪽 위
//            MainActivity.buttons[x+1][y+1].open();
//        } if(x>0 && y<8){ // 대각선 왼쪽 위
//            MainActivity.buttons[x-1][y+1].open();
//        } if(y>0 && x<8){ // 대각선 오른쪽 아래
//            MainActivity.buttons[x+1][y-1].open();
//        } if(x<8) { // 오른쪽
//            MainActivity.buttons[x+1][y].open();
//        } if(x>0){ // 왼쪽
//            MainActivity.buttons[x-1][y].open();
//        } if(y<8){ // 위
//            MainActivity.buttons[x][y+1].open();
//        } if(y>0){ // 아래
//            MainActivity.buttons[x][y-1].open();
//        }

    }
}
