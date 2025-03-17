import card.Card;
import card.ParticipantCardDeck;
import card.CardNumber;
import card.CardSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParticipantCardDeckTest {

    @Test
    void 카드셋_정적_팩토리_메서드_확인_2() {
        //given
        ParticipantCardDeck participantCardDeck = ParticipantCardDeck.generateEmptySet();

        //when
        Assertions.assertThat(participantCardDeck.getCards().size()).isEqualTo(0);
    }

    @Test
    void 카드_넣기() {
        //given
        ParticipantCardDeck participantCardDeck = ParticipantCardDeck.generateEmptySet();
        Card card = new Card(CardNumber.TWO, CardSymbol.CLOVER);

        //when
        participantCardDeck.addCard(card);

        //then
        Assertions.assertThat(participantCardDeck.getCards().size()).isEqualTo(1);
    }

    @Test
    void 점수_계산_확인() {
        //given
        Card card1 = new Card(CardNumber.TWO, CardSymbol.CLOVER);
        Card card2 = new Card(CardNumber.EIGHT, CardSymbol.CLOVER);

        ParticipantCardDeck participantCardDeck = ParticipantCardDeck.generateEmptySet();
        participantCardDeck.addCard(card1);
        participantCardDeck.addCard(card2);

        //when & then
        Assertions.assertThat(participantCardDeck.calculateScore()).isEqualTo(10);
    }

    @Test
    void 에이스_점수_계산_확인() {
        //given
        Card aceCard = new Card(CardNumber.ACE, CardSymbol.CLOVER);
        Card card = new Card(CardNumber.EIGHT, CardSymbol.CLOVER);

        ParticipantCardDeck participantCardDeck = ParticipantCardDeck.generateEmptySet();
        participantCardDeck.addCard(aceCard);
        participantCardDeck.addCard(card);

        //when & then
        Assertions.assertThat(participantCardDeck.calculateScore()).isEqualTo(19);
    }

    @Test
    void 에이스가_두개_이상_점수_계산_확인() {
        //given
        Card aceCard1 = new Card(CardNumber.ACE, CardSymbol.CLOVER);
        Card aceCard2 = new Card(CardNumber.ACE, CardSymbol.HEART);
        Card card = new Card(CardNumber.TWO, CardSymbol.HEART);

        ParticipantCardDeck participantCardDeck = ParticipantCardDeck.generateEmptySet();
        participantCardDeck.addCard(aceCard1);
        participantCardDeck.addCard(aceCard2);
        participantCardDeck.addCard(card);

        //when & then
        Assertions.assertThat(participantCardDeck.calculateScore()).isEqualTo(14);
    }
}
