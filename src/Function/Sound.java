package Function;

import java.io.FileNotFoundException;

public class Sound {
    static final String DIR = "music/";// �����ļ���
    static final String BACKGROUD = "BGM.wav";// ��������
     //���ű�������
    static public void backgroud() {
        play(DIR + BACKGROUD, true);// ѭ�����ű�������
    }
    //ѭ������
    private static void play(String file, boolean circulate) {
        try {
            MusicPlay player = new MusicPlay(file, circulate);
            player.play();// ��������ʼ����
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
