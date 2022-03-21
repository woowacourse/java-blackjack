package blackjack.domain.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.ParticipantCards;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {

    ParticipantCards blackjackCardsSet;
    ParticipantCards hittableCardsSet;
    ParticipantCards readyCardsSet;
    ParticipantCards emptyCardsSet;

    Card card = new Card(Suit.SPADE, Denomination.TWO);
    Card blackjackCard = new Card(Suit.SPADE, Denomination.TEN);

    @BeforeEach
    void setupCards() {
        blackjackCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.JACK)));

        hittableCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.TWO)));

        readyCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE)));

        emptyCardsSet = new ParticipantCards(List.of());
    }

    @Test
    @DisplayName("카드가 0장일 때 더 뽑을 시 ready를 반환한다.")
    void readyToReady() {
        Ready ready = new Ready(emptyCardsSet);

        assertThat(ready.draw(card)).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("카드가 한장일 때 카드를 한장 더 뽑을 시 Hit 반환한다.")
    void readyToHit() {
        Ready ready = new Ready(readyCardsSet);

        assertThat(ready.draw(card)).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드의 합이 블랙잭이면 Blackjack을 반환한다.")
    void readyToBlackjack() {
        Ready ready = new Ready(readyCardsSet);

        assertThat(ready.draw(blackjackCard)).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("Ready상태에서 stay를 호출할 시 오류 발생")
    void readyToStay() {
        Ready ready = new Ready(readyCardsSet);

        assertThatThrownBy(ready::stay)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 처음 카드는 두장 미만으로 받을 수 없습니다.");
    }

    @Test
    @DisplayName("참가자 카드를 반환해준다.")
    void getParticipantCards() {
        Ready ready = new Ready(readyCardsSet);

        assertThat(ready.getParticipantCards()).isSameAs(readyCardsSet);
    }

}
