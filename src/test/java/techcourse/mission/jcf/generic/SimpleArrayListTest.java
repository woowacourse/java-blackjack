package techcourse.mission.jcf.generic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SimpleArrayListTest {

    @Test
    public void sizeSuccess() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String data = "data";

        //when
        values.add(data);

        //then
        assertThat(values.size()).isEqualTo(1);
    }

    @Test
    public void addTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String data = "data";

        //when
        values.add(data);

        //then
        assertThat(values.size()).isEqualTo(1);
    }

    @Test
    public void getTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String data = "data";
        values.add(data);

        //when
        String result = values.get(0);

        //then
        assertThat(result).isEqualTo(data);
    }

    @Test
    public void addToIndexTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String first = "first";
        values.add(first);

        //when
        String second = "second";
        values.add(1, second);

        //then
        assertThat(values.size()).isEqualTo(2);
        assertThat(values.get(1)).isEqualTo(second);
    }

    @Test
    public void setTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String first = "first";
        String second = "second";
        values.add(first);
        values.add(second);
        String newData = "newData";

        //when
        values.set(1, newData);

        //then
        assertThat(values.get(1)).isEqualTo(newData);
    }

    @Test
    public void containsTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String first = "first";
        String second = "second";
        values.add(first);
        values.add(second);

        //when
        String successValue = "first";
        String failValue = "fail";
        boolean success = values.contains(successValue);
        boolean fail = values.contains(failValue);

        //then
        assertThat(success).isTrue();
        assertThat(fail).isFalse();
    }

    @Test
    public void indexOfTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String first = "first";
        String second = "second";
        values.add(first);
        values.add(second);

        //when
        String target = "first";
        int result = values.indexOf(target);

        //then
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void isEmptyTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();

        //when
        boolean result = values.isEmpty();

        //then
        assertThat(result).isTrue();
    }

    @Test
    public void removeFromValueTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String first = "first";
        String second = "second";
        values.add(first);
        values.add(second);

        //when
        boolean result = values.remove("first");

        //then
        assertThat(result).isTrue();
        assertThat(values.size()).isEqualTo(1);
    }

    @Test
    public void removeFromIndexTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String first = "first";
        String second = "second";
        values.add(first);
        values.add(second);

        //when
        String oldData = values.remove(0);

        //then
        assertThat(oldData).isEqualTo(first);
        assertThat(values.get(0)).isEqualTo(second);
        assertThat(values.size()).isEqualTo(1);
    }

    @Test
    public void clearTest() {
        //given
        SimpleArrayList<String> values = new SimpleArrayList<>();
        String first = "first";
        String second = "second";
        values.add(first);
        values.add(second);

        //when
        values.clear();

        //then
        assertThat(values.isEmpty()).isTrue();
    }
    
    @Test
    public void testFromArrayToList() {
        //given
        SimpleList<String> values = new SimpleArrayList<>();
        final String[] arrays = {"first", "second"};

        //when
        SimpleList<String> result = values.fromArrayToList(arrays);

        //then
        assertThat(result.size()).isEqualTo(arrays.length);
        for (int i = 0; i < 2; i++) {
            assertThat(result.get(i)).isEqualTo(arrays[i]);
        }
    }
    
    @Test
    public void testSum() {
        //given
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(1, 2);

        //when
        final double doubleTotal = SimpleList.sum(doubleValues);
        final double intTotal = SimpleList.sum(intValues);
    
        //then
        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3D);
    }
    
    @Test
    public void testFilterNegative() {
        //given
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(-10, 1, 2);
        
        //when
        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);
    
        //then
        assertThat(filteredDoubleValues.size()).isEqualTo(2);
        assertThat(filteredIntValues.size()).isEqualTo(2);
    }

    @Test
    public void testCopy() {
        //given
        class Printer { }
        class LaserPrinter extends Printer { }

        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<>(laserPrinter);
        
        //when
        SimpleList.copy(laserPrinters, printers);

        //then
        assertThat(printers.get(0) == laserPrinter).isTrue();
    }
}
