package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    public static final int BLACKJACK_NUMBER = 21;

    private final Deck deck;
    private final GameParticipants gameParticipants;

    public BlackjackGame(List<String> names) {
        this.deck = Deck.of(Card.createDeck());
        this.gameParticipants = new GameParticipants(createDealer(), toPlayers(names));
    }

    public void hit(Participant participant) {
        participant.putCard(deck.draw());
    }

    public Map<Participant, Integer> getCardResult(List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(participant -> participant, Participant::countCards));
    }

    public Map<PlayerOutcome, List<Player>> getGameResult() {
        return gameParticipants.getGameResult();
    }

    public List<Player> getPlayers() {
        return gameParticipants.getPlayers();
    }

    public List<Participant> getParticipant() {
        return gameParticipants.getParticipant();
    }

    public Dealer getDealer() {
        return gameParticipants.getDealer();
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.init(List.of(deck.draw(), deck.draw())));
    }

    private Players toPlayers(List<String> names) {
        return new Players(names.stream()
                .map(name -> new Player(name, HoldCards.init(List.of(deck.draw(), deck.draw()))))
                .collect(Collectors.toList()));
    }
}
