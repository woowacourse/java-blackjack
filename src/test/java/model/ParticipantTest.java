package model;


import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ParticipantTest {

    @Test
    public void 결과_출력_정상_작동() {
        Participant participant = new Participant(new PlayerName("jason"));

        assertThat(participant.getNameValue()).isEqualTo("jason");
        assertThat(participant.getHand().isEmpty()).isTrue();
        assertThat(participant.getScore()).isEqualTo(0);
    }

    @Test
    public void 카드_드로우_정상_작동() {
        Card card = new Card(Shape.CLOVER, CardNumber.EIGHT);
        Participant participant = new Participant(new PlayerName("jason"));
        participant.draw(card);

        List<Card> deck = participant.getHand();
        Integer score = participant.getScore();

        assertThat(deck).contains(card);
        assertThat(deck.size()).isEqualTo(1);
        assertThat(score).isEqualTo(card.cardNumber().getScore());
    }
}
