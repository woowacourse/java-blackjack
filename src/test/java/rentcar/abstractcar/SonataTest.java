package rentcar.abstractcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SonataTest {

    private Sonata sonata;

    @BeforeEach
    private void setUp() {
        sonata = new Sonata(100);
    }

    @Test
    @DisplayName("소나타의 연비 반환 테스트")
    void testDistancePerLiter() {
        assertThat(sonata.getDistancePerLiter()).isEqualTo(10);
    }

    @Test
    @DisplayName("소나타 생성할 때 이동할 거리 주입된다.")
    void testTripDistance() {
        assertThat(sonata.getTripDistance()).isEqualTo(100);
    }

    @Test
    @DisplayName("소나타에 필요한 주유량 테스트")
    void testChargeQuantity() {
        assertThat(sonata.getChargeQuantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("소나타 이름 반환된다.")
    void testName() {
        assertThat(sonata.getName()).isEqualTo("Sonata");
    }
}
