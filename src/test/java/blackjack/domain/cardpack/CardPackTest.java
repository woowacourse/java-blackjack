package blackjack.domain.cardpack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        void _52장이_생성된다() {
            assertThat(cardPack.size()).isEqualTo(52);
        }

        @Test
        void _52장을_드로우_할_수_있다() {
            assertAll(
                    () -> {
                        assertDoesNotThrow(() -> {
                            for (int i = 0; i < 52; i++) {
                                cardPack.takeOne();
                            }
                        });
                    },
                    () -> {
                        assertThatThrownBy(() -> cardPack.takeOne())
                                .isInstanceOf(IllegalStateException.class);
                    }
            );
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
        Card card = cardPack.takeOne();
        assertThat(card.getShape()).isEqualTo(CardShape.SPADE);
        assertThat(card.getNumber()).isEqualTo(CardNumber.ACE);
    }

    @Test
    void 카드팩은_한장을_뽑을_수_있디() {
        //given
        CardPack cardPack = new CardPack();
        int originSize = cardPack.size();

        //when
        Card card = cardPack.takeOne();

        // then
        assertThat(cardPack.size()).isEqualTo(originSize - 1);
    }
}
