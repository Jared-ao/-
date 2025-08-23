package Function;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * ���ֲ�����
 * 
 */
public class MusicPlay implements Runnable {
    File soundFile;// �����ļ�
    Thread thread; // ���߳�
    boolean circulate;// �Ƿ�ѭ������

    /**
              * ���췽����Ĭ�ϲ�ѭ������
              *            �����ļ���������
    */
    public MusicPlay(String filepath) throws FileNotFoundException {
        this(filepath, false);
    }

    /* filepath �����ļ���������circulate�Ƿ�ѭ������*/
    public MusicPlay(String filepath, boolean circulate)
            throws FileNotFoundException {
        this.circulate = circulate;
        soundFile = new File(filepath);
        if (!soundFile.exists()) {// ����ļ�������
            throw new FileNotFoundException(filepath + "δ�ҵ�");
        }
    }

    //����
    public void play() {
    
    	thread = new Thread(this);// �����̶߳���
        thread.start();// �����߳�
    }

    //ֹͣ����
    @SuppressWarnings("deprecation")
	public void stop() {
        thread.stop();// ǿ�ƹر��߳�
    }

    //��дrun����
    public void run() {
        byte[] auBuffer = new byte[1024 * 128];                                  //����128k������
        do {
            AudioInputStream audioInputStream = null;                           // ������Ƶ����������
            SourceDataLine auline = null;                                       // ��Ƶ��Դ������
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);      // �������ļ��л�ȡ��Ƶ������
                AudioFormat format = audioInputStream.getFormat(); // ��ȡ��Ƶ��ʽ
                DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);// ����Դ���������ͺ�ָ����Ƶ��ʽ���������ж���
                auline = (SourceDataLine) AudioSystem.getLine(info); // ������Ƶϵͳ������ָ�� Line.Info �����е�����ƥ����У���ת��ΪԴ�����ж���
                auline.open(format);        // ����ָ����ʽ��Դ������
                auline.start();             // Դ�����п�����д�
                int byteCount = 0;          // ��¼��Ƶ�������������ֽ���
                while (byteCount != -1){    // �����Ƶ�������ж�ȡ���ֽ�����Ϊ-1    
                    byteCount = audioInputStream.read(auBuffer, 0,auBuffer.length);// ����Ƶ�������ж���128K������
                    if (byteCount >= 0) {                      // ���������Ч����
                        auline.write(auBuffer, 0, byteCount);  //����Ч����д����������
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                auline.drain();//���������
                auline.close();//�ر�������
            }
        } while (circulate);//����ѭ����־�ж��Ƿ�ѭ������
    }
}