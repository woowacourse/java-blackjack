package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @DisplayName("Card 객체를 생성한다.")
    @Test
    public void createCard() {
        Card card = new Card(Suit.SPACE, Denomination.ACE);

        assertThat(card).isInstanceOf(Card.class);
    }

    @DisplayName("Card 객체의 값을 확인한다.")
    @Test
    public void checkValue() {
        Card card = new Card(Suit.SPACE, Denomination.ACE);

        int score = card.getScore();

        assertThat(score).isEqualTo(1);
    }

    @Test
    @DisplayName("에이스카드인지 확인한다.")
    void isAceCard() {
        Card card = new Card(Suit.CLOVER, Denomination.ACE);

        boolean isAce = card.isAceCard();

        assertThat(isAce).isTrue();
    }
}
