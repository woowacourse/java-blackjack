package domain.status;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.participant.HandCards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class FinishedTest {
    @Test
    @DisplayName("이미 종료된 상태(Stay)에서 시작 카드를 뽑는 경우 예외가 발생한다.")
    void finishedDoNotReceiveInitCards() {
        HandCards cards = new HandCards();
        List<Card> initHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        Status stayStatus = new Stay(cards);
        Assertions.assertThrows(IllegalStateException.class, () -> stayStatus.drawInitialCards(initHands));
    }

    @Test
    @DisplayName("이미 종료된 상태(Stay)에서 카드를 뽑으려 하면 예외가 발생한다.")
    void finishedDoNotDraw() {
        HandCards cards = new HandCards();
        Status stayStatus = new Stay(cards);
        Card newCard = new Card(CardNumber.TWO, CardShape.SPADE);

        Assertions.assertThrows(IllegalStateException.class, () -> stayStatus.draw(newCard));
    }

    @Test
    @DisplayName("이미 종료된 상태(Stay)에서 또 스테이를 하면 예외가 발생한다.")
    void finishedDoNotStay() {
        HandCards cards = new HandCards();
        Status stayStatus = new Stay(cards);
        Assertions.assertThrows(IllegalStateException.class, stayStatus::stay);
    }

}
