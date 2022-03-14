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

public class BlackjackGame {
    public static final int BLACKJACK_NUMBER = 21;

    private final Deck deck;
    private final Players players;

    public BlackjackGame(List<String> names) {
        this.deck = Deck.of(Card.createDeck());
        this.players = new Players(createDealer(), toPlayers(names));
    }

    public void isHitThenReceiveCard(Player player, Command command) {
        if (command == Command.HIT) {
            receiveOneMoreCard(player);
        }
    }

    private void receiveOneMoreCard(Player player) {
        player.putCard(deck.draw());
    }

    public boolean canDealerHit() {
        return players.isDealerHit(deck);
    }

    public boolean canHit(Player player, Command command) {
        return command == Command.HIT && player.countCards() < BLACKJACK_NUMBER;
    }

    public Map<Participant, Integer> getCardResult(List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(participant -> participant, Participant::countCards));
    }

    public Map<PlayerOutcome, List<Player>> getGameResult() {
        return players.getGameResult();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Participant> getParticipant() {
        return players.getParticipant();
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.init(deck.draw(), deck.draw()));
    }

    private List<Player> toPlayers(List<String> names) {
        return names.stream()
                .map(name -> new Player(name, HoldCards.init(deck.draw(), deck.draw())))
                .collect(Collectors.toList());
    }
}
