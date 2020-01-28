public class Main {
    public static void main(String[] args) {
        String text = "The US and China are locked in a bitter trade battle. " +
                "US President Donald Trump has complained about China's trading practices since before he took office in 2016. " +
                "The US launched an investigation into Chinese trade policies in 2017. It imposed tariffs on billions of dollars worth of Chinese products last year, and Beijing retaliated in kind. " +
                "Both countries agreed to halt new trade tariffs in December to allow for talks. But hope for a deal faded, and further tit-for-tat tariffs were imposed. " +
                "What tariffs are in place? " +
                "Last year, the US imposed three rounds of tariffs on more than $250bn worth of Chinese goods. " +
                "The duties of up to 25% cover a wide range of products, from handbags to railway equipment. " +
                "China hit back by imposing tariffs ranging from 5% to 25% on $110bn of US products including chemicals, coal and medical equipment.";
        text = text.replaceAll("[^\\w[\\s]]","");
        String[] words = text.split("\\s");
        for (int i = 0; i < words.length; i++){
            System.out.println(words[i]);
        }
    }
}
