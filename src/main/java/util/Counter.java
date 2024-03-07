package util;

public class Counter {
    private static int count = 0;

    private Counter() {
        throw new IllegalStateException("Static Util Class");
    }

    public static void increase() {
        count++;
    }

    public static void reset(){
        count = 0;
    }

    public static boolean isLessThan(int condition){
        return count < condition;
    }
}
