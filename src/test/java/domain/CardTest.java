package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @DisplayName("카드가 에이스 카드인지 판단한다.")
    @Test
    void isCardAce() {
        Card card = new Card(Denomination.ACE, Suit.CLOVER);
        assertThat(card.isAceCard()).isTrue();
    }

    @DisplayName("카드의 점수 정보를 제공한다.")
    @Test
    void getCardScore() {
        Card card = new Card(Denomination.TWO, Suit.CLOVER);
        assertThat(card.getScore()).isEqualTo(2);
    }

    @DisplayName("카드의 이름 정보를 제공한다.")
    @Test
    void getCardName() {
        Card card = new Card(Denomination.TWO, Suit.CLOVER);
        assertThat(card.getName()).isEqualTo("2");
    }

    @DisplayName("카드의 모양 정보를 제공한다.")
    @Test
    void getCardShape() {
        Card card = new Card(Denomination.TWO, Suit.CLOVER);
        assertThat(card.getShape()).isEqualTo("클로버");
    }
}
