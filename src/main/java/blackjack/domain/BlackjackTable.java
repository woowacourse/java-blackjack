package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.vo.BettingMoney;
import blackjack.domain.entry.vo.Name;
import java.util.List;
import java.util.Map;

public class BlackjackTable {

    private final Deck deck = new Deck();
    private final Players players;

    public BlackjackTable(Map<Name, BettingMoney> bettingPlayers) {
        this.players = new Players(createDealer(), bettingPlayers);
    }

    public void divideCardByAllPlayers() {
        players.divideCards(deck);
    }

    public boolean isFinishedPlayer(Name name) {
        return players.isFinishedBy(name);
    }

    public List<Card> hit(Name name, Command command) {
        if (command.isStay()) {
            return players.stayBy(name);
        }
        return players.hitBy(name, deck.draw());
    }

    public int countHitDealer() {
        int hitCount = 0;
        while (!players.isFinishedDealer()) {
            players.hitDealer(deck.draw());
            hitCount++;
        }
        return hitCount;
    }

    public List<Name> getPlayerNames() {
        return players.getNames();
    }

    public Map<Name, List<Card>> getPlayers() {
        return players.getPlayerCards();
    }

    public Map<String, Double> getPlayersEarningMoney() {
        return players.getPlayerEarningMoney();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public Map<Name, HoldCards> getAllPlayers() {
        return players.getAllPlayersCard();
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.initTwoCards(deck.draw(), deck.draw()));
    }
}
