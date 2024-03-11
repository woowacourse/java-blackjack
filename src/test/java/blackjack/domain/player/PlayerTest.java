package blackjack.domain.player;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import fixture.HandFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PlayerTest {

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                arguments(new Card(CardRank.THREE, CardSuit.HEART), 0, 1),
                arguments(new Card(CardRank.FOUR, CardSuit.HEART), 0, 1),
                arguments(new Card(CardRank.FIVE, CardSuit.HEART), 1, 0)
        );
    }

    @DisplayName("플레이어는 21을 넘지 않을 경우 히트가 가능하다.")
    @Test
    void testCanHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();

        Player player = new Player(hand, new PlayerName("pobi"));

        // when
        boolean actual = player.canHit();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어는 21을 넘을 경우 히트가 불가능하다.")
    @Test
    void testCanNotHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Player player = new Player(hand, new PlayerName("pobi"));

        // when
        boolean actual = player.canHit();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("플레이어가 21을 초과하면 플레이어는 패배한다.")
    @Test
    void testJudgePlayerBust() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));
        Player player = new Player(hand, new PlayerName("pobi"));

        Dealer dealer = new Dealer(new Hand());

        // when
        Result judge = player.judge(dealer);

        // then
        assertAll(
                () -> assertThat(judge.getWinCount()).isEqualTo(0),
                () -> assertThat(judge.getLoseCount()).isEqualTo(1)
        );
    }

    @DisplayName("플레이어가 21을 초과하지 않고 딜러가 21을 초과하면 플레이어는 승리한다.")
    @Test
    void testJudgeDealerBust() {
        // given
        Hand playerHand = HandFixture.createHandWithScoreTotal21();
        Player player = new Player(playerHand, new PlayerName("pobi"));

        Hand dealerHand = HandFixture.createHandWithScoreTotal21();
        dealerHand.append(new Card(CardRank.ACE, CardSuit.HEART));
        Dealer dealer = new Dealer(dealerHand);

        // when
        Result judge = player.judge(dealer);

        // then
        assertAll(
                () -> assertThat(judge.getWinCount()).isEqualTo(1),
                () -> assertThat(judge.getLoseCount()).isEqualTo(0)
        );
    }

    @DisplayName("플레이어와 딜러가 21을 초과하지 않으면 플레이어가 딜러보다 합이 높을 때 승리한다.")
    @ParameterizedTest
    @MethodSource("provideCards")
    void testJudge(Card card, int expectedWinCount, int expectedLoseCount) {
        // given
        Hand playerHand = new Hand();
        playerHand.append(card);
        Player player = new Player(playerHand, new PlayerName("pobi"));

        Hand dealerHand = new Hand();
        dealerHand.append(new Card(CardRank.FOUR, CardSuit.HEART));
        Dealer dealer = new Dealer(dealerHand);

        // when
        Result judge = player.judge(dealer);

        // then
        assertAll(
                () -> assertThat(judge.getWinCount()).isEqualTo(expectedWinCount),
                () -> assertThat(judge.getLoseCount()).isEqualTo(expectedLoseCount)
        );
    }
}
