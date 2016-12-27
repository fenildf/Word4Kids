package com.daming.wordkids.cardslideview;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daming.wordkids.R;
import com.daming.wordkids.bean.Card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 卡片Fragment
 *
 * @author xmuSistone
 */
@SuppressLint({"HandlerLeak", "NewApi", "InflateParams"})
public class CardFragment extends Fragment {
    private static final String TAG = "Dashu";
    private CardSlidePanel.CardSwitchListener cardSwitchListener;
    private CardSlidePanel slidePanel;
    private MediaPlayer player;
    private boolean isPlaying = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.card_layout, null);
        player = new MediaPlayer();
        player.setOnPreparedListener(onPreparedListener);
        player.setOnCompletionListener(onCompletionListener);
        initView(rootView);
        return rootView;
    }

    public void playAudio(String url) {
        if (isPlaying)
            return;
        try {
            Log.i(TAG, "playAudio: play url: " + url);
            isPlaying = true;
            player.setDataSource(url);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            player.start();
        }
    };
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.i(TAG, "onCompletion: i'm playing over");
            player.reset();
            isPlaying = false;
        }
    };

    private void initView(View rootView) {
        slidePanel = (CardSlidePanel) rootView
                .findViewById(R.id.image_slide_panel);
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(Card item) {
                Log.d("Dashu", "正在显示-" + item.word);
                playAudio(item.audio);
            }

            @Override
            public void onCardVanish(Card item, int type) {
                Log.d("Dashu", "正在消失-" + item.word + " 消失type=" + type);
                if (isPlaying) {
                    Log.i(TAG, "onCardVanish: Is playing and reset the player");
                    player.reset();
                    isPlaying = false;
                }
            }

            @Override
            public void onItemClick(Card item) {
                Log.d("Dashu", "卡片点击-" + item.word);
                playAudio(item.audio);
            }

            @Override
            public void onNoData() {
                Log.d(TAG, "onNoData: 没有数据啦。。");
            }
        };
        slidePanel.setCardSwitchListener(cardSwitchListener);
    }

    public void setCardList(List<Card> list) {
        Log.i(TAG, "setCardList: set card list " + slidePanel.getId());
        if (isPlaying) {
            Log.i(TAG, "setCardList: Is playing and reset the player");
            player.reset();
            isPlaying = false;
        }
        slidePanel.fillData(list);
    }

}
