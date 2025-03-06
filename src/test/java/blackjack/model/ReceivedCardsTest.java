package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ReceivedCardsTest {

    @Test
    void 카드를_받는다() {
        // given
        ReceivedCards receivecdCards = new ReceivedCards();

        // when
        receivecdCards.receive(new Card(CardShape.CLOVER, CardType.NORMAL_2));
        receivecdCards.receive(new Card(CardShape.CLOVER, CardType.NORMAL_2));

        // then
        assertThat(receivecdCards.size()).isEqualTo(2);
    }

    @Test
    void 보유한_카드의_점수를_계산한다() {
        // given
        ReceivedCards receivecdCards = new ReceivedCards();

        // when
        receivecdCards.receive(new Card(CardShape.HEART, CardType.ACE));
        receivecdCards.receive(new Card(CardShape.HEART, CardType.ACE));
        receivecdCards.receive(new Card(CardShape.HEART, CardType.NORMAL_9));

        // then
        assertThat(receivecdCards.calculateTotalPoint()).isEqualTo(21);
    }

    @Test
    void 보유한_카드_점수의_합이_21_초과면_버스트다() {
        // given
        ReceivedCards receivecdCards = new ReceivedCards();
        // when
        receivecdCards.receive(new Card(CardShape.HEART, CardType.NORMAL_2));
        receivecdCards.receive(new Card(CardShape.HEART, CardType.JACK));
        receivecdCards.receive(new Card(CardShape.HEART, CardType.JACK));
        // then
        assertThat(receivecdCards.isBust()).isTrue();
    }
}
