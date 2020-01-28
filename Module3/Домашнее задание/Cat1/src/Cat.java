
public class Cat
{

    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;
    public static final short COUNT_EYES = 2;       //Константы

    private double originWeight;
    private double weight;

    private double food = 0;        //Масса еды
    private static short count = 0;     //Кол-во кошек
    private CatColor color;
    private double foodEaten = 0;


    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        count ++;
    }

    public Cat(double weight)
    {
        this();
        this.weight = weight;
        originWeight = weight;      //Новый конструктор
    }

    public static Cat copy(Cat original){
        Cat newCat = new Cat(original.weight);
        newCat.foodEaten = original.foodEaten;
        newCat.color = original.color;
        return newCat;
    }


    public void setColor(CatColor color){
        this.color = color;
    }

    public CatColor getColor(){
        return color;
    }


    public String meow()
    {
        if (this.getStatus() == "Dead" || this.getStatus() == "Exploded"){
            return "Can't do this";
        }
        weight = weight - 1;
        System.out.println("Meow");
        if(weight < MIN_WEIGHT) {
            count--;
            return "Dead";
        }
        return null;

    }

    public String feed(Double amount)
    {
        if (this.getStatus() == "Dead" || this.getStatus() == "Exploded"){
            return "Can't eat";
        }
        weight = weight + amount;
        food += amount;
        this.foodEaten += amount;
        if(weight > MAX_WEIGHT) {
            count--;
            return "Exploded";
        }
        return null;
    }

    public String goToilet(){
        if (this.getStatus() == "Dead" || this.getStatus() == "Exploded"){
            return "Can't go to the toilet";
        }
        weight -= 150;
        System.out.println("Poop");
        if(weight < MIN_WEIGHT) {
            count--;
            return "Dead";
        }
        return null;       //Поход в туалет
    }

    public double getMassFoodEaten(){
        return this.foodEaten;
    }

    public double getAllMassFoodEaten(){
        return food;
    }

    public String drink(Double amount)
    {
        if (this.getStatus() == "Dead" || this.getStatus() == "Exploded"){
            return "Can't drink";
        }
        weight = weight + amount;
        if(weight > MAX_WEIGHT) {
            count--;
            return "Exploded";
        }
        return null;
    }

    public static short getCount() {
        return count;
    }


    public Double getWeight()
    {
        return weight;
    }

    public Double getMaxWeight()
    {
        return MAX_WEIGHT;
    }

    public Double getMinWeight()
    {
        return MIN_WEIGHT;
    }

    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
}