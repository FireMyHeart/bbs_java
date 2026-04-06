public class Employees {
    public String name;
    public String role;

    public static String hireEmployees() {
        Employees male = new Employees();
        male.name = "Александр";
        male.role = "директор по маркетингу";

        Employees female = new Employees();
        female.name = "Наталья";
        female.role = "заместитель директора по маркетингу";

        return "Вчера наша компания пополнилась новыми сотрудниками. "
                + male.name + " нанят на должность " + male.role + ", а "
                + female.name + " нанята в должности " + female.role + ".";
    }

    public static void main(String[] args) {
        System.out.println(hireEmployees());
    }
}
