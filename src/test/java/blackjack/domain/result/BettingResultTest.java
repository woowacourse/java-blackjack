package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackjack.domain.result.PlayerResult.*;
import static org.assertj.core.api.Assertions.assertThat;

class BettingResultTest {

    @Test
    @DisplayName("플레이어가 우승 시, 베팅금액에 따른 수익을 얻는다.")
    void calculatePlayerWinProfit() {
        final int playerBet = 10000;
        final Map<String, Double> expected = Map.of("sun",  (double) playerBet);
        final Players players = Players.startWithTwoCards(List.of("sun"), List.of(playerBet),
                new Deck(List.of(
                        new Card(CardPattern.SPADE, CardNumber.JACK),
                        new Card(CardPattern.SPADE, CardNumber.TEN)
                )));

        final Map<Player, PlayerResult> playerResults = new HashMap<>();
        playerResults.put(players.getStatuses().get(0), WIN);

        final BettingResult bettingResult = new BettingResult(playerResults);

        assertThat(bettingResult.getPlayerResult()).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어가 패배 시, 베팅금액을 잃는다.")
    void calculatePlayerLossProfit() {
        final int playerBet = 10000;
        final Map<String, Double> expected = Map.of("sun",  (double) playerBet * -1);
        final Players players = Players.startWithTwoCards(List.of("sun"), List.of(playerBet),
                new Deck(List.of(
                        new Card(CardPattern.SPADE, CardNumber.JACK),
                        new Card(CardPattern.SPADE, CardNumber.TEN)
                )));

        final Map<Player, PlayerResult> playerResults = new HashMap<>();
        playerResults.put(players.getStatuses().get(0), LOSS);

        final BettingResult bettingResult = new BettingResult(playerResults);

        assertThat(bettingResult.getPlayerResult()).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어 블랙잭 우승 시, 수익 베팅금액의 1.5배")
    void calculateBlackjackProfit() {
        final int playerBet = 10000;
        final Map<String, Double> expected = Map.of("sun",  playerBet * 1.5);
        final Players players = Players.startWithTwoCards(List.of("sun"), List.of(playerBet),
                new Deck(List.of(
                        new Card(CardPattern.SPADE, CardNumber.ACE),
                        new Card(CardPattern.SPADE, CardNumber.TEN)
                )));

        final Map<Player, PlayerResult> playerResults = new HashMap<>();
        playerResults.put(players.getStatuses().get(0), BLACKJACK_WIN);

        final BettingResult bettingResult = new BettingResult(playerResults);

        assertThat(bettingResult.getPlayerResult()).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어의 수익에 따른 딜러 수익 계산")
    void calculateDealerProfit() {
        Players players = Players.startWithTwoCards(List.of("sun", "if"), List.of(3000,10000),
                new Deck(List.of(
                        new Card(CardPattern.SPADE, CardNumber.ACE),
                        new Card(CardPattern.SPADE, CardNumber.TWO),
                        new Card(CardPattern.SPADE, CardNumber.THREE),
                        new Card(CardPattern.SPADE, CardNumber.FOUR)
                )));

        final Map<Player, PlayerResult> playerResults = new HashMap<>();
        playerResults.put(players.getStatuses().get(0), WIN);
        playerResults.put(players.getStatuses().get(1), LOSS);
        final BettingResult bettingResult = new BettingResult(playerResults);

        double expected = 7000;

        assertThat(bettingResult.getDealerResult()).isEqualTo(expected);
    }
}
