public class MyCollectionTest {
    public static void main(String[] args) {
        MyCollection<Person> persons = new MyCollection<>(10);
        Person p3 = new Person("xx", 3);
        persons.add(new Person("me", 36));
        persons.add(new Person("x", 22));
        persons.add(p3);
        System.out.println(persons.size());
        for (Person p : persons) {
            System.out.println(p);
        }

        System.out.println(persons.contains(p3));
        Object[] array = persons.toArray();
        for (Object o : array) {
            System.out.println(o);
        }

        persons.remove(p3);
        System.out.println(persons.size());

        Person[] ps=new Person[2];
        persons.toArray(ps);
        for(Person p:ps){
            System.out.println(p);
        }
    }
}