package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 기본_덱은_52장이다() {
        // given
        Deck deck = new Deck();

        // when
        int count = 0;
        while (true) {
            try {
                deck.dealCard();
                count++;
            } catch (Exception e) {
                break;
            }
        }

        // then
        assertThat(count).isEqualTo(52); // 4 suits × 13 ranks
    }

}
