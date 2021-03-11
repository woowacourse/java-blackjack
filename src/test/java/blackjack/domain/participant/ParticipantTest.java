package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.FixtureCards.ACE_CLUBS;
import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantTest {
    private Participant participant;
    private Deck deck;

    @BeforeEach
    void setUp() {
        participant = new Player("wannte");
        deck = new Deck();
    }

    @Test
    @DisplayName("플레이어 카드 추가")
    void addCard() {
        Card card = deck.draw();
        participant.startRound(ACE_CLUBS, ACE_CLUBS);
        participant.addCard(card);
        assertThat(participant.getCards()).contains(card);
    }
}