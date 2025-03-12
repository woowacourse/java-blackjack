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
    @DisplayName("딜러가 가진 카드의 총합의 경우의 수 중에서 16보다 큰 숫자가 있다면 false 를 반환한다")
    @Test
    void test1() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();

        Card card1 = new Card(CardSuit.SPADE, CardRank.ACE);
        Card card2 = new Card(CardSuit.SPADE, CardRank.SEVEN);

        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        // when
        boolean actual = gameRuleEvaluator.canTakeCardFor(new Dealer(hand));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("딜러가 가진 카드의 총합의 경우의 수가 모두 16 이하라면 true 를 반환한다")
    @Test
    void test2() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();

        Card card1 = new Card(CardSuit.SPADE, CardRank.ACE);
        Card card2 = new Card(CardSuit.SPADE, CardRank.TWO);

        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        // when
        boolean actual = gameRuleEvaluator.canTakeCardFor(new Dealer(hand));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어의 카드 총합이 21을 넘지 않은 경우가 있다면 true를 반환한다.")
    @Test
    void test3() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();

        Card card1 = new Card(CardSuit.SPADE, CardRank.ACE);
        Card card2 = new Card(CardSuit.SPADE, CardRank.SEVEN);

        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        // when
        boolean actual = gameRuleEvaluator.canTakeCardFor(new Player("꾹이", hand));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어의 카드 총합이 모두 21을 넘는다면 false를 반환한다")
    @Test
    void test4() {
        // given
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();

        Card card1 = new Card(CardSuit.CLUB, CardRank.TEN);
        Card card2 = new Card(CardSuit.SPADE, CardRank.TEN);
        Card card3 = new Card(CardSuit.HEART, CardRank.TEN);

        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);
        hand.takeCard(card3);

        // when
        boolean actual = gameRuleEvaluator.canTakeCardFor(new Player("꾹이", hand));

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
