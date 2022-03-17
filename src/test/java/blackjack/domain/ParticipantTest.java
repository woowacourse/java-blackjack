package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantTest {
    @DisplayName("참가자의 점수가 블랙잭일 경우 테스트")
    @Test
    void blackjack() {
        Participant player = new Player("pobi");

        player.addCard(Card.A_SPADE);
        player.addCard(Card.K_CLOVER);

        assertThat(player.score().isBlackjack()).isTrue();
    }

    @DisplayName("참가자의 점수가 버스트일 경우 테스트")
    @Test
    void burst() {
        Participant player = new Player("pobi");

        player.addCard(Card.K_SPADE);
        player.addCard(Card.K_CLOVER);
        player.addCard(Card.THREE_DIAMOND);

        assertThat(player.score().isBust()).isTrue();
    }
}
