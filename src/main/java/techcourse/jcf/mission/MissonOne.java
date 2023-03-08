package techcourse.jcf.mission;

public class MissonOne {
    public static void main(String[] args) {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);
    }
}
