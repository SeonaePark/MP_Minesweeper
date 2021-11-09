package com.example.firstapplication;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

class BlockButton extends AppCompatButton {
    int x,y;
    boolean mine;  // 지뢰인지
    boolean flag;  // 깃발인지
    int neighborMines;  //근처 지뢰 갯수
    static int flags = 0;  //남은 깃발
    static int blocks = 81;  //남은 블록

    public BlockButton(Context context, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
        this.mine = false;
        this.flag = false;
        this.neighborMines = 0;
//        this.flags = 0;
//        this.blocks = 81;
    }

    public void toggleFlag(){
        if(flag){
            flag = false;
            flags--;
            setText("");
        } else {
            flag = true;
            flags++;
            setText("+");
        }
    }

    public boolean breakBlock(View view) {
        if(flag)  //깃발이 있으면 열지 못함
            return false;
        setClickable(false);
        blocks--;
        if(mine){
            setText("mine"); // 지뢰 표현
            return true;
        }
        else{
            setText(neighborMines); //근처 지뢰 수 표현
            //represent the uncovered block
            //여기서 이웃들까지 열어야할 듯
            if(neighborMines == 0){
                //여기서 재귀함수 구현하기..?
            }
            return false;
        }
    }
}
