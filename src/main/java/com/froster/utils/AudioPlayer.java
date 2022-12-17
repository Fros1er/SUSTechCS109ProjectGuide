package com.froster.utils;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

// From https://odoepner.wordpress.com/2013/07/19/play-mp3-or-ogg-using-javax-sound-sampled-mp3spi-vorbisspi/
public class AudioPlayer {

    /**
     * play and loop bgm
     */

    private volatile boolean isCanceled = false;

    private Thread currentThread = null;

    public static AudioPlayer playBgm(String path) {
        AudioPlayer player = new AudioPlayer();
        player.currentThread = new Thread(() -> {
            while (!player.isCanceled) {
                player.play(path);
            }
        });
        player.currentThread.start();
        return player;
    }

    /**
     * play sound
     */
    public static AudioPlayer playSound(String path) {
        AudioPlayer player = new AudioPlayer();
        player.currentThread = new Thread(() -> player.play(path));
        player.currentThread.start();
        return player;
    }

    public void stop() {
        this.isCanceled = true;
    }

    public Thread getCurrentThread() {
        return currentThread;
    }

    public void play(String filePath) {
        final File file = new File(filePath);

        try (final AudioInputStream in = getAudioInputStream(file)) {

            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final Info info = new Info(SourceDataLine.class, outFormat);

            try (final SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
                if (line != null) {
                    line.open(outFormat);
                    line.start();
                    stream(getAudioInputStream(outFormat, in), line);
                    line.drain();
                    line.stop();
                }
            }

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();

        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private void stream(AudioInputStream in, SourceDataLine line) throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1 && !this.isCanceled; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }
}
