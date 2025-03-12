package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameRuleEvaluatorTest {

    @DisplayName("플레이어의 카드의 총합이 21을 넘으면 busted 여부의 결과가 true 가 된다")
    @Test
    void test5() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        Hand hand = new Hand();
        Player player = new Player("히로", hand);
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.KING));
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.QUEEN));
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.JACK));

        // when
        boolean actual = gameRuleEvaluator.isBusted(player);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("딜러의 카드의 총합이 21을 넘으면 busted 여부의 결과가 true 가 된다")
    @Test
    void test6() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        Hand hand = new Hand();
        Dealer dealer = new Dealer(hand);
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.KING));
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.QUEEN));
        hand.takeCard(new Card(CardSuit.SPADE, CardRank.JACK));

        // when
        boolean actual = gameRuleEvaluator.isBusted(dealer);

        // then
        assertThat(actual).isTrue();
    }
}
