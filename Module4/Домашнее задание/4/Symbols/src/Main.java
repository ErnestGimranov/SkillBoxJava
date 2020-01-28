public class Main {
    public static void main(String[] args) {
        String engAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIKLMNOPQRSTUVWXYZ";
        String rusAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁХЖИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        for(int i = 0; i < rusAlphabet.length(); i++){
            System.out.print(rusAlphabet.charAt(i) + " = " + (int) rusAlphabet.charAt(i) + "\t");
            if (i < engAlphabet.length()) {
                System.out.print(engAlphabet.charAt(i) + " = " + (int) engAlphabet.charAt(i) + "\n");
            }else {
                System.out.println();
            }
        }
    }
}
