package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardSetTest {

    @Test
    void 카드셋_정적_팩토리_메서드_확인() {
        //given
        CardSet cardSet = CardSet.generateFullSet();

        //when
        Assertions.assertThat(cardSet.getCards().size()).isEqualTo(52);
    }

    @Test
    void 카드셋_정적_팩토리_메서드_확인_2() {
        //given
        CardSet cardSet = CardSet.generateEmptySet();

        //when
        Assertions.assertThat(cardSet.getCards().size()).isEqualTo(0);
    }

    @Test
    void 카드_뽑기() {
        //given
        CardSet cardSet = CardSet.generateFullSet();

        //when
        Card card = cardSet.draw();

        //then
        Assertions.assertThat(cardSet.getCards().size()).isEqualTo(51);
        Assertions.assertThat(card).isInstanceOf(Card.class);
    }
}
