package domain;

import java.util.List;
import java.util.Objects;

public class Player extends Participant<Player>{

    public Player(ParticipantName participantName, Cards cards) {
        super(participantName, cards);
    }

    @Override
    public Player createParticipant(List<Card> providedCards) {
        return new Player(participantName, cards.addCards(providedCards));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(participantName, player.participantName) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantName, cards);
    }
}
