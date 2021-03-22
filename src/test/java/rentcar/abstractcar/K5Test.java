package rentcar.abstractcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class K5Test {

    private K5 k5;

    @BeforeEach
    private void setUp() {
        k5 = new K5(130);
    }

    @Test
    @DisplayName("K5의 연비 반환 테스트")
    void testDistancePerLiter() {
        assertThat(k5.getDistancePerLiter()).isEqualTo(13);
    }

    @Test
    @DisplayName("K5 생성할 때 이동할 거리 주입된다.")
    void testTripDistance() {
        assertThat(k5.getTripDistance()).isEqualTo(130);
    }

    @Test
    @DisplayName("K5에 필요한 주유량 테스트")
    void testChargeQuantity() {
        assertThat(k5.getChargeQuantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("K5 이름 반환된다.")
    void testName() {
        assertThat(k5.getName()).isEqualTo("K5");
    }
}
