package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    private Dealer createDealerWithCards(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.addCard(card);
        }
        return dealer;
    }

    @Test
    @DisplayName("딜러는 초기 카드 내역 공개 시, 가지고 있는 카드 2장 중 한장만 보여준다.")
    void shouldReturnSingleCardForInitialVisibleCards() {
        // given
        Card card1 = new Card(CardShape.HEART, CardContents.FIVE);
        Card card2 = new Card(CardShape.HEART, CardContents.SIX);
        Dealer testDealer = createDealerWithCards(card1, card2);

        // when & then
        assertThat(testDealer.getInitialVisibleCards())
                .containsExactly(card1);
    }

    @Test
    @DisplayName("딜러는 카드의 합이 16 이하면 카드를 더 뽑을 수 있다.")
    void shouldReturnTrueWhenCardScoreSumIsMinimumOrLess() {
        // given
        Dealer testDealer = createDealerWithCards(
                new Card(CardShape.HEART, CardContents.FIVE),
                new Card(CardShape.HEART, CardContents.SIX)
        );

        // when & then
        assertTrue(testDealer.isDrawable());
    }

    @Test
    @DisplayName("딜러는 카드의 합이 16 이상이면 카드를 더 이상 뽑을 수 없다.")
    void shouldReturnFalseWhenNotBust() {
        // given
        Dealer testDealer = createDealerWithCards(
                new Card(CardShape.HEART, CardContents.TEN),
                new Card(CardShape.CLOVER, CardContents.TEN),
                new Card(CardShape.SPADE, CardContents.TEN)
        );

        // when & then
        assertFalse(testDealer.isDrawable());
    }
}
