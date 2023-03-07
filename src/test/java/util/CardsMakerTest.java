package util;

import domain.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsMakerTest {

    @Test
    @DisplayName("Card 덱은 52 장의 초기 개수를 가진다.")
    void createCardDeckSuccess() {
        Cards cards = CardsMaker.generate();

        assertThat(cards.getSize()).isEqualTo(52);
    }

}
