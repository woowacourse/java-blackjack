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

    @DisplayName("Card의 Denomination을 확인한다.")
    @Test
    public void checkDenomination() {
        Card card = new Card(Suit.SPACE, Denomination.ACE);

        int denomination = card.getDenomination();

        assertThat(denomination).isEqualTo(1);
    }

    @DisplayName("Card의 shape을 확인한다.")
    @Test
    public void checkSuit() {
        Card card = new Card(Suit.SPACE, Denomination.ACE);

        String suit = card.getSuit();

        assertThat(suit).isEqualTo("스페이스");
    }

    @Test
    @DisplayName("에이스카드인지 확인한다.")
    void isAceCard() {
        Card card = new Card(Suit.CLOVER, Denomination.ACE);

        boolean isAce = card.isAceCard();

        assertThat(isAce).isTrue();
    }
}
