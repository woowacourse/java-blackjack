package model;


import java.util.List;
import dto.Card;
import dto.PlayerName;
import dto.PlayerResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TestParticipant {

    @Test
    public void 결과_출력_정상_작동() {
        Participant participant = new Participant(new PlayerName("jason"));

        PlayerResult result = participant.getResult();

        assertThat(result.name().value()).isEqualTo("jason");
        assertThat(result.hand().isEmpty()).isTrue();
        assertThat(result.score()).isEqualTo(0);
    }

    @Test
    public void 카드_드로우_정상_작동() {
        Card card = new Card(Shape.CLOVER, CardNumber.EIGHT);
        Participant participant = new Participant(new PlayerName("jason"));
        participant.draw(card);

        List<Card> deck = participant.getResult().hand();
        Integer score = participant.getResult().score();

        assertThat(deck).contains(card);
        assertThat(deck.size()).isEqualTo(1);

        assertThat(score).isEqualTo(card.cardNumber().getScore());

    }

}
