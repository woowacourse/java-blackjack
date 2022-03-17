package blackjack.domain.card;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.cards.Cards;
import blackjack.domain.card.element.Denomination;
import blackjack.domain.card.element.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private Cards cards;
    private Card card5;

    @BeforeEach
    void setup() {
        cards = new Cards();
        card5 = Card.of(Denomination.stringOf("A"), Suit.SPADE);
        Card card6 = Card.of(Denomination.stringOf("A"), Suit.SPADE);
        cards.add(card5);
        cards.add(card6);
    }

    @Test
    @DisplayName("카드모음 생성되는지 검사")
    public void equalSizeTest() {
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("첫 카드 리턴 기능 검사")
    void getFirstCardTest() {
        assertThat(cards.getFirstCard()).isEqualTo(card5);
    }

    @Test
    @DisplayName("단순 포인트 리턴 기능 검사")
    void getRawPointTest() {
        assertThat(cards.getRawPoint()).isEqualTo(22);
    }

    @Test
    @DisplayName("특정 카드 개수 리턴 기능 검사")
    void getAceCountTest() {
        assertThat(cards.getDenominationCount(Denomination.ACE)).isEqualTo(2);
    }
}
