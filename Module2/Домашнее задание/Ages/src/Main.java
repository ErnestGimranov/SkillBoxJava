public class Main
{
    public static void main(String[] args) {

        int petyaAge = 14, vasyaAge = 28, vanyaAge = 56;

        int min = -1, middle = -1, max = -1;

        if (vanyaAge > vasyaAge && vanyaAge > petyaAge) {
            max = vanyaAge;
            if(vasyaAge > petyaAge){
                middle = vasyaAge;
                min = petyaAge;
            }else {
                middle = petyaAge;
                min = vasyaAge;
            }
        } else if (vasyaAge > petyaAge && vasyaAge > vanyaAge) {
            max = vasyaAge;
            if (petyaAge > vasyaAge){
                middle = petyaAge;
                min = vanyaAge;
            } else {
                middle = vanyaAge;
                min = vanyaAge;
            }
        } else {
            max = petyaAge;
            if (vasyaAge > vanyaAge){
                middle = vasyaAge;
                min = vanyaAge;
            }else {
                middle = vanyaAge;
                min = vasyaAge;
            }
        }

        System.out.printf("Max Age = %d Middle Age = %d Min Age = %d", max, middle, min);
    }
}
