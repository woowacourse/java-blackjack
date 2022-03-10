package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SonataTest {

    @Test
    void getTripDistanceTest() {
        Sonata sonata = new Sonata(150);
        assertThat(sonata.getTripDistance()).isEqualTo(150);
    }

    @Test
    void getDefaultDistancePerLiterTest() {
        Sonata sonata = new Sonata(150);
        assertThat(sonata.getDistancePerLiter()).isEqualTo(10);
    }
}
