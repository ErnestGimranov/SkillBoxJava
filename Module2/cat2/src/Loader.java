
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();

        cat.massFoodEaten();
        cat.feed(100.);
        cat.massFoodEaten();
        cat.feed(120.);
        cat.massFoodEaten();

        Cat vasya = new Cat();
        System.out.println(vasya.getWeight());
        vasya.goToilet();
        System.out.println(vasya.getWeight());

        System.out.println(cat.getStatus());
    }
}