package com.example.tunetown;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class musicFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private int currentSong = 0;
    private final int[] songs = {
            R.raw.dyw,R.raw.khajrare, R.raw.kushi, R.raw.nyc, R.raw.heyhu,
            R.raw.just, R.raw.snow, R.raw.saka, R.raw.peelings, R.raw.tyb
    };
    private final String[] songTitles = {
            "Don't you want me..", "Khajra re..", "Kushi Kalthumbide..", "New York City..", "Hey Hu..",
            "Just math mathalli..", "Snow..", "Saka saka..", "Peeling (Pushpa2)..", "Take your Breath away.."
    };

    private  final int[] SongsImg={
            R.drawable.dyw ,R.drawable.meri,R.drawable.k2,R.drawable.nc,R.drawable.k1,
            R.drawable.justmathali,R.drawable.snow,R.drawable.saka,R.drawable.peelings, R.drawable.tyb
    };
    private TextView songTitleView;
    private ImageView SongsImgView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        songTitleView = view.findViewById(R.id.song_title);
        SongsImgView = view.findViewById(R.id.gifImageView);
        ImageView Forward = view.findViewById(R.id.image_forward);
        ImageView Backward = view.findViewById(R.id.image_backward);
        ImageView Play = view.findViewById(R.id.image_play);
        ImageView Pause = view.findViewById(R.id.image_pause);


        updateSong();
        Forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation ani = AnimationUtils.loadAnimation(requireContext(), R.anim.fade);
                SongsImgView.startAnimation(ani);
                songTitleView.startAnimation(ani);
                playSong(1);
            }
        });

        Backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation ani = AnimationUtils.loadAnimation(requireContext(), R.anim.fade);
                SongsImgView.startAnimation(ani);
                songTitleView.startAnimation(ani);
                playSong(-1);
            }
        });





        Play.setOnClickListener(v -> {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

        Pause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        return view;
    }

    private void playSong(int direction) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        currentSong = (currentSong + direction + songs.length) % songs.length;
        updateSong();
    }

    private void updateSong() {

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(requireContext(), songs[currentSong]);
        songTitleView.setText(songTitles[currentSong]);
        SongsImgView.setImageResource(SongsImg[currentSong]);
        mediaPlayer.setOnCompletionListener(mp -> playSong(1));

        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
