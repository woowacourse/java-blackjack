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

class RunningTest {
    @Test
    @DisplayName("진행 중인(Hit)에서 시작 카드를 뽑는 경우 예외가 발생한다.")
    void hitDoNotReceiveInitCards() {
        HandCards cards = new HandCards();
        List<Card> initHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        Status hitStatus = new Hit(cards);
        Assertions.assertThrows(IllegalStateException.class, () -> hitStatus.drawInitialCards(initHands));
    }

    @Test
    @DisplayName("스테이를 하면, Stay상태가 반환된다.")
    void stayTest() {
        HandCards cards = new HandCards();
        Status hitStatus = new Hit(cards);
        Status stay = hitStatus.stay();
        assertThat(stay).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("히트를 한 후 버스트라면 Bust상태가 반환된다.")
    void bustTest() {
        HandCards cards = new HandCards();

        List<Card> initHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.TEN, CardShape.DIAMOND));
        Status startStatus = new Start(cards);

        Status initHandStatus = startStatus.drawInitialCards(initHands);
        Status bustStatus = initHandStatus.draw(new Card(CardNumber.EIGHT, CardShape.HEART));

        assertThat(bustStatus).isInstanceOf(Bust.class);
    }


    @Test
    @DisplayName("히트를 하고 버스트가 아니라면 Hit상태가 반환된다.")
    void hitTest() {
        HandCards cards = new HandCards();

        List<Card> initHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        Status startStatus = new Start(cards);

        Status initHandStatus = startStatus.drawInitialCards(initHands);
        Status hitStatus = initHandStatus.draw(new Card(CardNumber.EIGHT, CardShape.HEART));

        assertThat(hitStatus).isInstanceOf(Hit.class);
    }
}
