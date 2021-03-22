package rentcar.interfacecar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AvanteTest {

    private Avante avante;

    @BeforeEach
    private void setUp() {
        avante = new Avante(150);
    }

    @Test
    @DisplayName("아반떼의 연비 반환 테스트")
    void testDistancePerLiter() {
        assertThat(avante.getDistancePerLiter()).isEqualTo(15);
    }

    @Test
    @DisplayName("아반뗴 생성할 때 이동할 거리 주입된다.")
    void testTripDistance() {
        assertThat(avante.getTripDistance()).isEqualTo(150);
    }

    @Test
    @DisplayName("아반떼에 필요한 주유량 테스트")
    void testChargeQuantity() {
        assertThat(avante.getChargeQuantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("아반떼 이름 반환된다.")
    void testName() {
        assertThat(avante.getName()).isEqualTo("Avante");
    }
}
