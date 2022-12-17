package com.froster;

import com.froster.utils.AudioPlayer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var t = AudioPlayer.playBgm("./majSoul.wav");
        Thread.sleep(2000);
        t.stop();
    }
}