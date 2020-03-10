package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @Test
    @DisplayName("Cards 생성")
    void cards() {
        Cards cards = new Cards(Arrays.asList(new Card(Symbol.TWO,Type.HEART)));
        assertThat(cards).isNotNull();
    }
}
