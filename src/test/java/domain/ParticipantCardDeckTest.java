package domain;

import domain.card.Card;
import domain.card.ParticipantCardDeck;
import domain.card.CardNumber;
import domain.card.CardSymbol;
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
}
