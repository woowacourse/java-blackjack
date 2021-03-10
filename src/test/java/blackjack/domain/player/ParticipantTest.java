package blackjack.domain.player;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Score;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void createParticipant_blackJack() {
        Participant participant = Participant.of(
            "nabom",
            Card.of(CardNumber.ACE, Symbol.CLOVER),
            Card.of(CardNumber.KING, Symbol.CLOVER)
        );

        assertThat(participant.isBlackJack()).isTrue();
        assertThat(participant.drawable()).isFalse();
    }

    @Test
    void createParticipant_hit() {
        Participant participant = Participant.of(
            "nabom",
            Card.of(CardNumber.ACE, Symbol.CLOVER),
            Card.of(CardNumber.THREE, Symbol.CLOVER)
        );

        assertThat(participant.isBlackJack()).isFalse();
        assertThat(participant.drawable()).isTrue();
    }

    @Test
    @DisplayName("자신의 스코어가 21이하일 경우 계속 카드를 뽑을 수 있는 지 여부 확인")
    void drawCard() {
        Participant participant = Participant.of(
            "nabom",
            Card.of(CardNumber.ACE, Symbol.CLOVER),
            Card.of(CardNumber.THREE, Symbol.CLOVER)
        );

        participant.drawCard(Card.of(CardNumber.KING, Symbol.CLOVER));
        assertThat(participant.score()).isEqualTo(Score.of(14));
        assertThat(participant.drawable()).isTrue();

        participant.drawCard(Card.of(CardNumber.EIGHT, Symbol.CLOVER));
        assertThat(participant.score()).isEqualTo(Score.of(22));
        assertThat(participant.drawable()).isFalse();
    }
}