package domain.status;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.participant.HandCards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StartTest {

    @Test
    @DisplayName("시작 카드를 뽑는 경우(블랙잭이 아닐 때) Hit를 반환한다.")
    void startNotBlackJackTest() {
        HandCards cards = new HandCards();
        List<Card> initHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        Status startStatus = new Start(cards);
        Status hit = startStatus.drawInitialCards(initHands);

        assertThat(hit).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("블랙잭을 뽑을 때 BlackJack를 반환한다.")
    void startBlackJackTest() {
        HandCards cards = new HandCards();
        List<Card> initHands = List.of(new Card(CardNumber.KING, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        Status startStatus = new Start(cards);
        Status blackJack = startStatus.drawInitialCards(initHands);

        assertThat(blackJack).isInstanceOf(Blackjack.class);
    }


    @Test
    @DisplayName("시작에서 카드를 뽑으려 하면 예외가 발생한다.")
    void finishedDoNotDraw() {
        HandCards cards = new HandCards();
        Status startStatus = new Start(cards);
        Card newCard = new Card(CardNumber.TWO, CardShape.SPADE);

        Assertions.assertThrows(IllegalStateException.class, () -> startStatus.draw(newCard));
    }

    @Test
    @DisplayName("시작에서 스테이를 하면 예외가 발생한다.")
    void finishedDoNotStay() {
        HandCards cards = new HandCards();
        Status startStatus = new Start(cards);
        Assertions.assertThrows(IllegalStateException.class, startStatus::stay);
    }

}
