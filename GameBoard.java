import java.awt.*;
import javax.swing.*;

public class GameBoard extends JFrame {
    private static final int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE];
    private ImageIcon exampleIcon;
    public String[][] piecesArray;


    public GameBoard() {
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

       

        // create your 2d Array to store your image variables and assign positions
        // add your code here
        // this line of code initializes a new 2D Array of Strings the size of 1 row and 2 columns
        // your 2D array must be a minimum of 6 rows x 2 columns
        // you may add a row for every image if you'd like to have every square be a different color/image

        piecesArray = new String[32][2];
        piecesArray[0]= new String[] {"B_Bishop.png","26"};
        piecesArray[1]= new String[] {"B_Bishop.png","29"};
        piecesArray[2]= new String[] {"B_King.png","28"};
        piecesArray[3]= new String[] {"B_Knight.png","25"};
        piecesArray[4]= new String[] {"B_Knight.png","30"};
        for (int i = 5; i<5+8;i++){
            piecesArray[i]= new String[] {"B_Pawn.png",(10+i)+""};
        }
        piecesArray[13]= new String[] {"B_Queen.png","28"};
        piecesArray[14]= new String[] {"B_Rook.png","24"};
        piecesArray[15]= new String[] {"B_Rook.png","31"};


        piecesArray[16]= new String[] {"W_Bishop.png","2"};
        piecesArray[17]= new String[] {"W_Bishop.png","5"};
        piecesArray[18]= new String[] {"W_King.png","4"};
        piecesArray[19]= new String[] {"W_Knight.png","1"};
        piecesArray[20]= new String[] {"W_Knight.png","6"};
        for (int i = 21; i<21+8;i++){
            piecesArray[i]= new String[] {"W_Pawn.png",(i-5)+""};
        }
        piecesArray[29]= new String[] {"W_Queen.png","3"};
        piecesArray[30]= new String[] {"W_Rook.png","1"};
        piecesArray[31]= new String[] {"W_Rook.png","7"};
        //print the contents of your 2D array
        //this is a requirement to show your 2D array is not sorted at the beginning of your program

        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }

        exampleIcon = new ImageIcon(piecesArray[0][0]); // Load image file

        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel(new BorderLayout());

                // creates the checkered pattern with the two colors
                // you can add more colors or take away any you'd like
                
                if (row >= 2 && row <= 5) {
                    squares[row][col].setBackground(new Color(139, 69, 19)); // brown
                } else if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new Color(55, 255, 55)); //dark green
                } else {
                    squares[row][col].setBackground(new Color(200, 255, 200)); //lighter green
                }


                // this is where your sorting method will be called 
                // you will use the column 2 values to arrange your images to the board
                // be sure to sort them before you add them onto the board 
                // you will use a loop to add to your 2D Array, below is an example of how to add ONE image to ONE square
                
                // Adding an image to specific positions (e.g., first row)
                if (row == 0 && col==0) {
                    Image scaledImage = exampleIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(piecesArray[0][1], SwingConstants.CENTER);
                    squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    squares[row][col].add(textLabel, BorderLayout.SOUTH);
                }

                
                add(squares[row][col]);
            }
        }
    }


    // add your merge sort method here
    // add a comment to every line of code that describes what the line is accomplishing
    // your mergeSort method does not have to return any value

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}
