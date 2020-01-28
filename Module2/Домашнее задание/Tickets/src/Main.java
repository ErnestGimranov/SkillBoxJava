public class Main {

    public static void main(String[] args) {
        System.out.println("Билеты с 200000 по 210000:");
        System.out.println();
        for (var i = 200000; i <= 210000; i++){
            System.out.print(i + "  ");
            if(i % 100 == 99)
                System.out.println();
        }
        System.out.println();
        System.out.println("============================================================================================");
        System.out.println("Билеты с 220000 по 235000:");
        System.out.println();
        var i = 220000;
        while (i <= 235000){
            System.out.print(i + "  ");
            if(i % 100 == 99)
                System.out.println();
            i++;
        }
    }

}
