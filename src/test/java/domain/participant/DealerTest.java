package domain.participant;

import static domain.GameResult.LOSE;
import static domain.GameResult.TIE;
import static domain.GameResult.WIN;
import static domain.HandsTestFixture.blackJack;
import static domain.HandsTestFixture.sum10Size2;
import static domain.HandsTestFixture.sum18Size2;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size3;
import static domain.amount.BetAmount.defaultBetAmount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.GameResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {


    @Test
    @DisplayName("참여자에게 카드 2장을 나눠준다.")
    void dealCards() {
        //given
        final Player player1 = new Player(new Name("레디"), defaultBetAmount);
        final Player player2 = new Player(new Name("제제"), defaultBetAmount);
        final Players players = new Players(List.of(player1, player2));
        final Dealer dealer = new Dealer();

        //when
        dealer.initHands(players);

        //then
        assertThat(players.getPlayers()).allMatch(player -> player.handsSize() == 2);
    }

    @Test
    @DisplayName("참여자의 답변이 y라면 카드를 한장 추가한다.")
    void addOneCard() {
        //given
        final Player hitPlayer = new Player(new Name("레디"), Hands.createEmptyHands(), defaultBetAmount);
        final Player stayPlayer = new Player(new Name("제제"), Hands.createEmptyHands(), defaultBetAmount);

        final Players players = new Players(List.of(hitPlayer, stayPlayer));

        final Dealer dealer = new Dealer();
        dealer.initHands(players);

        //when
        dealer.deal(hitPlayer, Answer.HIT);
        dealer.deal(stayPlayer, Answer.STAY);

        //then
        assertAll(
                () -> assertThat(hitPlayer.handsSize()).isEqualTo(3),
                () -> assertThat(stayPlayer.handsSize()).isEqualTo(2));
    }

    @Test
    @DisplayName("딜러의 카드의 합이 17이상이 될때까지 카드를 추가한다.")
    void dealerDeal() {
        //given
        final Dealer dealer = new Dealer(sum10Size2);

        //when
        dealer.deal();

        //then
        assertAll(
                () -> assertThat(dealer.countAddedHands()).isPositive(),
                () -> assertThat(dealer.handsSum()).isGreaterThanOrEqualTo(17));
    }

    @DisplayName("딜러의 카드의 합이 17이상이라면 카드를 추가하지 않는다")
    @Test
    void dealerNoDeal() {
        //given
        final Dealer dealer = new Dealer(sum18Size2);

        //when
        dealer.deal();

        //then
        assertAll(
                () -> assertThat(dealer.countAddedHands()).isZero(),
                () -> assertThat(dealer.handsSum()).isGreaterThanOrEqualTo(17));
    }

    @DisplayName("딜러의 승패무를 판단한다.")
    @Test
    void dealerResult() {
        // given
        final Player loser1 = new Player(new Name("레디"), sum18Size2, defaultBetAmount);
        final Player loser2 = new Player(new Name("피케이"), sum18Size2, defaultBetAmount);
        final Player winner = new Player(new Name("제제"), sum21Size3, defaultBetAmount);
        final Player tier = new Player(new Name("브라운"), sum20Size3, defaultBetAmount);

        final Players players = new Players(List.of(loser1, loser2, winner, tier));
        final Dealer dealer = new Dealer(sum20Size3);

        // when
        final Map<GameResult, Integer> expected = Map.of(WIN, 2, LOSE, 1, TIE, 1);

        // then
        assertThat(dealer.getDealerResult(players)).isEqualTo(expected);
    }

    @Test
    @DisplayName("처음 나눠준 카드 두장의 합이 21이라면 블랙잭이다.")
    void checkingBlackJack() {
        //given
        final Dealer dealer = new Dealer(sum20Size2);
        final Player blackJackPlayer = new Player(new Name("수달"), blackJack, defaultBetAmount);
        final Player noBlackJackPlayer = new Player(new Name("레디"), sum18Size2, defaultBetAmount);

        //when && then
        assertAll(() -> assertThat(dealer.isBlackJack()).isFalse(),
                () -> assertThat(blackJackPlayer.isBlackJack()).isTrue(),
                () -> assertThat(noBlackJackPlayer.isBlackJack()).isFalse());
    }
}
