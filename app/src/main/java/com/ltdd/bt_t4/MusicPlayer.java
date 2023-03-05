package com.ltdd.bt_t4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MusicPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
    }

    public void bindEventForMediaPlayer(){
        ViewHolder viewHolder = new ViewHolder(
                findViewById(R.id.btnPrev),
                findViewById(R.id.btnNext),
                findViewById(R.id.btnPlay),
                findViewById(R.id.btnPause),
                findViewById(R.id.txtMediaName),
                findViewById(R.id.txtS),
                findViewById(R.id.txtE)
        );


    }

    static class ViewHolder{
        ImageButton pre, next, play, pause;
        TextView name, start, end;

        public ViewHolder(ImageButton pre, ImageButton next, ImageButton play, ImageButton pause, TextView name, TextView start, TextView end) {
            this.pre = pre;
            this.next = next;
            this.play = play;
            this.pause = pause;
            this.name = name;
            this.start = start;
            this.end = end;
        }
    }
}