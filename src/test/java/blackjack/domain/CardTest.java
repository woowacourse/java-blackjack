package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드 생성 확인")
    void createCard() {
        Suit pattern = Suit.HEART;
        Denomination point = Denomination.ACE;

        Card card = new Card(pattern, point);

        assertThat(card).isInstanceOf(Card.class);
    }


}
