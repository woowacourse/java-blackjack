package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SonataTest {

    @DisplayName("소나타 생성자를 호출하면, 객체가 생성된다.")
    @Test
    void constructor() {
        Sonata sonata = new Sonata(150);

        assertThat(sonata).isNotNull();
    }

    @DisplayName("소나타의 이름은 언제나 Sonata 이다.")
    @ParameterizedTest(name = "[{index}] 거리: {0}")
    @ValueSource(ints = {100, 500, 3000})
    void getName(int distance) {
        Sonata sonata = new Sonata(distance);

        String actual = sonata.getName();

        assertThat(actual).isEqualTo("Sonata");
    }

    @DisplayName("소나타는 필요한 연료를 거리와 연비를 통해 계산하여 반환한다.")
    @ParameterizedTest(name = "[{index}] 거리: {0}, 필요한 연료: {1}")
    @CsvSource(value = {"100,10", "500,50", "3000,300"})
    void getFuelNeeded(int distance, int expected) {
        Sonata sonata = new Sonata(distance);

        int actual = sonata.getFuelNeeded();

        assertThat(actual).isEqualTo(expected);
    }
}
