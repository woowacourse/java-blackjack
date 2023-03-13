package techcourse.jcf.mission;

public class MissionFour {
    public static void main(String[] args) {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        for (int index = 0; index < filteredDoubleValues.size(); index++) {
            System.out.println(filteredDoubleValues.get(index));
        }
        for (int index = 0; index < filteredIntValues.size(); index++) {
            System.out.println(filteredIntValues.get(index));
        }
    }
}
