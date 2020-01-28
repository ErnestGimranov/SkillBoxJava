public class Main {
    public static void main(String[] args) {
        int n = 9;
        String cross[][] = new String[n][n];
        for (int i = 0; i < cross.length; i++){
            for (int j = 0; j < cross[i].length; j++){
                if (i == j || (i + j) == (n - 1)){
                    cross[i][j] = "X";
                }else{
                    cross[i][j] = " ";
                }
                System.out.print(cross[i][j]);
            }
            System.out.println();
        }
    }
}
