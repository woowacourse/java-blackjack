package domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.state.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.NameParser;

public class CheckWinnerTest {
    private final ResultCalculator resultCalculator = new ResultCalculator();

    @Test
    @DisplayName("점수가 같으면 무승부이다.")
    void sameScoreLoseResult() {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = getFirstPlayer(players);
        addCards(dealer,
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.HEART, Rank.QUEEN));
        addCards(player,
                new Card(Suit.DIAMOND, Rank.JACK),
                new Card(Suit.CLUB, Rank.KING));

        GameResult gameResult = resultCalculator.calculate(dealer, players);

        assertEquals(Outcome.DRAW, gameResult.getPlayerOutcome(player.getName()));
    }

    @Test
    @DisplayName("딜러 점수가 더 크면 플레이어는 패배한다")
    void loseResult() {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = getFirstPlayer(players);
        addCards(dealer,
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.HEART, Rank.QUEEN));
        addCards(player,
                new Card(Suit.DIAMOND, Rank.TEN),
                new Card(Suit.CLUB, Rank.EIGHT));

        GameResult gameResult = resultCalculator.calculate(dealer, players);

        assertEquals(Outcome.LOSE, gameResult.getPlayerOutcome(player.getName()));
    }

    @Test
    @DisplayName("플레이어 점수가 더 크면 플레이어는 승리한다")
    void winResult() {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = getFirstPlayer(players);
        addCards(dealer,
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.HEART, Rank.EIGHT));
        addCards(player,
                new Card(Suit.DIAMOND, Rank.KING),
                new Card(Suit.CLUB, Rank.QUEEN));

        GameResult gameResult = resultCalculator.calculate(dealer, players);

        assertEquals(Outcome.WIN, gameResult.getPlayerOutcome(player.getName()));
    }

    @Test
    @DisplayName("플레이어가 버스트면 딜러 버스트 여부와 상관없이 패배한다")
    void playerBustAlwaysLose() {
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList("pobi");
        Player player = getFirstPlayer(players);
        addCards(dealer,
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.HEART, Rank.QUEEN),
                new Card(Suit.CLUB, Rank.TWO));
        addCards(player,
                new Card(Suit.DIAMOND, Rank.KING),
                new Card(Suit.CLUB, Rank.QUEEN),
                new Card(Suit.HEART, Rank.TWO));

        GameResult gameResult = resultCalculator.calculate(dealer, players);

        assertEquals(Outcome.LOSE, gameResult.getPlayerOutcome(player.getName()));
    }

    private void addCards(Participant participant, Card... cards) {
        for (Card card : cards) {
            participant.getCardList().addCard(card);
        }
    }

    private Player getFirstPlayer(Players players) {
        final Player[] firstPlayer = new Player[1];
        players.forEachPlayer(player -> {
            if (firstPlayer[0] == null) {
                firstPlayer[0] = player;
            }
        });
        return firstPlayer[0];
    }
}
