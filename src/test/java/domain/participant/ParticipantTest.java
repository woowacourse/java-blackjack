package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 카드를_한_장_받는다() {
        // given
        Participant participant = Player.of("name");

        // when
        participant.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));

        // then
        assertThat(participant.getCardCount()).isEqualTo(1);
    }

    @Test
    void 참여자의_카드_점수를_반환한다() {
        // given
        Participant participant = Player.of("name");
        participant.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));
        participant.receive(Card.of(TrumpNumber.SIX, TrumpShape.DIAMOND));
        participant.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.SPADE));

        // when
        int score = participant.getScore();

        // then
        assertThat(score).isEqualTo(18);
    }

    @Test
    void 카드_개수를_반환한다() {
        // given
        Participant participant = Player.of("name");
        participant.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));
        participant.receive(Card.of(TrumpNumber.SIX, TrumpShape.DIAMOND));
        participant.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.SPADE));

        // when
        int cardCount = participant.getCardCount();

        // then
        assertThat(cardCount).isEqualTo(3);
    }
}