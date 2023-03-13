package techcourse.jcf.mission;

public class MissionTwo {
    public static void main(String[] args) {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
        System.out.println(values.get(0));
        System.out.println(values.get(1));

        final Integer[] arrays2 = {1, 2};
        final SimpleList<Integer> values2 = SimpleList.<Integer>fromArrayToList(arrays2);
        System.out.println(values2.get(0));
        System.out.println(values2.get(1));
    }
}
