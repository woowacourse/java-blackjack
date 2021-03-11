package rentcompany;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class CarTest {
    @Test
    @DisplayName("소나타 생성 확인")
    void create() {
        assertThatCode(() -> new Sonata(150)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("K5 생성 확인")
    void create2() {
        assertThatCode(() -> new K5(150)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("아반떼 생성 확인")
    void create3() {
        assertThatCode(() -> new Avante(150)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("소나타 연료량 계산")
    void calculateAmountOfFuel_Sonata() {
        Car sonata = new Sonata(100);
        double value = sonata.calculateAmountOfFuel();
        assertThat(value).isEqualTo(10.0);
    }

    @Test
    @DisplayName("K5 연료량 계산")
    void calculateAmountOfFuel_K5() {
        Car k5 = new K5(130);
        double value = k5.calculateAmountOfFuel();
        assertThat(value).isEqualTo(10.0);
    }

    @Test
    @DisplayName("아반떼 연료량 계산")
    void calculateAmountOfFuel_Avante() {
        Car avante = new Avante(150);
        double value = avante.calculateAmountOfFuel();
        assertThat(value).isEqualTo(10.0);
    }
}
