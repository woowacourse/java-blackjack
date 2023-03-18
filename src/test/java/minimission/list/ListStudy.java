package minimission.list;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ListStudy {
    @Test
    public void arrayList() {
        SimpleList values = new SimpleArrayList();

        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue(); // 세 번째 값을 추가한다.
        assertThat(values.size()).isEqualTo(3); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("first"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.remove(0)).isEqualTo("first"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(2); // 값이 삭제 됐는지 확인한다.

        // TODO values에 담긴 모든 값을 출력한다.
        System.out.println(values);
    }
    
    @Test
    void generic_mission1() {
        SimpleList<Integer> values = new SimpleArrayList<>();
        values.add(1);
        values.add(2);
    
        Integer first = values.get(0);
        Integer second = values.get(1);
        
        assertAll(
                () -> assertThat(first).isEqualTo(1),
                () -> assertThat(second).isEqualTo(2)
        );
    }
    
    @Test
    void generic_mission2() {
        final String[] arrays = {"first", "second"};
    
        final SimpleList<String> values = SimpleList.fromArrayToList(arrays);
    
        assertAll(
                () -> assertThat(values.get(0)).isEqualTo("first"),
                () -> assertThat(values.get(1)).isEqualTo("second")
        );
    }
    
    @Test
    void generic_mission3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);
    
        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3
        assertAll(
                () -> assertThat(doubleTotal).isEqualTo(1.2),
                () -> assertThat(intTotal).isEqualTo(3)
        );
    }
    
    @Test
    void generic_mission4() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);
    
        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);
        
        assertAll(
                () -> assertThat(filteredDoubleValues.size()).isEqualTo(2),
                () -> assertThat(filteredIntValues.size()).isEqualTo(2)
        );
    }
    
    class Printer { }
    class LaserPrinter extends Printer { }
    
    @Test
    void generic_mission5() {
        final var laserPrinter = new LaserPrinter();
    
        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);
    
        SimpleList.copy(laserPrinters, printers);
    
        assertThat(printers.get(0) == laserPrinter).isTrue();
    }
}
