package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.money.Chip;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.Rank.NINE;
import static blackjack.domain.card.Rank.QUEEN;
import static blackjack.domain.card.Rank.SEVEN;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러")
class DealerTest {
    @Test
    @DisplayName("딜러의 첫 카드를 반환한다.")
    void getFirstCardTest() {
        // given
        Dealer dealer = new Dealer(new Chip(0L));
        Card expectedCard = Card.of(NINE, SPADE);

        // when
        dealer.draw(List.of(Card.of(NINE, SPADE), Card.of(QUEEN, CLUB)));

        // then
        assertThat(dealer.findPublicDealCard()).isEqualTo(expectedCard);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 true를 반환한다.")
    void dealerHitUpperBoundTest() {
        // given
        Dealer dealer = new Dealer(new Chip(0L));

        // when
        dealer.draw(List.of(Card.of(NINE, SPADE), Card.of(SEVEN, CLUB)));

        // then
        assertThat(dealer.isScoreUnderBound()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러는 전체 플레이어의 추가 금액의 합 * -1 만큼 받는다.")
    void dealerProfit() {
        // given
        Dealer dealer = new Dealer(new Chip(0L));

        // when
        long actual = dealer.calculateProfit(1000L + 200L);

        // then
        assertThat(actual).isEqualTo(-1200L);
    }
}
