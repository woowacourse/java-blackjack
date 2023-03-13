package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Players;
import blackjack.view.DrawCommand;

import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    private BlackjackGame(final Players players, final Deck deck) {
        dealer = new Dealer();
        this.players = players;
        this.deck = deck;
    }

    public static BlackjackGame of(final List<String> playerNames, final Deck deck) {
        return new BlackjackGame(Players.from(playerNames), deck);
    }

    public void distributeInitialCards() {
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());
        players.distributeInitialCards(deck);
    }

    public Card findDealerInitialCard() {
        return dealer.getCards()
                .get(0);
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

    public void drawCardOfPlayerByName(final String playerName, final DrawCommand drawCommand) {
        if (drawCommand == DrawCommand.DRAW) {
            players.drawCardOfPlayerByName(playerName, deck.popCard());
        }
    }

    public List<Card> findCardsOfPlayerByName(final String playerName) {
        return players.findCardsOfPlayerByName(playerName);
    }

    public int findDealerDrawCount() {
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

    public List<Card> findDealerCard() {
        return dealer.getCards();
    }

    public Score findDealerScore() {
        return dealer.currentScore();
    }

    public Score findScoreOfPlayerByName(final String playerName) {
        return players.findScoreOfPlayerByName(playerName);
    }

    public ResultOfGame findResultOfGame() {
        final Map<String, ResultType> playerResult = players.calculateResult(new GameReferee(dealer));
        final Map<ResultType, Integer> dealerResult = Dealer.calculateResult(playerResult);
        return new ResultOfGame(playerResult, dealerResult);
    }

}
