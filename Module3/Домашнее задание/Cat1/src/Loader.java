
public class Loader
{
    public static void main(String[] args)
    {
        System.out.println("Eyes count = " + Cat.COUNT_EYES);

        Cat vasya = new Cat();      //Создание одной кошки

        System.out.println("Count = " + Cat.getCount());        //Вывод колличество кошек

        Cat manya = new Cat();
        Cat kesha = new Cat(5600.0);
        Cat petka = new Cat();
        Cat misha = new Cat();        //Создание кошки через новый конструктор
        Cat cat = new Cat();        //Создание остальных кошек

        System.out.println("Вес кота = " + kesha.getWeight());
        kesha.feed(1000.);
        System.out.println("Вес кота = " + kesha.getWeight());


        manya.setColor(CatColor.WHITE);
        System.out.println("Цвет кошки " + manya.getColor());

        System.out.println("Count = " + Cat.getCount());        //Вывод колличества всех кошек

        System.out.println("Mass food eaten = " + cat.getAllMassFoodEaten());
        cat.feed(100.);
        System.out.println("Mass food eaten = " + cat.getAllMassFoodEaten());
        cat.feed(120.);
        System.out.println("Mass food eaten = " + cat.getAllMassFoodEaten());        //Кормление кошек

        System.out.println(vasya.getWeight());
        vasya.goToilet();
        System.out.println(vasya.getWeight());      //Кошка сходила в туалет

        System.out.println(vasya.getWeight() + "\n");

        manya.meow();

        System.out.println(kesha.getWeight() + "\n");

        kesha.drink(1.);

        System.out.println(kesha.getWeight() + "\n");

        System.out.println(petka.getWeight() + "\n");

        petka.feed(2.);

        System.out.println(petka.getWeight() + "\n");

        System.out.println(misha.getStatus() + "\n");       //Кошки помяукали, попили, поели

        while (vasya.getWeight() > vasya.getMinWeight()){
            vasya.meow();
        }       //Кошка замяукалась до смерти

        System.out.println("Count = " + Cat.getCount());        //Вывод колличества кошек после смерти одной

        System.out.println(vasya.getStatus());

        while (misha.getWeight() < misha.getMaxWeight()){
            misha.feed(100.);
        }       //Кошка переела и взорвалась

        System.out.println(misha.getStatus());

        System.out.println("Count = " + Cat.getCount());        //Вывод колличства оставшихся кошек

        Cat copyManya = Cat.copy(manya);

        manya.feed(13.);

        System.out.println("Вес кошки = " + manya.getWeight() + "\nМасса съеденной еды = " + manya.getMassFoodEaten() + "\nЦвет кошки - " + manya.getColor());
        System.out.println("Count = " + Cat.getCount());        //Вывод колличства оставшихся кошек
        System.out.println("Вес копии кошки = " + copyManya.getWeight() + "\nМасса съеденной еды копии = " + manya.getMassFoodEaten() + "\nЦвет копии кошки - " + manya.getColor());

    }
}