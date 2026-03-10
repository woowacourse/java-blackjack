package model;


import java.util.List;
import model.dto.Card;
import model.dto.PlayerResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TestParticipant {

    @Test
    public void 결과_출력_정상_작동() {
        Participant participant = new Participant(new PlayerName("jason"));

        PlayerResult result = participant.getResult();

        assertThat(result.name()).isEqualTo("jason");
        assertThat(result.deck().isEmpty()).isTrue();
        assertThat(result.score()).isEqualTo(0);
    }

    @Test
    public void 카드_받기_정상_작동() {
        Card card = new Card(Shape.CLOVER, CardNumber.EIGHT);
        Participant participant = new Participant(new PlayerName("jason"));
        participant.addCard(card);

        List<String> deck = participant.getResult().deck();

        assertThat(deck).contains(card.getString());
        assertThat(deck.size()).isEqualTo(1);
    }

    @Test
    public void 버스트_판정_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));
        participant.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.KING));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        assertThat(participant.isBust()).isFalse();

        participant.addCard(new Card(Shape.CLOVER, CardNumber.ACE));
        assertThat(participant.isBust()).isTrue();
    }

}
