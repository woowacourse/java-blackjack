package study;

public class ReferenceTest {
    public static class Balloon {

        private String color;

        public Balloon() {}

        public Balloon(String c) {
            this.color = c;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public static void main(String[] args) {
        Balloon red = new Balloon("Red"); // memory reference = 50
        Balloon blue = new Balloon("Blue"); // memory reference = 100
        System.out.println(red);
        System.out.println(blue);
        swap(red, blue);

        System.out.println("After the swap method executes:");
        System.out.println("`red` color value = " + red.getColor());
        System.out.println("`blue` color value = " + blue.getColor());


    }

    // Generic swap method
    public static void swap(Object o1, Object o2){
        System.out.println(o1);
        System.out.println(o2);
        Object temp = o1;
        o1 = o2;
        o2 = temp;
        System.out.println(o1);
        System.out.println(o2);
    }

}
