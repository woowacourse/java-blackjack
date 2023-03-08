package blackjack.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class BlackJackGame {
    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public Participants getParticipants() {
        return participants;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Name> getPlayerNames() {
        return participants.getPlayers().stream()
                .map(Participant::getName)
                .collect(toList());
    }
}
