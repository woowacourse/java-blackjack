package techcourse.jcf.mission;

public class MissionThree {
    public static void main(String[] args) {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        System.out.println(doubleTotal);
        System.out.println(intTotal);
    }
}
