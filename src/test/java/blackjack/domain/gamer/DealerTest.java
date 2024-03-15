package blackjack.domain.gamer;

import blackjack.domain.supplies.Card;
import blackjack.domain.supplies.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.supplies.Rank.NINE;
import static blackjack.domain.supplies.Rank.QUEEN;
import static blackjack.domain.supplies.Rank.SEVEN;
import static blackjack.domain.supplies.Suit.CLUB;
import static blackjack.domain.supplies.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러")
class DealerTest {
    @Test
    @DisplayName("딜러의 첫 카드를 반환한다.")
    void getFirstCardTest() {
        // given
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Card expectedCard = new Card(NINE, SPADE);

        // when
        dealer.draw(List.of(new Card(NINE, SPADE), new Card(QUEEN, CLUB)));

        // then
        assertThat(dealer.findPublicDealCard()).isEqualTo(expectedCard);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 true를 반환한다.")
    void dealerHitUpperBoundTest() {
        // given
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));

        // when
        dealer.draw(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB)));

        // then
        assertThat(dealer.isScoreUnderBound()).isEqualTo(true);
    }
}
