import java.util.*;

public class Main{
    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();
    public static void main(String[] args) {
        char[][] x = new char[3][3];
        boolean gameActive = true;
        while (true) {
            int cpumove = cpuMove();
            place(x, cpumove, "Cpu");
            printTheBoard(x);
            String result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }
            Scanner sc = new Scanner(System.in);
            if(playerPosition.isEmpty() && cpuPosition.isEmpty()) {
                System.out.println("Your move.Enter a number between 1-9");
            }else{
                System.out.println("Enter a number between 1-9 except" + getOccupiedPositions());
            }
            int move = sc.nextInt();
            while (move < 1 || move > 9 || playerPosition.contains(move) || cpuPosition.contains(move)) {
                System.out.println("This position is invalid.try again");
                move = sc.nextInt();
            }
            System.out.println(move);
            place(x, move, "player");
            result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                break;
            }
        }
    }
    public static void printTheBoard(char[][] t){
        for(char[] y : t){
            for(char z : y){
                System.out.print(z + " ");
            }
            System.out.println();
        }
    }
    public static void place(char[][] t, int move,String user) {
        char Symbol = ' ';
        if (user.equals("player")) {
            Symbol = 'X';
            playerPosition.add(move);
        } else if (user.equals("Cpu")) {
            Symbol = 'O';
            cpuPosition.add(move);
        }
            switch (move) {
                case 1:
                    t[0][0] = Symbol;
                    break;
                case 2:
                    t[0][1] = Symbol;
                    break;
                case 3:
                    t[0][2] = Symbol;
                    break;
                case 4:
                    t[1][0] = Symbol;
                    break;
                case 5:
                    t[1][1] = Symbol;
                    break;
                case 6:
                    t[1][2] = Symbol;
                    break;
                case 7:
                    t[2][0] = Symbol;
                    break;
                case 8:
                    t[2][1] = Symbol;
                    break;

                case 9:
                    t[2][2] = Symbol;
                    break;
                default:
                    break;
            }
        }
    public static int cpuMove() {
        Random rand = new Random();
        int cpuMove = rand.nextInt(9) + 1;

        while (playerPosition.contains(cpuMove) || cpuPosition.contains(cpuMove)) {
            cpuMove = rand.nextInt(9) + 1;
        }
        cpuPosition.add(cpuMove);
        return cpuMove;
        }

           public static String checkWinner(){
        List<Integer> row1 = Arrays.asList(1,2,3);
        List<Integer> row2 = Arrays.asList(4,5,6);
        List<Integer> row3 = Arrays.asList(7,8,9);
        List<Integer> col1 = Arrays.asList(1,4,7);
        List<Integer> col2 = Arrays.asList(2,5,8);
        List<Integer> col3 = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);
        List<List> winnningCond = new ArrayList<List>();
        winnningCond.add(row1);
        winnningCond.add(row2);
        winnningCond.add(row3);
        winnningCond.add(col1);
        winnningCond.add(col2);
        winnningCond.add(col3);
        winnningCond.add(cross1);
        winnningCond.add(cross2);


        for(List l : winnningCond) {
            if (playerPosition.containsAll(l)) {
                return "player won";
            } else if(cpuPosition.containsAll(l)) {
                return "cpu won";
            }
        }
               if (playerPosition.size() + cpuPosition.size() == 9) {
                   List<Integer> allPositions = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
                   for (int position : allPositions) {
                       if (!playerPosition.contains(position) && !cpuPosition.contains(position)) {
                           return "";
                       }
                   }
                   return "The game is a draw";
               }
        return "";
        }
    public static String getOccupiedPositions() {
        List<Integer> occupiedPositions = new ArrayList<>(playerPosition);
        occupiedPositions.addAll(cpuPosition);
        return occupiedPositions.toString();
    }
}
