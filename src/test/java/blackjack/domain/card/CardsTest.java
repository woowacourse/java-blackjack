package blackjack.domain.card;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    private Cards cards;

    @BeforeEach
    void setup() {
        cards = new Cards();
        cards.add(Card.of(Denomination.fromInitial("A"), Suit.SPADE));
        cards.add(Card.of(Denomination.fromInitial("A"), Suit.CLOVER));
    }

    @Test
    @DisplayName("카드모음 생성되는지 테스트")
    public void equalSizeTest() {
        assertThat(cards.size())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("첫 카드 리턴 기능 테스트")
    void getFirstCardTest() {
        assertThat(cards.getFirstCard())
                .isEqualTo(Card.of(Denomination.fromInitial("A"), Suit.SPADE));
    }

    @Test
    @DisplayName("단순 포인트 리턴 기능 테스트")
    void getRawPointTest() {
        assertThat(cards.getRawPoint())
                .isEqualTo(22);
    }

    @Test
    @DisplayName("특정 카드 개수 리턴 기능 테스트")
    void getAceCountTest() {
        assertThat(cards.getDenominationCount(Denomination.ACE))
                .isEqualTo(2);
    }

    @Test
    @DisplayName("카드 리스트 리턴 기능 테스트")
    void get() {
        assertThat(cards.getCopy())
                .isEqualTo(List.of(
                        Card.of(Denomination.fromInitial("A"), Suit.SPADE),
                        Card.of(Denomination.fromInitial("A"), Suit.CLOVER)));
    }
}
