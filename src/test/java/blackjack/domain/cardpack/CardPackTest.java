package blackjack.domain.cardpack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.assertj.core.api.Assertions;
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
        void _52이_생성된다() {
            Assertions.assertThatThrownBy(() -> {
                for (int i = 0; i < 53; i++) {
                    cardPack.takeOne();
                }
            }).isInstanceOf(IllegalStateException.class);
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
        Assertions.assertThat(card.getShape()).isEqualTo(CardShape.SPADE);
        Assertions.assertThat(card.getNumber()).isEqualTo(CardNumber.ACE);
    }

    @Test
    void 카드팩은_한장을_뽑을_수_있디() {
        //given
        CardPack cardPack = new CardPack();
        int originSize = cardPack.size();

        //when
        Card card = cardPack.takeOne();

        // then
        Assertions.assertThat(cardPack.size()).isEqualTo(originSize - 1);
    }
}
