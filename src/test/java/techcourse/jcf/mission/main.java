package techcourse.jcf.mission;

public class main {

    public static void main(String[] args) {
        final String[] arrays = {"first", "second"};
        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
        System.out.println(values.get(0));
    }
}
