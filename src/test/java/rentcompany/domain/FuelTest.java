package rentcompany.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FuelTest {
    @DisplayName("연료 VO 생성 확인 테스트")
    @Test
    void fuelCreateTest() {
        double expectedFuelQuantity = 100;
        Fuel fuel = new Fuel(expectedFuelQuantity);

        assertThat(fuel).isEqualTo(new Fuel(expectedFuelQuantity));
    }

    @DisplayName("예외 : 연료량이 음수면 예외처리")
    @Test
    void whenNegativeQuantityTest() {
        double expectedFuelQuantity = -100;
        assertThatThrownBy(() -> new Fuel(expectedFuelQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("연료량은 음수일 수 없습니다.");
    }
}
