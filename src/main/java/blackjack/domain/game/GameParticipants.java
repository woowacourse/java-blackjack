package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Players;

import java.util.List;
import java.util.Map;

public class GameParticipants {

    private final Dealer dealer;
    private final Players players;

    public GameParticipants(final Players players) {
        dealer = new Dealer();
        this.players = players;
    }

    public void distributeInitialCards(final Deck deck) {
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());
        players.distributeInitialCards(deck);
    }

    public List<Card> findDealerCard() {
        return dealer.getCards();
    }

    public Map<String, List<Card>> findPlayerNameToCards() {
        return players.findPlayerNameToCards();
    }

    public List<String> findPlayerNames() {
        return players.findPlayerNames();
    }

    public boolean isPlayerDrawable(final String playerName) {
        return players.isPlayerDrawable(playerName);
    }

    public void drawCardOfPlayerByName(final String playerName, final Deck deck) {
        players.drawCardOfPlayerByName(playerName, deck.popCard());
    }

    public List<Card> findCardsOfPlayerByName(final String name) {
        return players.findCardsOfPlayerByName(name);
    }

    public int findDealerDrawCount(final Deck deck) {
        int drawCount = 0;
        while (dealer.isDrawable()) {
            dealer.drawCard(deck.popCard());
            drawCount++;
        }
        return drawCount;
    }

    public int findDealerDrawPoint() {
        return dealer.getDrawPoint();
    }

    public Score findDealerScore() {
        return dealer.currentScore();
    }

    public Map<Map<String, List<Card>>, Score> findPlayerStatusByName() {
        return players.findPlayerStatusByName();
    }

    public ResultOfGame findResultOfGame() {
        final Map<String, ResultType> playerResult = players.calculateResult(new GameReferee(dealer));
        final Map<ResultType, Integer> dealerResult = Dealer.calculateResult(playerResult);
        return new ResultOfGame(playerResult, dealerResult);
    }

    public Score findScoreOfPlayerByName(final String playerName) {
        return players.findScoreOfPlayerByName(playerName);
    }
}
