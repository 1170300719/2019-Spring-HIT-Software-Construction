package P1;

import java.io.*;

public class MagicSquare {
    boolean isLegalMagicSquare(String fileName) {
        String filename = "src/P1/txt/" + fileName;
        File file = new File(filename);//�ļ�����
        String content = null;
        Long filelongth = file.length();
        byte[] filecontent = new byte[filelongth.intValue()];
        int cols = 0, rows = 0;
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            content = new String(filecontent, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + "UTF-8");//ʵʱ���
            e.printStackTrace();
        }
        String line[] = content.split("\n");
        rows = line.length;
        cols = rows;
        int nums[][] = new int[rows][rows];
        int sumRow[] = new int[rows];//���к�
        int sumCol[] = new int[cols];//���к�
        int sumDia[] = new int[2];//�Խ��ߺ�
        for (int i = 0; i < rows; i++) {
            String[] data = line[i].split("\t");
            if (data.length != rows) {
                    System.out.println("��Ϊ����");
                return false;
            }
            for (int j = 0; j < rows; j++) {
                try {
                    int num = Integer.valueOf(data[j]).intValue();
                    nums[i][j] = num;
                } catch (NumberFormatException e) {
                    System.out.println("���ڷǷ�����");
                    return false;
                }
                if (nums[i][j] <= 0) {
                    System.out.println("���ڸ���");
                    return false;
                }
                sumRow[i] += nums[i][j];
                sumCol[j] += nums[i][j];
                if (i == j) {
                    sumDia[0] += nums[i][j];
                }
                if (i + j + 1 == cols) {
                    sumDia[1] += nums[i][j];
                }
            }
        }
        if (sumDia[0] != sumDia[1]) {
            return false;
        }
        int sum = sumDia[0];
        for (int i = 0; i < rows; i++) {
            if (sumRow[i] != sum || sumCol[i] != sum)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        generateMagicSquare(5);
        MagicSquare magic = new MagicSquare();
        for (int i = 1; i <= 6; i++) {
            if (magic.isLegalMagicSquare(String.valueOf(i) + ".txt"))
                System.out.println(i + ":Yes");
            else {
                System.out.println(i + ":No");
            }
        }

    }

    public static boolean generateMagicSquare(int n) {
        try {
            int magic[][] = new int[n][n];
            int row = 0, col = n / 2, i, j, square = n * n;
            for (i = 1; i <= square; i++) {
                try {
                    magic[row][col] = i;//ÿ�θ�ֵһ������Ԫ�ز�������һ��λ��
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("����������ż��");
                    return false;
                }
                if (i % n == 0)  //������һ��λ��
                    row++;//������ú�һ����б��"��Ԫ�أ���ʼ��һ��б��
                else {
                    if (row == 0) //���Ԫ��λ�������˵�һ�У�������һ�п�ʼ
                        row = n - 1;
                    else
                        row--;//����������ȥ1
                    if (col == (n - 1))//���Ԫ��λ�����������һ�У���ӵ�һ�п�ʼ
                        col = 0;
                    else
                        col++;//������������һ
                }
            }
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++)
                    System.out.print(magic[i][j] + "\t");
                System.out.println();
            }//��������Ԫ��
            //�ļ�������֣������ɵľ���д��6.txt
            File writeName = new File("src/P1/txt/6.txt");
            try {
                writeName.createNewFile();
                FileWriter writer = new FileWriter(writeName);
                BufferedWriter out = new BufferedWriter(writer);
                for (i = 0; i < n; i++) {
                    for (j = 0; j < n; j++)
                        out.write(magic[i][j] + "\t");
                    out.write( "\n");
                }
                out.flush(); // �ѻ���������ѹ���ļ�
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            return true;
        } catch (
                NegativeArraySizeException e) {
            System.out.println("���������˸���");
            return false;
        }
    }
}
