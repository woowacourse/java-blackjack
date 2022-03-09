package fuelinjection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CarTest {
    @ParameterizedTest(name = "소나타가 {0}km 이동해야 할 때 필요한 연료량은 {1}리터다.")
    @CsvSource(value = {"100:10", "120:12"}, delimiterString = ":")
    void injectFuel_Sonata(int distance, int fuel) {
        Car sonata = new Sonata();
        sonata.injectFuel(new Distance(distance));
        assertThat(sonata.getFuel()).isEqualTo(fuel);
    }

    @ParameterizedTest(name = "아반떼가 {0}km 이동해야 할 때 필요한 연료량은 {1}리터다.")
    @CsvSource(value = {"120:8", "150:10"}, delimiterString = ":")
    void injectFuel_Avante(int distance, int fuel) {
        Car avante = new Avante();
        avante.injectFuel(new Distance(distance));
        assertThat(avante.getFuel()).isEqualTo(fuel);
    }

    @ParameterizedTest(name = "K5가 {0}km 이동해야 할 때 필요한 연료량은 {1}리터다.")
    @CsvSource(value = {"130:10", "260:20"}, delimiterString = ":")
    void injectFuel_K5(int distance, int fuel) {
        Car k5 = new K5();
        k5.injectFuel(new Distance(distance));
        assertThat(k5.getFuel()).isEqualTo(fuel);
    }
}
