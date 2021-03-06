package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantTest {
    private Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Player("wannte");
    }

    @Test
    @DisplayName("플레이어 카드 추가")
    void addCard() {
        Card card = Deck.draw();
        participant.addCard(card);
        assertThat(participant.getCards()).containsExactly(card);
    }
}