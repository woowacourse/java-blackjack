package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드를 생성한다.")
    void createCard() {
        Card card = new Card(Denomination.THREE, Suit.DIAMOND);
        assertThat(card.toString()).isEqualTo("3다이아몬드");
    }

    @Test
    @DisplayName("카드가 Ace인지 확인한다.")
    void checkAceCard() {
        Card card = new Card(Denomination.ACE, Suit.DIAMOND);
        assertThat(card.isAce()).isTrue();
    }

    @Test
    @DisplayName("카드의 문양을 확인한다.")
    void checkDenomination() {
        Card card = new Card(Denomination.ACE, Suit.DIAMOND);
        assertThat(card.getDenomination()).isEqualTo(Denomination.ACE);
    }

    @Test
    @DisplayName("카드의 문양 내용을을 확인한다.")
    void checkDenominationName() {
        Card card = new Card(Denomination.ACE, Suit.DIAMOND);
        assertThat(card.getDenominationName()).isEqualTo("A");
    }

    @Test
    @DisplayName("카드의 숫자 내용을을 확인한다.")
    void checkSuitName() {
        Card card = new Card(Denomination.TWO, Suit.DIAMOND);
        assertThat(card.getSuitName()).isEqualTo("다이아몬드");
    }
}
