package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ReceivedCardsTest {

    @Test
    void 카드를_받는다() {
        // given
        ReceivedCards receivecdCards = new ReceivedCards();

        // when
        receivecdCards.receive(new NormalCard(2, CardShape.CLOVER));
        receivecdCards.receive(new NormalCard(2, CardShape.HEART));

        // then
        assertThat(receivecdCards.size()).isEqualTo(2);
    }

    @Test
    void 보유한_카드의_점수를_계산한다() {
        // given
        ReceivedCards receivecdCards = new ReceivedCards();

        // when
        receivecdCards.receive(new AceCard(CardShape.HEART));
        receivecdCards.receive(new AceCard(CardShape.HEART));
        receivecdCards.receive(new NormalCard(9, CardShape.SPADE));

        // then
        assertThat(receivecdCards.calculateTotalPoint()).isEqualTo(21);
    }

    @Test
    void 보유한_카드_점수의_합이_21_초과면_버스트다() {
        // given
        ReceivedCards receivecdCards = new ReceivedCards();

        // then
        assertThat(receivecdCards.isBust(22)).isTrue();
    }

    @Test
    void ACE가_11점_일때_버스트면_ACE는_1점_이다() {
        // given
        ReceivedCards receivecdCards = new ReceivedCards();

        // when
        receivecdCards.receive(new AceCard(CardShape.CLOVER));
        receivecdCards.receive(new SpecialCard('K', CardShape.HEART));
        receivecdCards.receive(new SpecialCard('J', CardShape.HEART));

        // then
        assertThat(receivecdCards.get(0).getPoint()).isEqualTo(1);
    }
}
