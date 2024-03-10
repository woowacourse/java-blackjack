package domain.game;

import domain.constant.GameResult;
import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.playingcard.PlayingCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.constant.GameResult.*;
import static domain.playingcard.PlayingCardShape.*;
import static domain.playingcard.PlayingCardValue.*;
import static org.assertj.core.api.Assertions.assertThat;

class GameResultsTest {
    @DisplayName("플레이어가 버스트면 딜러가 승리한다.")
    @Test
    void dealerWinWhenPlayerBust() {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        Player player = new Player(playerName, initBustHand());
        Dealer dealer = new Dealer(new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(DIAMOND, NINE))));

        // When
        GameResults gameResults = GameResults.of(dealer, List.of(player));
        GameResult dealerGameResult = gameResults.getDealerGameResults().get(0);
        GameResult playerGameResult = gameResults.getPlayerGameResults().get(playerName);

        // Then
        assertThat(dealerGameResult).isEqualTo(WIN);
        assertThat(playerGameResult).isEqualTo(LOSE);
    }

    @DisplayName("딜러가 블랙잭이면서 플레이어가 블랙잭이 아니면 딜러가 승리한다.")
    @Test
    void dealerWinWhenDealerIsBlackjackAndPlayerIsNotBlackJack() {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        Hand playerHand = new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(DIAMOND, NINE)));
        Player player = new Player(playerName, playerHand);
        Dealer dealer = new Dealer(initBlackJackHand());

        // When
        GameResults gameResults = GameResults.of(dealer, List.of(player));
        GameResult dealerGameResult = gameResults.getDealerGameResults().get(0);
        GameResult playerGameResult = gameResults.getPlayerGameResults().get(playerName);

        // Then
        assertThat(dealerGameResult).isEqualTo(WIN);
        assertThat(playerGameResult).isEqualTo(LOSE);
    }

    @DisplayName("딜러의 손패합이 플레이어의 손패합 보다 크면 딜러가 승리한다.")
    @Test
    void dealerWinWhenDealersTotalScoreIsBiggerThenPlayerTotalScore() {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        Hand playerHand = new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(DIAMOND, TWO)));
        Player player = new Player(playerName, playerHand);
        Hand dealerHand = new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(CLOVER, QUEEN)));
        Dealer dealer = new Dealer(dealerHand);

        // When
        GameResults gameResults = GameResults.of(dealer, List.of(player));
        GameResult dealerGameResult = gameResults.getDealerGameResults().get(0);
        GameResult playerGameResult = gameResults.getPlayerGameResults().get(playerName);

        // Then
        assertThat(dealerGameResult).isEqualTo(WIN);
        assertThat(playerGameResult).isEqualTo(LOSE);
    }

    @DisplayName("딜러가 버스트이면서 플레이어가 버스트가 아니면 플레이어가 승리한다.")
    @Test
    void playerWinWhenDealerIsBustAndPlayerIsNotBust() {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        Hand playerHand = new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(DIAMOND, TWO)));
        Player player = new Player(playerName, playerHand);
        Dealer dealer = new Dealer(initBustHand());

        // When
        GameResults gameResults = GameResults.of(dealer, List.of(player));
        GameResult dealerGameResult = gameResults.getDealerGameResults().get(0);
        GameResult playerGameResult = gameResults.getPlayerGameResults().get(playerName);

        // Then
        assertThat(dealerGameResult).isEqualTo(LOSE);
        assertThat(playerGameResult).isEqualTo(WIN);
    }

    @DisplayName("플레이어가 블랙잭이면서 딜러가 블랙잭이 아니면 플레이어가 승리한다.")
    @Test
    void playerWinWhenPlayerIsBlackjackAndDealerIsNotBlackjack() {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        Player player = new Player(playerName, initBlackJackHand());
        Hand dealerHand = new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(DIAMOND, TWO)));
        Dealer dealer = new Dealer(dealerHand);

        // When
        GameResults gameResults = GameResults.of(dealer, List.of(player));
        GameResult dealerGameResult = gameResults.getDealerGameResults().get(0);
        GameResult playerGameResult = gameResults.getPlayerGameResults().get(playerName);

        // Then
        assertThat(dealerGameResult).isEqualTo(LOSE);
        assertThat(playerGameResult).isEqualTo(WIN);
    }

    @DisplayName("플레이어의 손패합이 딜러의 손패합보다 크면 플레이어가 승리한다.")
    @Test
    void playerWinWhenPlayerTotalScoreIsBiggerThenDealersTotalScore() {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        Hand playerHand = new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(CLOVER, QUEEN)));
        Player player = new Player(playerName, playerHand);
        Hand dealerHand = new Hand(List.of(new PlayingCard(DIAMOND, TEN), new PlayingCard(DIAMOND, TWO)));
        Dealer dealer = new Dealer(dealerHand);

        // When
        GameResults gameResults = GameResults.of(dealer, List.of(player));
        GameResult dealerGameResult = gameResults.getDealerGameResults().get(0);
        GameResult playerGameResult = gameResults.getPlayerGameResults().get(playerName);

        // Then
        assertThat(dealerGameResult).isEqualTo(LOSE);
        assertThat(playerGameResult).isEqualTo(WIN);
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부 판정을 내린다.")
    @Test
    void determineDrawWhenPlayerAndDealerIsBlackjack() {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        Player player = new Player(playerName, initBlackJackHand());
        Dealer dealer = new Dealer(initBlackJackHand());

        // When
        GameResults gameResults = GameResults.of(dealer, List.of(player));
        GameResult dealerGameResult = gameResults.getDealerGameResults().get(0);
        GameResult playerGameResult = gameResults.getPlayerGameResults().get(playerName);

        // Then
        assertThat(dealerGameResult).isEqualTo(DRAW);
        assertThat(playerGameResult).isEqualTo(DRAW);
    }

    private static Hand initBustHand() {
        return new Hand(List.of(
                new PlayingCard(DIAMOND, KING),
                new PlayingCard(CLOVER, QUEEN),
                new PlayingCard(HEART, NINE))
        );
    }

    private static Hand initBlackJackHand() {
        return new Hand(List.of(
                new PlayingCard(DIAMOND, DEFAULT_ACE),
                new PlayingCard(CLOVER, QUEEN)
        ));
    }
}
