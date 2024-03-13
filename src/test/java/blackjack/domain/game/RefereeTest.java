package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackjack.domain.card.Rank.ACE;
import static blackjack.domain.card.Rank.EIGHT;
import static blackjack.domain.card.Rank.FIVE;
import static blackjack.domain.card.Rank.JACK;
import static blackjack.domain.card.Rank.KING;
import static blackjack.domain.card.Rank.NINE;
import static blackjack.domain.card.Rank.QUEEN;
import static blackjack.domain.card.Rank.SEVEN;
import static blackjack.domain.card.Rank.THREE;
import static blackjack.domain.card.Rank.TWO;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("심판")
public class RefereeTest {
    @Test
    @DisplayName("두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이기는지 테스트한다.")
    void playerWinTest() {
        Players players = Players.from(List.of("lemone"));
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();
        expectedPlayersResult.put("lemone", PlayerGameResult.WIN);

        players.draw(List.of(new Card(NINE, SPADE), new Card(QUEEN, CLUB)));
        dealer.draw(List.of(new Card(EIGHT, SPADE), new Card(QUEEN, CLUB)));
        referee.calculatePlayersResults(players, dealer);

        assertThat(referee.getPlayersNameAndResults())
                .isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("플레이어는 자신의 숫자 합이 21을 초과할 경우 패배한다.")
    void playerLoseWhenBustTest() {
        Players players = Players.from(List.of("lemone"));
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();
        expectedPlayersResult.put("lemone", PlayerGameResult.LOSE);

        players.draw(List.of(new Card(NINE, SPADE), new Card(QUEEN, CLUB), new Card(THREE, CLUB)));
        dealer.draw(List.of(new Card(EIGHT, SPADE), new Card(QUEEN, CLUB), new Card(TWO, SPADE)));
        referee.calculatePlayersResults(players, dealer);

        assertThat(referee.getPlayersNameAndResults())
                .isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("딜러의 합과 플레이어의 합이 같으면 무승부이다.")
    void playerDrawWhenSameBlackjackTest() {
        Players players = Players.from(List.of("lemone"));
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();
        expectedPlayersResult.put("lemone", PlayerGameResult.PUSH);

        players.draw(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB), new Card(THREE, CLUB)));
        dealer.draw(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB), new Card(THREE, SPADE)));
        referee.calculatePlayersResults(players, dealer);

        assertThat(referee.getPlayersNameAndResults())
                .isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("플레이어의 첫 두개의 카드의 합이 21이면 승리한다.")
    void playerDealCardsBlackjackTest() {
        Players players = Players.from(List.of("lemone"));
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();
        expectedPlayersResult.put("lemone", PlayerGameResult.WIN);

        players.draw(List.of(new Card(ACE, CLUB), new Card(JACK, CLUB)));
        dealer.draw(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB), new Card(FIVE, CLUB)));
        referee.calculatePlayersResults(players, dealer);

        assertThat(referee.getPlayersNameAndResults())
                .isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 낮을 경우 패배한다.")
    void playerLoseTest() {
        Players players = Players.from(List.of("lemone"));
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();
        expectedPlayersResult.put("lemone", PlayerGameResult.LOSE);

        players.draw(List.of(new Card(TWO, CLUB), new Card(JACK, CLUB)));
        dealer.draw(List.of(new Card(NINE, SPADE), new Card(SEVEN, CLUB), new Card(FIVE, CLUB)));
        referee.calculatePlayersResults(players, dealer);

        assertThat(referee.getPlayersNameAndResults())
                .isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 bust 이면, 플레이어가 패배한다.")
    void playerDealerAllBustPlayerLoseTest() {
        Players players = Players.from(List.of("lemone"));
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();
        expectedPlayersResult.put("lemone", PlayerGameResult.LOSE);

        players.draw(List.of(new Card(KING, CLUB), new Card(JACK, CLUB), new Card(THREE, CLUB)));
        dealer.draw(List.of(new Card(KING, CLUB), new Card(JACK, CLUB), new Card(THREE, CLUB)));
        referee.calculatePlayersResults(players, dealer);

        assertThat(referee.getPlayersNameAndResults())
                .isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("딜러가 bust이고 플레이가 bust가 아닐 경우, 플레이어가 승리한다.")
    void dealerBustPlayerNonBustWinTest() {
        Players players = Players.from(List.of("lemone"));
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();
        expectedPlayersResult.put("lemone", PlayerGameResult.WIN);

        players.draw(List.of(new Card(KING, CLUB), new Card(JACK, CLUB)));
        dealer.draw(List.of(new Card(KING, CLUB), new Card(JACK, CLUB), new Card(THREE, CLUB)));
        referee.calculatePlayersResults(players, dealer);

        assertThat(referee.getPlayersNameAndResults())
                .isEqualTo(expectedPlayersResult);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 블랙잭이 아닌 21이면 딜러가 승리한다.")
    void dealerBlackjackAndPlayerMaximumTest() {
        Players players = Players.from(List.of("lemone"));
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Referee referee = new Referee();
        Map<String, PlayerGameResult> expectedPlayersResult = new HashMap<>();
        expectedPlayersResult.put("lemone", PlayerGameResult.LOSE);

        players.draw(List.of(new Card(KING, CLUB), new Card(NINE, CLUB), new Card(TWO, CLUB)));
        dealer.draw(List.of(new Card(ACE, CLUB), new Card(JACK, CLUB)));
        referee.calculatePlayersResults(players, dealer);

        assertThat(referee.getPlayersNameAndResults()).isEqualTo(expectedPlayersResult);
    }
}
