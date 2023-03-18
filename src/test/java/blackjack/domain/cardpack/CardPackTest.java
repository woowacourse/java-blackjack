package blackjack.domain.cardpack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.util.NoviceShuffleStrategy;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardPackTest {

    @Nested
    class 카드팩은_생성_시 {
        private CardPack cardPack;

        @BeforeEach
        void setCardPack() {
            cardPack = new CardPack();
        }


        @Test
        void _순서대로_생성된다() {

            final List<CardShape> shapes = List.of(CardShape.CLOVER, CardShape.HEART, CardShape.DIAMOND,
                    CardShape.SPADE);
            for (final CardShape shape : shapes) {
                for (int i = 0; i < 13; i++) {
                    Card card = cardPack.pop();
                    assertThat(card.getShape()).isEqualTo(shape);
                }
            }
        }


        @Test
        void _52장까지만_생성된다() {
            assertThatThrownBy(() -> {
                for (int i = 0; i < 53; i++) {
                    cardPack.pop();
                }
            })
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    @Test
    void 카드팩은_shuffle_규칙에_의거하여_섞인다() {
        //given
        CardPack cardPack = new CardPack();
        ShuffleStrategy strategy = new NoviceShuffleStrategy();

        //when
        cardPack.shuffle(strategy);

        //then
        Card card = cardPack.pop();
        assertThat(card.getShape()).isEqualTo(CardShape.SPADE);
        assertThat(card.getNumber()).isEqualTo(CardNumber.ACE);
    }

    @Test
    void 카드팩은_한장을_뽑을_수_있디() {
        //given
        CardPack cardPack = new CardPack();

        // when
        Card card = cardPack.pop();

        // then
        assertThat(cardPack.pop()).isNotEqualTo(card);
    }

}
