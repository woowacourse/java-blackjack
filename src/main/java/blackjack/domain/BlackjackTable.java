package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.BettingMoney;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Name;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackTable {

    private final Deck deck;
    private final Dealer dealer;
    private final Map<Name, BettingMoney> bettingPlayers;
    private final Map<Name, State> playerState = new HashMap<>();

    public BlackjackTable(Map<String, Integer> bettingPlayers) {
        deck = new Deck();
        this.bettingPlayers = new HashMap<>();
        for (String name : bettingPlayers.keySet()) {
            this.bettingPlayers.put(new Name(name), new BettingMoney(bettingPlayers.get(name)));
        }
        dealer = new Dealer(HoldCards.initTwoCards(deck.draw(), deck.draw()));
    }

    public boolean isReady() {
        return playerState.values().stream()
            .allMatch(State::isReady);
    }

    public void divideCard() {
        for (Name name : bettingPlayers.keySet()) {
            State state = Ready.start(deck.draw(), deck.draw());
            this.playerState.put(name, state);
        }
    }

    public boolean isFinished(String name) {
        State state = playerState.get(new Name(name));
        return state.isFinished();
    }

    public List<Card> hit(Name name, String command) {
        State state = playerState.get(name);
        if (Command.find(command).isStay()) {
            state = state.stay();
            playerState.put(name, state);
            return state.getHoldCards().getCards();
        }
        state = state.draw(deck.draw());
        playerState.put(name, state);
        return state.getHoldCards().getCards();
    }

    private HoldCards getCards(State state) {
        return state.getHoldCards();
    }

    public boolean isFinishedDealer() {
        return !dealer.canHit();
    }

    public void divideCardByDealer() {
        dealer.addCard(deck.draw());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Map<Name, HoldCards> getPlayers() {
        return playerState.keySet().stream()
            .collect(Collectors.toMap(name -> name, name -> getCards(playerState.get(name))));
    }

    public Map<String, Double> getResult() {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Name name : bettingPlayers.keySet()) {
            BettingMoney bettingMoney = bettingPlayers.get(name);
            State state = playerState.get(name);
            result.put(name.getValue(), state.profit(bettingMoney));
        }
        return result;
    }
}
