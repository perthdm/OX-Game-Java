import java.util.Scanner;

public class Game {
    private static OX ox;
    private static Scanner kb = new Scanner(System.in);
    private static int col;
    private static int row;


    public static void main(String args[] ){
        ox = new OX();

        while (true){
            System.out.print(ox.getTableString());
            input();

            if(ox.put(col,row) == true ){
                ox.switchPlayer();
            }

            if(ox.checkWin(col,row)){
                break;
            }


        }


    }

    public static void input(){
        System.out.print(ox.getCurrentPlayer()+ " (Col): ");
        col = kb.nextInt();
        System.out.print(ox.getCurrentPlayer()+ " (Row): ");
        row = kb.nextInt();
    }






}
