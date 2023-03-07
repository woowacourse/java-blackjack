package techcourse.jcf.mission;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ListStudy {

    @Test
    public void arrayList() {
        ArrayList<String> values = new ArrayList<>();
        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue(); // 세 번째 값을 추가한다.
        assertThat(values.size()).isEqualTo(3); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("first"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.remove(0)).isEqualTo("first"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(2); // 값이 삭제 됐는지 확인한다.
        assertThat(values.get(0)).isEqualTo("second");
        assertThat(values.get(1)).isEqualTo("third");
        assertThat(values.remove(1)).isEqualTo("third"); // 첫 번째 값을 삭제한다.
        assertThat(values.remove(0)).isEqualTo("second"); // 첫 번째 값을 삭제한다.
        assertThat(values.add("four")).isTrue();
        assertThat(values.get(0)).isEqualTo("four");
        values.add(0, "five");
        assertThat(values.get(0)).isEqualTo("five");
        assertThat(values.get(1)).isEqualTo("four");
        assertThat(values.contains("five")).isTrue();
        assertThat(values.isEmpty()).isFalse();
        assertThat(values.indexOf("five")).isEqualTo(0);
        assertThat(values.remove("five")).isTrue();
        assertThat(values.get(0)).isEqualTo("four");
        assertThat(values.set(0, "five")).isEqualTo("four");
        assertThat(values.get(0)).isEqualTo("five");
        values.clear();
        assertThat(values.size()).isEqualTo(0);
        assertThat(values.add("six")).isTrue();
        assertThat(values.add("seven")).isTrue();
        values.add(0, "eight");
        System.out.println(values);
    }

    @Test
    public void mathewArrayList() {
        SimpleList<String> values = new SimpleArrayList();
        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue(); // 세 번째 값을 추가한다.
        assertThat(values.size()).isEqualTo(3); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("first"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.remove(0)).isEqualTo("first"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(2); // 값이 삭제 됐는지 확인한다.
        assertThat(values.get(0)).isEqualTo("second");
        assertThat(values.get(1)).isEqualTo("third");
        assertThat(values.remove(1)).isEqualTo("third"); // 첫 번째 값을 삭제한다.
        assertThat(values.remove(0)).isEqualTo("second"); // 첫 번째 값을 삭제한다.
        assertThat(values.add("four")).isTrue();
        assertThat(values.get(0)).isEqualTo("four");
        values.add(0, "five");
        assertThat(values.get(0)).isEqualTo("five");
        assertThat(values.get(1)).isEqualTo("four");
        assertThat(values.contains("five")).isTrue();
        assertThat(values.isEmpty()).isFalse();
        assertThat(values.indexOf("five")).isEqualTo(0);
        assertThat(values.remove("five")).isTrue();
        assertThat(values.get(0)).isEqualTo("four");
        assertThat(values.set(0, "five")).isEqualTo("four");
        assertThat(values.get(0)).isEqualTo("five");
        values.clear();
        assertThat(values.size()).isEqualTo(0);
        assertThat(values.add("six")).isTrue();
        assertThat(values.add("seven")).isTrue();
        values.add(0, "eight");
        System.out.println(values);
    }

    @Test
    public void mathewLinkedList() {
        SimpleList<String> values = new SimpleLinkedList();
        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue(); // 세 번째 값을 추가한다.
        assertThat(values.size()).isEqualTo(3); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("first"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.remove(0)).isEqualTo("first"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(2); // 값이 삭제 됐는지 확인한다.
        assertThat(values.get(0)).isEqualTo("second");
        assertThat(values.get(1)).isEqualTo("third");
        assertThat(values.remove(1)).isEqualTo("third"); // 첫 번째 값을 삭제한다.
        assertThat(values.remove(0)).isEqualTo("second"); // 첫 번째 값을 삭제한다.
        assertThat(values.add("four")).isTrue();
        assertThat(values.get(0)).isEqualTo("four");
        values.add(0, "five");
        assertThat(values.get(0)).isEqualTo("five");
        assertThat(values.get(1)).isEqualTo("four");
        assertThat(values.contains("five")).isTrue();
        assertThat(values.isEmpty()).isFalse();
        assertThat(values.indexOf("five")).isEqualTo(0);
        assertThat(values.remove("five")).isTrue();
        assertThat(values.get(0)).isEqualTo("four");
        assertThat(values.set(0, "five")).isEqualTo("four");
        assertThat(values.get(0)).isEqualTo("five");
        values.clear();
        assertThat(values.size()).isEqualTo(0);
        assertThat(values.add("six")).isTrue();
        assertThat(values.add("seven")).isTrue();
        values.add(0, "eight");
        System.out.println(values);
    }

    @Test
    public void genericArrayList() {
        SimpleList<Integer> values = new SimpleArrayList();
        values.add(1);
        values.add(2);

        assertThat(values.get(0)).isEqualTo(1);
        assertThat(values.set(0, 4)).isEqualTo(1);
        System.out.println(values);
    }

    @Test
    public void genericLinkedList() {
        SimpleList<Double> values = new SimpleLinkedList();
        values.add(2.2);
        values.add(2.3);

        assertThat(values.get(0)).isEqualTo(2.2);
        assertThat(values.set(0, 1.2)).isEqualTo(2.2);
        System.out.println(values);
    }

    @Test
    public void missionTwo() {
        final Integer[] arrays = {1, 2};
        final SimpleList<Integer> values = SimpleList.fromArrayToList(arrays);

        assertThat(values.get(0)).isEqualTo(1);
        assertThat(values.set(0, 4)).isEqualTo(1);
        System.out.println(values);
    }

    @Test
    public void missionThree() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(1, 2);

        assertThat(SimpleList.sum(doubleValues)).isEqualTo(1.2);
        assertThat(SimpleList.sum(intValues)).isEqualTo(3);
        System.out.println(doubleValues);
        System.out.println(intValues);
    }

    @Test
    public void missionFour() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(-10, 0, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        assertThat(filteredDoubleValues.toString()).isEqualTo("[0.5, 0.7]");
        assertThat(filteredIntValues.toString()).isEqualTo("[0, 1, 2]");
    }

    @Test
    public void missionFive() {
        final LaserPrinter laserPrinter = new LaserPrinter();
        final SimpleList<Printer> printers = new SimpleArrayList<>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);
        assertThat(printers.get(0) == laserPrinter).isTrue();
    }

}

class Printer {
}

class LaserPrinter extends Printer {
}
