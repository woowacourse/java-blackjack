import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Card;
import domain.Dealer;
import domain.GameResult;
import domain.Outcome;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.Rank;
import domain.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.NameParser;

public class CheckWinnerTest {
    @Test
    @DisplayName("점수가 같으면 무승부다")
    void drawResult() {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = players.getPlayer(0);
        addCards(dealer,
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.HEART, Rank.QUEEN));
        addCards(player,
                new Card(Suit.DIAMOND, Rank.JACK),
                new Card(Suit.CLUB, Rank.KING));

        GameResult gameResult = players.calculateResult(dealer);

        assertEquals(Outcome.DRAW, gameResult.getPlayerOutcome(player.getName()));
    }

    @Test
    @DisplayName("딜러 점수가 더 크면 플레이어는 패배한다")
    void loseResult() {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = players.getPlayer(0);
        addCards(dealer,
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.HEART, Rank.QUEEN));
        addCards(player,
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.CLUB, Rank.EIGHT));

        GameResult gameResult = players.calculateResult(dealer);

        assertEquals(Outcome.LOSE, gameResult.getPlayerOutcome(player.getName()));
    }

    @Test
    @DisplayName("플레이어 점수가 더 크면 플레이어는 승리한다")
    void winResult() {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = players.getPlayer(0);
        addCards(dealer,
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.EIGHT));
        addCards(player,
                new Card(Suit.DIAMOND, Rank.KING),
                new Card(Suit.CLUB, Rank.QUEEN));

        GameResult gameResult = players.calculateResult(dealer);

        assertEquals(Outcome.WIN, gameResult.getPlayerOutcome(player.getName()));
    }

    private void addCards(Participant participant, Card... cards) {
        for (Card card : cards) {
            participant.getCardList().addCard(card);
        }
    }
}
