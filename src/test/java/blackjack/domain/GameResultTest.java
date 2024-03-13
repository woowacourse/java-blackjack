package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("플레이어 승 : 플레이어 카드패 > 딜러 카드패")
    @Test
    void should_returnWin_When_PlayerHands_Higher_Than_DealerHands() {
        Player player1 = new Player("pobi", 1);
        Players players = new Players(List.of(player1));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.valueOf(0));
        testPlayer.addCard(Card.valueOf(8));

        dealer.addCard(Card.valueOf(0));
        dealer.addCard(Card.valueOf(7));
        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getTargetResultCount(Result.WIN)).isOne();
    }

    @DisplayName("플레이어 승 : 플레이어 - NonBurst, 딜러 - Burst")
    @Test
    void should_returnWin_When_PlayerNonBurst_DealerBurst() {
        Player player1 = new Player("pobi", 1);
        Players players = new Players(List.of(player1));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.valueOf(0));
        testPlayer.addCard(Card.valueOf(8));

        dealer.addCard(Card.valueOf(9));
        dealer.addCard(Card.valueOf(9));
        dealer.addCard(Card.valueOf(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player1)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 블랙 잭 : 플레이어 - BLACK_JACK, 딜러 - NON_BLACK_JACK")
    @Test
    void should_returnBlackJack_When_PlayerBlackJack_DealerNonBlackJACK() {
        Player player1 = new Player("pobi", 1);
        Players players = new Players(List.of(player1));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.valueOf(0));
        testPlayer.addCard(Card.valueOf(9));

        dealer.addCard(Card.valueOf(9));
        dealer.addCard(Card.valueOf(8));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player1)).isEqualTo(Result.BLACK_JACK);
    }

    @DisplayName("플레이어 패 : 플레이어 카드패 < 딜러 카드패")
    @Test
    void should_returnLose_When_PlayerHands_Lower_Than_DealerHands() {
        Player player1 = new Player("pobi", 1);
        Players players = new Players(List.of(player1));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.valueOf(0));
        testPlayer.addCard(Card.valueOf(1));

        dealer.addCard(Card.valueOf(0));
        dealer.addCard(Card.valueOf(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player1)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어 패 : 플레이어 - Burst, 딜러 - NonBurst")
    @Test
    void should_returnLose_When_PlayerBurst_DealerNonBurst() {
        Player player1 = new Player("pobi", 1);
        Players players = new Players(List.of(player1));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.valueOf(9));
        testPlayer.addCard(Card.valueOf(9));
        testPlayer.addCard(Card.valueOf(9));

        dealer.addCard(Card.valueOf(9));
        dealer.addCard(Card.valueOf(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player1)).isEqualTo(Result.LOSE);
    }

    @DisplayName("무승부 : 플레이어 카드패 == 딜러 카드패")
    @Test
    void should_returnDraw_When_PlayerHands_Equal_DealerHands() {
        Player player1 = new Player("pobi", 1);
        Players players = new Players(List.of(player1));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.valueOf(0));

        dealer.addCard(Card.valueOf(0));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player1)).isEqualTo(Result.DRAW);
    }

    @DisplayName("무승부 : 플레이어- Burst, 딜러- Burst")
    @Test
    void should_returnDraw_When_Both_Burst() {
        Player player1 = new Player("pobi", 1);
        Players players = new Players(List.of(player1));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.valueOf(9));
        testPlayer.addCard(Card.valueOf(9));
        testPlayer.addCard(Card.valueOf(9));

        dealer.addCard(Card.valueOf(9));
        dealer.addCard(Card.valueOf(9));
        dealer.addCard(Card.valueOf(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player1)).isEqualTo(Result.DRAW);
    }

    @DisplayName("무승부 : 플레이어- BLACK_JACK, 딜러- BLACK_JACK")
    @Test
    void should_returnDraw_When_Both_BlackJack() {
        Player player1 = new Player("pobi", 1);
        Players players = new Players(List.of(player1));
        Dealer dealer = new Dealer();

        Player testPlayer = players.getPlayers().get(0);
        testPlayer.addCard(Card.valueOf(0));
        testPlayer.addCard(Card.valueOf(9));

        dealer.addCard(Card.valueOf(0));
        dealer.addCard(Card.valueOf(9));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getGameResult().get(player1)).isEqualTo(Result.DRAW);
    }
}
