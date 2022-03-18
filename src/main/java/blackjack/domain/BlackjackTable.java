package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.domain.entry.Participants;
import blackjack.domain.entry.BettingMoney;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackTable {
    private final Deck deck;
    private final Participants participants;

    public BlackjackTable(Map<String, Integer> players) {
        this.deck = new Deck();
        this.participants = new Participants(createDealer(), toPlayers(players));
    }

    public void hit(Participant participant) {
        participant.addCard(deck.draw());
    }

    public Map<Player, Integer> countGameResult() {
        return participants.getGameResults();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.initTwoCards(deck.draw(), deck.draw()));
    }

    private List<Player> toPlayers(Map<String, Integer> players) {
        return players.keySet().stream()
            .map(name -> toPlayer(players, name))
            .collect(Collectors.toList());
    }

    private Player toPlayer(Map<String, Integer> players, String name) {
        return new Player(name, new BettingMoney(players.get(name)), HoldCards.initTwoCards(deck.draw(), deck.draw()));
    }
}
