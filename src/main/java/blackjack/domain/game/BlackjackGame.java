package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participants.BettingMoney;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Money;
import blackjack.domain.participants.Players;

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

    public void placePlayerBets(final String playerName, final BettingMoney bettingMoney) {
        players.placeBetsByName(playerName, bettingMoney);
    }

    public void distributeInitialCards() {
        dealer.drawInitialCards(deck.popCard(), deck.popCard());
        players.distributeInitialCards(deck);
    }

    public Card findDealerInitialCard() {
        return dealer.getInitialCard();
    }

    public List<String> findPlayerNames() {
        return players.findPlayerNames();
    }

    public boolean isPlayerDrawable(final String playerName) {
        return players.isPlayerDrawable(playerName);
    }

    public void drawCardOfPlayerByName(final String playerName) {
        players.drawCardOfPlayerByName(playerName, deck.popCard());
    }

    public void stayCardOfPlayerByName(final String playerName) {
        players.stayCardOfPlayerByName(playerName);
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

    public List<Card> findDealerCard() {
        return dealer.getCards();
    }

    public Score findDealerScore() {
        return dealer.score();
    }

    public Score findScoreOfPlayerByName(final String playerName) {
        return players.findScoreOfPlayerByName(playerName);
    }

    public Map<String, Money> findRevenueOfPlayers() {
        return players.findRevenueOfPlayers(dealer);
    }

    public Money findRevenueOfDealer() {
        final Map<String, Money> revenueOfPlayers = findRevenueOfPlayers();
        final Money sumOfPlayerRevenue = revenueOfPlayers.values()
                .stream()
                .reduce(new Money(0), Money::add);

        return sumOfPlayerRevenue.multiple(-1);
    }

}
