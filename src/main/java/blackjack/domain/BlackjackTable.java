package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Players;
import blackjack.domain.entry.vo.BettingMoney;
import blackjack.domain.entry.vo.Name;
import java.util.List;
import java.util.Map;

public class BlackjackTable {

    private final Deck deck = new Deck();
    private final Players players;

    public BlackjackTable(Map<Name, BettingMoney> bettingPlayers) {
        this.players = new Players(createDealer(), bettingPlayers);
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

    public int countHitByDealer() {
        int hitCount = 0;
        while (!players.isFinishedDealer()) {
            players.hitDealer(deck.draw());
            hitCount++;
        }
        return hitCount;
    }

    public List<Name> getPlayerNames() {
        return players.getPlayersName();
    }

    public List<Participant> getAllPlayers() {
        return players.getAllPlayers();
    }

    public Map<Participant, Double> getGameResult() {
        return players.getAllProfit();
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.initTwoCards(deck.draw(), deck.draw()));
    }
}
