import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;
    private static final String  REGEX_EMAIL = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
    private static final String  REGEX_TELEPHONE = "^\\+7\\d{10}$";


    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {

        String[] components = data.split("\\s+");
        if (components.length != 4){
            throw new NotValidException("Неверный формат. Верный формат:\nadd Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        String name = components[0] + " " + components[1];
        if(!components[2].matches(REGEX_EMAIL)){
            throw new NotValidException("Введен не верный формат eMail");
        }
        if(!components[3].matches(REGEX_TELEPHONE)){
            throw new NotValidException("Введен не верный формат телефона");
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        if(!storage.containsKey(name)){
            throw new NotValidException("Вы пытаетесь удалить не существующего человека.");
        }
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}