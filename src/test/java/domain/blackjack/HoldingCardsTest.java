package domain.blackjack;

import static domain.card.TestCards.ACE_HEART;
import static domain.card.TestCards.JACK_HEART;
import static domain.card.TestCards.KING_HEART;
import static domain.card.TestCards.QUEEN_HEART;
import static domain.card.TestCards.SIX_HEART;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldingCardsTest {
    @Test
    @DisplayName("포함된 카드의 포인트 합계가 올바른지 검증")
    void calculateTotalPoint() {
        HoldingCards holdingCards = HoldingCards.of(ACE_HEART, SIX_HEART, JACK_HEART, QUEEN_HEART, KING_HEART);

        SummationCardPoint actual = holdingCards.calculateTotalPoint();
        SummationCardPoint expected = new SummationCardPoint(37);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Ace가 포함되었는지 여부 검증")
    void hasAce() {
        HoldingCards holdingCards = HoldingCards.of(ACE_HEART);
        Assertions.assertThat(holdingCards.hasAce()).isTrue();
    }
}
