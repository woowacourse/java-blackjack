package model;


import java.util.List;
import model.dto.PlayerResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ParticipantTest {

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
    public void ACE_제외_카드_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.NINE));

        assertThat(participant.getResult().score()).isEqualTo(9);

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));

        assertThat(participant.getResult().score()).isEqualTo(19);
    }

    @Test
    public void ACE_한_장일_때_카드_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        assertThat(participant.getResult().score()).isEqualTo(11);
    }

    @Test
    public void ACE_두_장일_때__카드_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));
        participant.addCard(new Card(Shape.HEART, CardNumber.ACE));

        assertThat(participant.getResult().score()).isEqualTo(12);
    }

    @Test
    public void 특정_값_이상일_때_ACE_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.NINE));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        assertThat(participant.getResult().score()).isEqualTo(20);
    }

    @Test
    public void 특정_값_이하일_때_ACE_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.NINE));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        assertThat(participant.getResult().score()).isEqualTo(20);
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

    @Test
    public void 블랙잭_판정_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        assertThat(participant.isBlackJack()).isTrue();

        Participant participant2 = new Participant(new PlayerName("player2"));

        participant2.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));
        participant2.addCard(new Card(Shape.DIAMOND, CardNumber.NINE));
        participant2.addCard(new Card(Shape.HEART, CardNumber.ACE));

        assertThat(participant2.isBlackJack()).isFalse();
    }
}
