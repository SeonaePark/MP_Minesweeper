package com.example.firstapplication;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

class BlockButton extends AppCompatButton {
    int x,y;
    boolean mine;
    boolean flag;
    int neighborMines;
    static int flags;
    static int blocks;

    public BlockButton(Context context, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
        this.mine = false;
        this.flag = false;
        this.neighborMines = 0;
        this.flags = 0;
        this.blocks = 81;
    }

    public void toggleFlag(){
        if(flag){
            flag = false;
            flags--;
        } else {
            flag = true;
            flags++;
        }
    }

    public void breakBlock(View view) {
    }
}
