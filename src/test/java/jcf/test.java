package jcf;

public class test {
    public static void main(String[] args) {
        SimpleList<Integer> simpleList = new SimpleArrayList<>();
        for (int i = 0; i < 15; i++) {
            simpleList.add(i);
        }
        System.out.println(simpleList.size());
        simpleList.add(4, -1);
        printAll(simpleList);
        simpleList.remove(Integer.valueOf(-1));
        printAll(simpleList);
        simpleList.remove(14);
        printAll(simpleList);
        System.out.println(simpleList.contains(-1));
        System.out.println(simpleList.indexOf(4));
        simpleList.set(13, -1);
        printAll(simpleList);
    }

    private static void printAll(SimpleList<Integer> simpleList) {
        for (int i = 0; i < simpleList.size(); i++) {
            System.out.print(simpleList.get(i) + " ");
        }
        System.out.println();
    }
}
