package domain;

import static domain.HandsTestFixture.sum10Size2;
import static domain.HandsTestFixture.sum18Size2;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size2;
import static domain.Result.LOSE;
import static domain.Result.TIE;
import static domain.Result.WIN;

import domain.participant.Dealer;
import domain.participant.Hands;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("참여자에게 카드 2장을 나눠준다.")
    void dealCards() {
        //given
        final Players players = Players.from(List.of("레디", "제제"));
        final Dealer dealer = new Dealer();

        //when
        dealer.initHands(players);

        //then
        Assertions.assertThat(players.getPlayers()).allMatch(player -> player.handsSize() == 2);
    }

    @Test
    @DisplayName("참여자의 답변이 y라면 카드를 한장 추가한다.")
    void addOneCard() {
        //given
        final Player hitPlayer = new Player(new Name("레디"), Hands.createEmptyHands());
        final Player stayPlayer = new Player(new Name("제제"), Hands.createEmptyHands());

        final Players players = new Players(List.of(hitPlayer, stayPlayer));

        final Dealer dealer = new Dealer();
        dealer.initHands(players);

        //when
        dealer.deal(hitPlayer, Answer.HIT);
        dealer.deal(stayPlayer, Answer.STAY);

        //then
        Assertions.assertThat(hitPlayer.handsSize()).isEqualTo(3);
        Assertions.assertThat(stayPlayer.handsSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 17이상이 될때까지 카드를 추가한다.")
    void dealerDeal() {
        //given
        final Dealer dealer = new Dealer(sum10Size2);

        //when
        dealer.deal();

        //then
        Assertions.assertThat(dealer.countAddedHands()).isPositive();
        Assertions.assertThat(dealer.handsSum()).isGreaterThanOrEqualTo(17);
    }

    @DisplayName("딜러의 카드의 합이 17이상이라면 카드를 추가하지 않는다")
    @Test
    void dealerNoDeal() {
        //given
        final Dealer dealer = new Dealer(sum18Size2);

        //when
        dealer.deal();

        //then
        Assertions.assertThat(dealer.countAddedHands()).isZero();
        Assertions.assertThat(dealer.handsSum()).isGreaterThanOrEqualTo(17);
    }

    @DisplayName("딜러의 승패무를 판단한다.")
    @Test
    void dealerResult() {
        // given
        Player loser1 = new Player(new Name("레디"), sum18Size2);
        Player loser2 = new Player(new Name("피케이"), sum18Size2);
        Player winner = new Player(new Name("제제"), sum21Size2);
        Player tier = new Player(new Name("브라운"), sum20Size3);

        Players players = new Players(List.of(loser1, loser2, winner, tier));
        Dealer dealer = new Dealer(sum20Size3);

        // when
        Map<Result, Integer> expected = Map.of(WIN, 2, LOSE, 1, TIE, 1);

        // then
        Assertions.assertThat(dealer.getDealerResult(players)).isEqualTo(expected);
    }
}
