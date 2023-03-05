package com.ltdd.bt_t4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MusicPlayer extends AppCompatActivity {
    private static final int CHOOSE_FILE_REQUESTCODE = 8777;
    private static final int PICKFILE_RESULT_CODE = 8778;
    private Song song = new Song();
    MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    ViewHolder viewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        viewHolder = new ViewHolder(
                findViewById(R.id.btnPrev),
                findViewById(R.id.btnNext),
                findViewById(R.id.btnPlay),
                findViewById(R.id.btnPause),
                findViewById(R.id.txtMediaName),
                findViewById(R.id.txtS),
                findViewById(R.id.txtE),
                findViewById(R.id.seekBar)
        );
        bindEventForMediaPlayer();
    }
    public void bindEventForMediaPlayer(){

//        try {
//            Button btnAddF = findViewById(R.id.button);
//            btnAddF.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
//                    chooseFile.setType("*/*");
//                    chooseFile = Intent.createChooser(chooseFile, "Choose a file");
//                    startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);
//                    onActivityResult(CHOOSE_FILE_REQUESTCODE, PICKFILE_RESULT_CODE, chooseFile);
//                    Uri uri = chooseFile.getData();
//                    Log.i("System", uri.getPath());
//                }
//            });
            mediaPlayer = MediaPlayer.create(this, R.raw.mbyvdd);
            viewHolder.name.setText("Một bước yêu vạn dặm đau");
            viewHolder.prog.setClickable(false);
            viewHolder.pause.setEnabled(false);
            viewHolder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Playing Audio", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                    song.eTime = mediaPlayer.getDuration();
                    song.sTime = mediaPlayer.getCurrentPosition();
                    if (song.oTime == 0){
                        viewHolder.prog.setMax(song.eTime);
                        song.oTime = 1;
                    }
                    viewHolder.start.setText(String.format("%d min, %d sec",
                            TimeUnit.MILLISECONDS.toMinutes(song.sTime),
                            TimeUnit.MILLISECONDS.toSeconds(song.sTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(song.sTime))
                    ));
                    viewHolder.end.setText(String.format("%d min, %d sec",
                            TimeUnit.MILLISECONDS.toMinutes(song.eTime),
                            TimeUnit.MILLISECONDS.toSeconds(song.eTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(song.eTime))
                    ));
                    viewHolder.prog.setProgress(song.sTime);
                    handler.postDelayed(UpdateSongTime, 100);
                    viewHolder.pause.setEnabled(true);
                    viewHolder.play.setEnabled(false);
                }
            });
            viewHolder.pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();
                    viewHolder.pause.setEnabled(false);
                    viewHolder.play.setEnabled(true);
                    Toast.makeText(getApplicationContext(),"Pausing Audio",
                            Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((song.sTime + song.fTime) <= song.eTime){
                        song.sTime = song.sTime + song.fTime;
                        mediaPlayer.seekTo(song.sTime);
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Cannot jump forward 5 second",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                    if (!viewHolder.play.isEnabled()){
                        viewHolder.play.setEnabled(true);
                    }
                }
            });

            viewHolder.prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if((song.sTime - song.bTime) > 0){
                        song.sTime = song.sTime - song.bTime;
                        mediaPlayer.seekTo(song.sTime);
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Cannot jump backward 5 seconds",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                    if(!viewHolder.play.isEnabled()){
                        viewHolder.play.setEnabled(true);
                    }
                }
            });
//        }catch (Exception ex){
//            Log.e("System", ex.getMessage());
//        }

    }
    class Song{
        int oTime, sTime, eTime, fTime, bTime;
        public Song(){
            oTime = sTime = eTime = 0;
            fTime = bTime = 5000;
        }
    }
    static class ViewHolder{
        ImageButton prev, next, play, pause;
        TextView name, start, end;
        SeekBar prog;

        public ViewHolder(ImageButton prev, ImageButton next, ImageButton play, ImageButton pause, TextView name, TextView start, TextView end, SeekBar prog) {
            this.prev = prev;
            this.next = next;
            this.play = play;
            this.pause = pause;
            this.name = name;
            this.start = start;
            this.end = end;
            this.prog = prog;
        }
    }
    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            song.sTime = mediaPlayer.getCurrentPosition();
            viewHolder.start.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes(song.sTime),
                    TimeUnit.MILLISECONDS.toSeconds(song.sTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(song.sTime))
            ));
            viewHolder.prog.setProgress(song.sTime);
            handler.postDelayed(this, 100);
        }
    };
}