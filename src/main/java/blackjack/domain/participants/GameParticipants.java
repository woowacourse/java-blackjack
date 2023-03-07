package blackjack.domain.participants;

import blackjack.domain.Deck;
import blackjack.domain.GameReferee;
import blackjack.domain.ResultType;
import blackjack.domain.card.Card;

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

    public Map<String, List<Card>> findPlayerNameAndCards() {
        return players.findPlayerNameToCards();
    }

    public List<String> findPlayerNames() {
        return players.findPlayerNames();
    }

    public boolean isPlayerDrawable(final String playerName) {
        return players.isDrawable(playerName);
    }

    public void drawCardOf(final String playerName, final Card card) {
        players.drawCardOf(playerName, card);
    }

    public List<Card> findCardsByPlayerName(final String name) {
        return players.findCardsByPlayerName(name);
    }

    public int findDealerDrawCount(final Deck deck) {
        int drawCount = 0;
        while (dealer.isDrawable()) {
            dealer.drawCard(deck.popCard());
            drawCount++;
        }
        return drawCount;
    }

    public int findDealerScore() {
        return dealer.currentScore();
    }


    public Map<Map<String, List<Card>>, Integer> findPlayerStatusByName() {
        return players.findPlayerStatusByName();
    }


    //
    public ResultOfGame findResultOfGame() {
        final Map<String, ResultType> playerResult = players.calculateResult(new GameReferee(dealer));
        final Map<ResultType, Integer> dealerResult = dealer.calculateResult(playerResult);

        return new ResultOfGame(playerResult, dealerResult);
    }
}
