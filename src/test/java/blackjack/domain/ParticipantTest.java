package blackjack.domain;

import org.junit.jupiter.api.Test;

public class ParticipantTest {
    @Test
    void 참여자는_카드를_받을_수_있다() {
        Participant participant = new Player("이산", new Hand());
        Deck deck = new Deck();
        participant.receiveCard(deck.draw());
    }
}
