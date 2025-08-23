package Function;

import java.io.FileNotFoundException;

public class Sound {
    static final String DIR = "music/";// 音乐文件夹
    static final String BACKGROUD = "BGM.wav";// 背景音乐
     //播放背景音乐
    static public void backgroud() {
        play(DIR + BACKGROUD, true);// 循环播放背景音乐
    }
    //循环播放
    private static void play(String file, boolean circulate) {
        try {
            MusicPlay player = new MusicPlay(file, circulate);
            player.play();// 播放器开始播放
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
