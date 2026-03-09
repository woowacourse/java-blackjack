package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void 카드_생성_테스트() {
        // given
        CardValue cardValue = CardValue.ACE;
        CardShape cardShape = CardShape.DIAMOND;

        // when
        Card card = new Card(cardValue, cardShape);

        // then
        assertThat(card.cardValue()).isEqualTo(cardValue);
        assertThat(card.cardShape()).isEqualTo(cardShape);
    }

    @Test
    void 카드가_에이스인지_판단하는_기능_테스트() {
        // given
        Card card = new Card(CardValue.ACE, CardShape.DIAMOND);

        // when
        boolean isAce = card.isAce();

        // then
        assertThat(isAce).isTrue();
    }

}
