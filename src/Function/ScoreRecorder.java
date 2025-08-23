
package Function;

import java.io.*;
import java.util.Arrays;

/*��¼����*/
public class ScoreRecorder {
    private static final String SCOREFILE = "data/score.txt";  //�÷ּ�¼�ļ�
    private static int scores[] = new int[3];                  //��ǰ�÷����ǰ����
    
    //������ʼ��
    public static void init() {
        File f = new File(SCOREFILE);  // ����һ����¼�ļ�
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(f);                 // �ļ��ֽ�������
            isr = new InputStreamReader(fis);             // �ֽ���ת�ַ���
            br = new BufferedReader(isr);                 // �����ַ���
            String value = br.readLine();                 // ��ȡһ��
            if (!(value == null || "".equals(value))) {   
                String vs[] = value.split(",");           // �ָ��ַ���
                if (vs.length < 3) {                      // ����ָ���С��3
                    Arrays.fill(scores, 0);               // �������0
                } else {
                    for (int i = 0; i < 3; i++) {
                        scores[i] = Integer.parseInt(vs[i]);// ����¼�ļ��е�ֵ������ǰ��������
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// ���ιر���
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //�������
    public static void saveScore() {
        String value = scores[0] + "," + scores[1] + "," + scores[2]; // ƴ�ӵ÷�����
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(SCOREFILE);    //�ļ��ֽ������
            osw = new OutputStreamWriter(fos);        // �ֽ���ת�ַ���
            bw = new BufferedWriter(osw);             // �����ַ���
            bw.write(value);                          // д��ƴ�Ӻ���ַ���
            bw.flush();                               // �ַ���ˢ��
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {                                   //�ر���
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //�·���д�볤��Ϊ4������Ȼ���ٽ������򣬱���
    static public void addNewScore(int score) {
        int tmp[] = Arrays.copyOf(scores, 4);    //�ڵ÷����������ϴ���һ������Ϊ4����ʱ����
        tmp[3] = score;                          // ���·�����ֵ�����ĸ�Ԫ��
        Arrays.sort(tmp);                        // ��ʱ������������
        scores = Arrays.copyOfRange(tmp, 1, 4);  // ��������Ԫ�ظ�ֵ���÷�����
    }

    //��ȡ����
    static public int[] getScores() {
        return scores;
    }

}
