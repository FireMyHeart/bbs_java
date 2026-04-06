public class DeclareVariables {
    public String customerName = "Sasha";
    public int cupsOfCoffee = 1;
    public double costOfCoffee = 99.9;
    public double tips = 0;
    public double totalCost = costOfCoffee * cupsOfCoffee + tips;
    public boolean tip = true;

    public String declareVariables() {
        String response = "";
        response = "Dear " + customerName + ", thank you for supporting our business! \n"
                + "Cups of coffee bought: " + cupsOfCoffee + ".\n"
                + "Cost of 1 cup of coffee: $" + costOfCoffee + ".\n"
                + "Your total is: $" + totalCost + ".\n"
                + "Tip is included? " + tip + ".";
        return response;
    }

    public static void main(String[] args) {
        DeclareVariables d = new DeclareVariables();
        System.out.println(d.declareVariables());
    }
}

/*
Вот эту часть у AI спрашивала, как мне сделать аналог вызова метода и принт, как в питоне, чтобы проверить, правильно ли пишется:
    public static void main(String[] args) {
        DeclareVariables d = new DeclareVariables();
        System.out.println(d.declareVariables());
    }
 */
