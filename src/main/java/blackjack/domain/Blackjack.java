package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.domain.entry.Players;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Blackjack {
    private static final int BLACKJACK_NUMBER = 21;

    private final Deck deck;
    private final Players players;

    public Blackjack(List<String> names) {
        this.deck = Deck.of(Card.createDeck());
        this.players = new Players(createDealer(), toPlayers(names));
    }

    public void receiveOneMoreCard(Player player) {
        player.putCard(deck.draw());
    }

    public boolean isDealerReceiveOneMoreCard() {
        return players.isDealerHit(deck);
    }

    public boolean canHit(Player player, Command command) {
        return command == Command.HIT && player.countCards() < BLACKJACK_NUMBER;
    }

    public Map<Outcome, List<Player>> getGameResult() {
        return players.getGameResult();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Participant> getParticipant() {
        return players.getParticipant();
    }

    private Dealer createDealer() {
        return new Dealer(new HoldCards(deck.draw(), deck.draw()));
    }

    private List<Player> toPlayers(List<String> names) {
        return names.stream()
                .map(name -> new Player(name, new HoldCards(deck.draw(), deck.draw())))
                .collect(Collectors.toList());
    }
}