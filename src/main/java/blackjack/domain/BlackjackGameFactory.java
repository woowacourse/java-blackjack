package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;
import java.util.List;

public class BlackjackGameFactory {

    private final Deck deck;

    public BlackjackGameFactory(final Deck deck) {
        this.deck = deck;
    }

    public BlackjackGame createGame(final List<String> names, final List<Integer> amounts) {
        Participants participants = Participants.of(Players.from(names, amounts));
        return new BlackjackGame(deck, participants);
    }
}
