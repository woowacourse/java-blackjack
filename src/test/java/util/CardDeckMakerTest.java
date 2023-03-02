package util;

import domain.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckMakerTest {

    @Test
    @DisplayName("Card 덱은 52 장의 초기 개수를 가진다.")
    void createCardDeckSuccess() {
        List<Card> cards = CardDeckMaker.generate();

        assertThat(cards.size()).isEqualTo(52);
    }

}
