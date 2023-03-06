package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("게임의 한 턴을 진행한다.")
    @Nested
    class playTurn {

        @Test
        @DisplayName("카드를 받음")
        void playTurnHit() {
            BlackJackGame blackJackGame = new BlackJackGame("split");
            Player currentPlayer = blackJackGame.getCurrentPlayer();
            blackJackGame.playTurn(currentPlayer, TurnAction.HIT);
            Assertions.assertThat(currentPlayer.showHand()).hasSize(1);
        }

        @Test
        @DisplayName("카드를 받지 않음")
        void playTurnStand() {
            BlackJackGame blackJackGame = new BlackJackGame("split");
            Player currentPlayer = blackJackGame.getCurrentPlayer();
            blackJackGame.playTurn(currentPlayer, TurnAction.STAND);
            Assertions.assertThat(currentPlayer.showHand()).hasSize(0);
        }
    }

    @DisplayName("딜러의 점수가 16이하인지 확인한다.")
    @Nested
    class isDealerUnderThresholds {

        @Test
        @DisplayName("16점일 때")
        void isDealerUnderThresholdsTrue() {
            BlackJackGame blackJackGame = new BlackJackGame("split");
            Dealer dealer = new Dealer();
            dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            dealer.dealt(new Card(Denomination.SIX, Suit.DIAMOND));
            Assertions.assertThat(blackJackGame.isDealerUnderThresholds(dealer)).isTrue();
        }

        @Test
        @DisplayName("17점일 때")
        void isDealerUnderThresholdsFalse() {
            BlackJackGame blackJackGame = new BlackJackGame("split");
            Dealer dealer = new Dealer();
            dealer.dealt(new Card(Denomination.JACK, Suit.DIAMOND));
            dealer.dealt(new Card(Denomination.SEVEN, Suit.DIAMOND));
            Assertions.assertThat(blackJackGame.isDealerUnderThresholds(dealer)).isFalse();
        }
    }


    @Test
    @DisplayName("플레이어들의 게임결과로 딜러의 게임결과를 생성한다.")
    void playTurn() {
        BlackJackGame blackJackGame = new BlackJackGame("split");
        GameResult dealerBoxResult = blackJackGame.getDealerGameResult(List.of(
            new GameResult(5, 0),
            new GameResult(0, 3))
        );
        Assertions.assertThat(dealerBoxResult.getWinCount()).isEqualTo(3);
        Assertions.assertThat(dealerBoxResult.getLoseCount()).isEqualTo(5);
    }
}
