package domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.state.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultCalculatorTest {
    @Test
    @DisplayName("플레이어가 버스트면 결과는 패배다")
    void playerBustLose() {
        ResultCalculator calculator = new ResultCalculator();
        Dealer dealer = new Dealer();
        Players players = new Players(java.util.List.of(new Player("pobi")));
        final Player[] playerRef = new Player[1];
        players.forEachPlayer(player -> playerRef[0] = player);

        dealer.getCardList().addCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.getCardList().addCard(new Card(Suit.HEART, Rank.EIGHT));
        playerRef[0].getCardList().addCard(new Card(Suit.CLUB, Rank.KING));
        playerRef[0].getCardList().addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        playerRef[0].getCardList().addCard(new Card(Suit.HEART, Rank.TWO));

        GameResult result = calculator.calculate(dealer, players);

        assertEquals(Outcome.LOSE, result.getPlayerOutcome(playerRef[0].getName()));
    }
}
