package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.vo.BettingMoney;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.vo.Name;
import blackjack.domain.entry.Player;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackTable {

    private final Deck deck = new Deck();
    private final Players players;

    public BlackjackTable(Map<Name, BettingMoney> bettingPlayers) {
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

    public Map<Name, List<Card>> getPlayers() {
        return players.getPlayersCard();
    }

    public Map<String, Double> getResult() {
        return players.getPlayerEarningMoney();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    private Map<Player, State> toPlayers(Map<Name, BettingMoney> bettingPlayers) {
        return bettingPlayers.keySet().stream()
            .collect(Collectors.toMap(name -> new Player(name, bettingPlayers.get(name)), name -> toReady()));
    }

    private State toReady() {
        return Ready.start(deck.draw(), deck.draw());
    }
}
