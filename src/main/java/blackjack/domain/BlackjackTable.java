package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.BettingMoney;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Name;
import blackjack.domain.entry.Player;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackTable {

    private final Deck deck = new Deck();
    private final Players players;

    public BlackjackTable(Map<String, Integer> bettingPlayers) {
        this.players = new Players(createDealer(), toPlayers(bettingPlayers));
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.initTwoCards(deck.draw(), deck.draw()));
    }

    public boolean isFinishedPlayer(Name name) {
        return players.isFinished(name);
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

    public Map<Name, HoldCards> getPlayers() {
        return players.stay();
    }

    public Map<String, Double> getResult() {
        return players.getPlayerEarningMoney();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    private Map<Player, State> toPlayers(Map<String, Integer> bettingPlayers) {
        return bettingPlayers.keySet().stream()
            .collect(Collectors.toMap(name -> toPlayer(bettingPlayers, name), name -> toReady()));
    }

    private Player toPlayer(Map<String, Integer> bettingPlayers, String name) {
        return new Player(new Name(name), new BettingMoney(bettingPlayers.get(name)));
    }

    private State toReady() {
        return Ready.start(deck.draw(), deck.draw());
    }
}
