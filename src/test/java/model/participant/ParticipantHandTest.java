package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParticipantHandTest {
    @Test
    public void 카드_넣기_정상_작동() {
        ParticipantHand participantHand = new ParticipantHand();

        Card card = new Card(Shape.CLOVER, CardNumber.ACE);
        participantHand.addDeck(card);

        List<Card> cards = participantHand.getDeck();
        String firstCard = cards.getFirst().getString();

        assertThat(cards.size()).isEqualTo(1);
        assertThat(card.getString()).isEqualTo(firstCard);
    }

    @Test
    public void 카드_점수_정상_작동() {
        ParticipantHand participantHand = new ParticipantHand();

        participantHand.addDeck(new Card(Shape.CLOVER, CardNumber.ACE));
        assertThat(participantHand.getScore()).isEqualTo(11);

        participantHand.addDeck(new Card(Shape.CLOVER, CardNumber.KING));
        assertThat(participantHand.getScore()).isEqualTo(21);

        participantHand.addDeck(new Card(Shape.DIAMOND, CardNumber.ACE));
        assertThat(participantHand.getScore()).isEqualTo(12);
    }

    @Test
    public void 중복_카드_추가_예외() {
        ParticipantHand participantHand = new ParticipantHand();
        Card card = new Card(Shape.CLOVER, CardNumber.ACE);

        participantHand.addDeck(card);
        assertThatThrownBy(() -> participantHand.addDeck(card))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATED_CARD_IN_DECK.getMessage());

        assertThatThrownBy(() -> participantHand.addDeck(new Card(Shape.CLOVER, CardNumber.ACE)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATED_CARD_IN_DECK.getMessage());
    }

    @Test
    public void ACE_제외_카드_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.NINE));

        Assertions.assertThat(participant.getScore()).isEqualTo(9);

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));

        Assertions.assertThat(participant.getScore()).isEqualTo(19);
    }

    @Test
    public void ACE_한_장일_때_카드_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        Assertions.assertThat(participant.getScore()).isEqualTo(11);
    }

    @Test
    public void ACE_두_장일_때__카드_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));
        participant.addCard(new Card(Shape.HEART, CardNumber.ACE));

        Assertions.assertThat(participant.getScore()).isEqualTo(12);
    }

    @Test
    public void 특정_값_이상일_때_ACE_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.NINE));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        Assertions.assertThat(participant.getScore()).isEqualTo(20);
    }

    @Test
    public void 특정_값_이하일_때_ACE_점수_계산_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.NINE));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        Assertions.assertThat(participant.getScore()).isEqualTo(20);
    }

    @Test
    public void 버스트_판정_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));
        participant.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.KING));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        Assertions.assertThat(participant.isBust()).isFalse();

        participant.addCard(new Card(Shape.CLOVER, CardNumber.ACE));

        Assertions.assertThat(participant.isBust()).isTrue();
    }

    @Test
    public void 블랙잭_판정_정상_작동() {
        Participant participant = new Participant(new PlayerName("player"));

        participant.addCard(new Card(Shape.DIAMOND, CardNumber.QUEEN));
        participant.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));

        Assertions.assertThat(participant.isBlackJack()).isTrue();

        Participant participant2 = new Participant(new PlayerName("player2"));

        participant2.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));
        participant2.addCard(new Card(Shape.DIAMOND, CardNumber.NINE));
        participant2.addCard(new Card(Shape.HEART, CardNumber.ACE));

        Assertions.assertThat(participant2.isBlackJack()).isFalse();
    }
}
