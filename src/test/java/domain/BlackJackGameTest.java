package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    @DisplayName("참가자에게 카드를 지급한다.")
    void dealTest() {
        Dealer dealer = new Dealer();
        BlackJackGame blackJackGame = new BlackJackGame("echo");
        blackJackGame.progressTurn(dealer, TurnAction.HIT);
        List<Card> readyCards = dealer.faceUpInitialHand();
        Assertions.assertThat(readyCards).containsExactly(new Card(Denomination.ACE, Suit.SPADE));
    }

    @Test
    @DisplayName("게임이 준비완료된 상태를 반환한다.")
    void readyResultTest() {
        BlackJackGame blackJackGame = new BlackJackGame("echo");
        blackJackGame.dealInitialHand();
        List<Player> readyResults = blackJackGame.getPlayersAndDealerAtFirst();
        Player dealer = readyResults.get(0);
        Player echo = readyResults.get(1);
        Assertions.assertThat(readyResults).containsExactly(new Dealer(), new Player("echo"));
        Assertions.assertThat(dealer.faceUpInitialHand()).hasSize(1);
        Assertions.assertThat(echo.faceUpInitialHand()).hasSize(2);
    }
}
