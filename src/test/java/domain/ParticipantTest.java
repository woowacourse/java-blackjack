package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {

    @Test
    @DisplayName("카드를 출력한다")
    void createParticipant_thenDisplayCards() {
        //given
        Participant participant = Participant.from("power");
        participant.takeCard(Card.of(Shape.DIAMOND, Number.FIVE));
        participant.takeCard(Card.of(Shape.SPADE, Number.TWO));
        participant.takeCard(Card.of(Shape.CLUBS, Number.SIX));

        //when
        List<Card> cards = participant.displayCards();

        //then
        assertThat(cards)
                .isEqualTo(List.of(Card.of(Shape.DIAMOND, Number.FIVE)
                        , Card.of(Shape.SPADE, Number.TWO)
                        , Card.of(Shape.CLUBS, Number.SIX)));
    }
}
