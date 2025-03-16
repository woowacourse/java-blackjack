package domain.participants;

import static domain.card.Card.CLOVER_JACK;
import static domain.card.Card.CLOVER_KING;
import static domain.card.Card.CLOVER_NINE;
import static domain.card.Card.CLOVER_QUEEN;
import static domain.card.Card.CLOVER_THREE;
import static domain.card.Card.CLOVER_TWO;
import static domain.card.Card.DIA_JACK;
import static domain.card.Card.DIA_TEN;
import static domain.card.Card.HEART_ACE;
import static domain.card.Card.HEART_JACK;
import static domain.card.Card.HEART_QUEEN;
import static domain.card.Card.HEART_TEN;
import static domain.card.Card.SPADE_JACK;
import static domain.card.Card.SPADE_QUEEN;
import static domain.card.Card.SPADE_TEN;
import static domain.card.Card.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.game.BettingResultAmount;
import domain.game.BettingStatistics;
import domain.game.GameResult;
import domain.game.GameStatistics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {
    @Test
    @DisplayName("특정 이름을 가진 플레이어에게 카드를 제공한다.")
    void giveCardToGamerTest() {
        // given
        Map<PlayerName, BettingAmount> playersInfo = Map.of(
                new PlayerName("김"), new BettingAmount(10000),
                new PlayerName("이"), new BettingAmount(10000),
                new PlayerName("박"), new BettingAmount(10000)
        );
        Players players = new Players(playersInfo);
        PlayerName username = new PlayerName("이");
        Card card = HEART_JACK;
        // when
        players.giveCard(username, List.of(card));
        // then
        List<Card> cards = players.getPlayerCard(username);
        assertThat(cards).contains(card);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("특정 플레이어가 카드를 더 받을 수 있는지 확인합니다.")
    void canGetMoreCardTest(List<Card> cards, boolean expected) {
        //given
        Map<PlayerName, BettingAmount> playersInfo = Map.of(
                new PlayerName("김"), new BettingAmount(10000),
                new PlayerName("이"), new BettingAmount(10000),
                new PlayerName("박"), new BettingAmount(10000)
        );
        Players players = new Players(playersInfo);
        players.giveCard(new PlayerName("김"), cards);

        //when & then
        assertThat(players.isDrawable(new PlayerName("김"))).isEqualTo(expected);
    }

    public static Stream<Arguments> canGetMoreCardTest() {
        return Stream.of(
                Arguments.of(List.of(HEART_TEN, HEART_ACE), true),
                Arguments.of(List.of(HEART_TEN, HEART_ACE, CLOVER_QUEEN), true),
                Arguments.of(List.of(HEART_TEN, HEART_QUEEN, CLOVER_THREE), false),
                Arguments.of(List.of(HEART_TEN, DIA_JACK, CLOVER_TWO), false)
        );
    }

    @Test
    @DisplayName("플레이어가 모두 존재하는지 확인합니다.")
    void getPlayerInfoTest() {
        // given
        Map<PlayerName, BettingAmount> playersInfo = Map.of(
                new PlayerName("김"), new BettingAmount(10000),
                new PlayerName("이"), new BettingAmount(10000),
                new PlayerName("박"), new BettingAmount(10000)
        );
        Players players = new Players(playersInfo);
        // when
        Map<PlayerName, Gamer> playerInfo = players.getPlayersInfo();
        // then
        assertThat(playerInfo.keySet()).containsAll(
                Set.of(new PlayerName("김"), new PlayerName("이"), new PlayerName("박")));
    }

    @Test
    @DisplayName("정확한 게임 결과를 반환한다.")
    void calculateGameStatisticsTest() {
        // given
        Players players = getPlayers();
        Dealer dealer = new Dealer(new Deck(new ArrayList<>()));
        dealer.addCard(List.of(DIA_TEN, DIA_JACK));
        // when
        GameStatistics gameStatistics = players.calculateGameStatistics(dealer);
        // then
        assertThat(gameStatistics.getDealerDrawCount()).isEqualTo(1);
        assertThat(gameStatistics.getDealerLoseCount()).isEqualTo(1);
        assertThat(gameStatistics.getDealerWinCount()).isEqualTo(2);
        Map<PlayerName, GameResult> results = gameStatistics.getResults();
        assertThat(results.get(new PlayerName("a"))).isEqualTo(GameResult.WIN);
        assertThat(results.get(new PlayerName("b"))).isEqualTo(GameResult.LOSE);
        assertThat(results.get(new PlayerName("c"))).isEqualTo(GameResult.LOSE);
        assertThat(results.get(new PlayerName("d"))).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("정확한 배팅 금액 결과를 반환한다.")
    void calculateBettingStatisticsTest() {
        // given
        Players players = getPlayers();
        Dealer dealer = new Dealer(new Deck(new ArrayList<>()));
        dealer.addCard(List.of(DIA_TEN, DIA_JACK));
        // when
        BettingStatistics bettingStatistics = players.calculateBettingStatistics(dealer);
        // then
        assertThat(bettingStatistics.calculateDealerBettingResult()).isEqualTo(new BettingResultAmount(40000));
        Map<PlayerName, BettingResultAmount> bettingResult = bettingStatistics.getBettingResult();
        assertThat(bettingResult.get(new PlayerName("a"))).isEqualTo(new BettingResultAmount(10000));
        assertThat(bettingResult.get(new PlayerName("b"))).isEqualTo(new BettingResultAmount(-20000));
        assertThat(bettingResult.get(new PlayerName("c"))).isEqualTo(new BettingResultAmount(-30000));
        assertThat(bettingResult.get(new PlayerName("d"))).isEqualTo(new BettingResultAmount(0));
    }

    private Players getPlayers() {
        Map<PlayerName, BettingAmount> playersInfo = Map.of(
                new PlayerName("a"), new BettingAmount(10000),
                new PlayerName("b"), new BettingAmount(20000),
                new PlayerName("c"), new BettingAmount(30000),
                new PlayerName("d"), new BettingAmount(40000)
        );
        Players players = new Players(playersInfo);
        players.giveCard(new PlayerName("a"), List.of(HEART_TEN, HEART_ACE));
        players.giveCard(new PlayerName("b"), List.of(CLOVER_JACK, CLOVER_NINE));
        players.giveCard(new PlayerName("c"), List.of(SPADE_TEN, SPADE_TWO, SPADE_QUEEN));
        players.giveCard(new PlayerName("d"), List.of(SPADE_JACK, CLOVER_KING));
        return players;
    }
}