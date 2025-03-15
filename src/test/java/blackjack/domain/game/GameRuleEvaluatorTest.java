package blackjack.domain.game;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.GameRuleEvaluator;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class GameRuleEvaluatorTest {

    @Test
    void 플레이어의_카드_합이_21을_초과하면_Busted가_된다() {
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

    @Test
    void 딜러의_카드_합이_21을_초과하면_Busted가_된다() {
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
