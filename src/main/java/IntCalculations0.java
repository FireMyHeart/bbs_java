public class IntCalculations0 {
    public static double getDataTypesIntA() {
        // метод должен вернуть значение 1, т.е. остаток от целочисленного деления
        int a = 0;
        a = 5 % 2;
        return a;
    }

    public static double getDataTypesIntB() {
        // метод должен вернуть значение 2, т.е. просто выполнить целочисленное деление
        int b = 0;
        b = 5 / 2;
        return b;
    }

    public static double getDataTypesDoubleA() {
        // метод должен вернуть значение 0,5, т.е. снова нужен остаток от деления
        double a = 0;
        a = 5.0 % 1.5;
        return a;
    }

    // снова попросила AI, как выводить правильно для самопроверки, т.к. не понимаю до конца синтаксис
    public static void main(String[] args) {
        System.out.println("A = " + getDataTypesIntA());
        System.out.println("B = " + getDataTypesIntB());
        System.out.println("DoubleA = " + getDataTypesDoubleA());
    }
}
