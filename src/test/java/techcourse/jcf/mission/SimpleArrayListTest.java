package techcourse.jcf.mission;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SimpleArrayListTest {

    private final SimpleArrayList arrayList = new SimpleArrayList();

    @BeforeEach
    void init() {
        arrayList.add("first");
        arrayList.add("second");
        arrayList.add("third");
        arrayList.add("forth");
    }

    @Test
    @DisplayName("생성자 파라미터가 없으면 빈 SimpleArrayList가 생성된다.")
    void generateEmptyTest() {
        SimpleArrayList arrayList = new SimpleArrayList();
        System.out.println(arrayList.getElementData().length);

        arrayList.add("seongHa");
        System.out.println(arrayList.getElementData().length);

        arrayList.add("se");
        arrayList.add("se");
        arrayList.add("se");
        arrayList.add("se");
        arrayList.add("se");
        arrayList.add("se");
        arrayList.add("se");
        arrayList.add("se");
        arrayList.add("se");
        arrayList.add("se");
        System.out.println(arrayList.getElementData().length);
    }

    @Test
    @DisplayName("int 파라미터가 있으면 int 사이즈의 SimpleArrayList가 생성된다.")
    void generateParameterSizeTest() {

        SimpleArrayList arrayList = new SimpleArrayList(5);

        assertThat(arrayList.getElementData().length).isEqualTo(5);
    }

    @Test
    @DisplayName("add에 값이 주어지면 맨 뒤에 값을 추가한다.")
    void addValueTest() {
        arrayList.add("first");
        arrayList.add("second");

        String[] elementData = arrayList.getElementData();

        Assertions.assertThat(arrayList.size()).isEqualTo(6);
        Assertions.assertThat(elementData[0]).isEqualTo("first");
        Assertions.assertThat(elementData[1]).isEqualTo("second");
        System.out.println(elementData.length);
    }

    @Test
    @DisplayName("인덱스, 값이 주어지면 해당 인덱스에 값을 추가한다.")
    void addIndexAndValueTest() {
        arrayList.add(1, "newSecond");

        String[] elementData = arrayList.getElementData();

        Assertions.assertThat(elementData[1]).isEqualTo("newSecond");
        Assertions.assertThat(arrayList.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("인덱스와 값으로 해당 인덱스의 값을 저장하고 이전 저장 값을 반환한다.")
    void setTest() {
        String previousValue = arrayList.set(1, "newSecond");
        String[] elementData = arrayList.getElementData();

        Assertions.assertThat(previousValue).isEqualTo("second");
        Assertions.assertThat(elementData[1]).isEqualTo("newSecond");
    }

    @ParameterizedTest
    @CsvSource(value = {"0, first", "1, second", "2, third", "3, forth"})
    @DisplayName("인덱스로 해당 인덱스에 저장된 값을 반환한다.")
    void getTest(int index, String expectedValue) {
        Assertions.assertThat(arrayList.get(index)).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @CsvSource(value = {"first, 0", "second, 1", "third, 2", "forth, 3"})
    @DisplayName("값으로 해당 값이 저장된 인덱스를 반환한다.")
    void indexOfTest(String value, int expectedIndex) {
        Assertions.assertThat(arrayList.indexOf(value)).isEqualTo(expectedIndex);
    }

    @ParameterizedTest
    @ValueSource(strings = {"first", "second", "third", "forth"})
    @DisplayName("주어진 값이 저장된 값 중에 있는지 확인한다.")
    void containsTest(String value) {
        Assertions.assertThat(arrayList.contains(value)).isTrue();
    }

    @Test
    @DisplayName("저장된 값 없이 비었는지 확인한다.")
    void isEmptyTest() {
        SimpleArrayList generatedArrayList = new SimpleArrayList();
        Assertions.assertThat(generatedArrayList.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("주어진 값과 같은 저장된 값을 지운다.")
    void removeValueTest() {
        arrayList.remove("second");

        Assertions.assertThat(arrayList.size()).isEqualTo(3);
        Assertions.assertThat(arrayList.get(1)).isNotEqualTo("second");
        Assertions.assertThat(arrayList.get(1)).isEqualTo("third");
        Assertions.assertThat(arrayList.remove("first")).isTrue();
    }

    @Test
    @DisplayName("주어진 인덱스로 해당 인덱스에 저장된 값을 지운다.")
    void removeIndexTest() {
        arrayList.remove(1);
        Assertions.assertThat(arrayList.size()).isEqualTo(3);
        Assertions.assertThat(arrayList.get(1)).isNotEqualTo("second");
        Assertions.assertThat(arrayList.get(1)).isEqualTo("third");
        Assertions.assertThat(arrayList.remove(0)).isEqualTo("first");
    }

    @Test
    @DisplayName("저장된 모든 값을 지운다.")
    void clearTest() {
        arrayList.clear();

        Assertions.assertThat(arrayList.get(0)).isNull();
        Assertions.assertThat(arrayList.get(1)).isNull();
        Assertions.assertThat(arrayList.get(2)).isNull();
        Assertions.assertThat(arrayList.get(3)).isNull();
    }
}
