import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameBoard extends JFrame {
    private static final int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE];
    private String[][] piecesArray;
    
    public GameBoard() {
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        piecesArray = new String[][] {
            {"B_Bishop.png", "3"}, {"B_Bishop.png", "6"}, {"B_King.png", "5"},
            {"B_Knight.png", "2"}, {"B_Knight.png", "7"}, {"B_Pawn.png", "9"},
            {"B_Pawn.png", "10"}, {"B_Pawn.png", "11"}, {"B_Pawn.png", "12"},
            {"B_Pawn.png", "13"}, {"B_Pawn.png", "14"}, {"B_Pawn.png", "15"},
            {"B_Pawn.png", "16"}, {"B_Queen.png", "4"}, {"B_Rook.png", "1"},
            {"B_Rook.png", "8"}, {"W_Bishop.png", "59"}, {"W_Bishop.png", "62"},
            {"W_King.png", "60"}, {"W_Knight.png", "58"}, {"W_Knight.png", "63"},
            {"W_Pawn.png", "49"}, {"W_Pawn.png", "50"}, {"W_Pawn.png", "51"},
            {"W_Pawn.png", "52"}, {"W_Pawn.png", "53"}, {"W_Pawn.png", "54"},
            {"W_Pawn.png", "55"}, {"W_Pawn.png", "56"}, {"W_Queen.png", "61"},
            {"W_Rook.png", "57"}, {"W_Rook.png", "64"}
        };

        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }

        //Arrays.sort(piecesArray, (a, b) -> a[0].compareTo(b[0]));
        initializeBoard();
        System.out.println(piecesArray);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mergeSort(piecesArray, 0, piecesArray.length - 1);
                initializeBoard();
            }
        });
    }

    private void initializeBoard() {
        getContentPane().removeAll();
        setLayout(new GridLayout(SIZE, SIZE));
        int index = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel(new BorderLayout());
                squares[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                
                if (row<2 ||row>5) {
                    ImageIcon icon = new ImageIcon(piecesArray[index][0]);
                    Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(piecesArray[index][1], SwingConstants.CENTER);

                    squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    squares[row][col].add(textLabel, BorderLayout.SOUTH);
                    index++;
                }
                
                add(squares[row][col]);
            }
        }
        revalidate();
        repaint();
    }

    private void mergeSort(String[][] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private void merge(String[][] array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        
        String[][] leftArray = new String[leftSize][2];
        String[][] rightArray = new String[rightSize][2];
        
        for (int i = 0; i < leftSize; i++)
            leftArray[i] = array[left + i];
        for (int j = 0; j < rightSize; j++)
            rightArray[j] = array[middle + 1 + j];
        
        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (Integer.parseInt(leftArray[i][1]) <= Integer.parseInt(rightArray[j][1])) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}
