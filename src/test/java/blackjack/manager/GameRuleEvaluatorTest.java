package blackjack.manager;

import blackjack.StubPossibleSumCardHolder;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Card;
import blackjack.domain.cardholder.CardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.cardholder.Hand;
import blackjack.domain.Player;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameRuleEvaluatorTest {
    @DisplayName("딜러가 가진 카드의 총합의 경우의 수 중에서 16보다 큰 숫자가 있다면 false 를 반환한다")
    @Test
    void test1() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        CardHolder cardHolder = new StubPossibleSumCardHolder(List.of(1, 16, 19));

        // when
        boolean actual = gameRuleEvaluator.canTakeCardFor(new Dealer(cardHolder));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("딜러가 가진 카드의 총합의 경우의 수가 모두 16 이하라면 true 를 반환한다")
    @Test
    void test2() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        CardHolder cardHolder = new StubPossibleSumCardHolder(List.of(1, 3, 13));

        // when
        boolean actual = gameRuleEvaluator.canTakeCardFor(new Dealer(cardHolder));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어의 카드 총합이 21을 넘지 않은 경우가 있다면 true를 반환한다.")
    @Test
    void test3() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        CardHolder cardHolder = new StubPossibleSumCardHolder(List.of(1, 21, 100));
        // when
        boolean actual = gameRuleEvaluator.canTakeCardFor(new Player("꾹이", cardHolder));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어의 카드 총합이 모두 21을 넘는다면 false를 반환한다")
    @Test
    void test4() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        CardHolder cardHolder = new StubPossibleSumCardHolder(List.of(22, 23, 100));

        // when
        boolean actual = gameRuleEvaluator.canTakeCardFor(new Player("꾹이", cardHolder));

        // then
        assertThat(actual).isFalse();
    }

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
        boolean actual = gameRuleEvaluator.isBustedFor(player);

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
        boolean actual = gameRuleEvaluator.isBustedFor(dealer);

        // then
        assertThat(actual).isTrue();
    }
}
