package blackjack.domain.cardpack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardPackTest {

    @Nested
    class 카드팩은_생성_시 {

        @ParameterizedTest(name = "{0}부터 {1}까지는 {2}다.")
        @CsvSource(value = {"0:12:SPADE", "13:25:DIAMOND", "26:38:HEART", "39:52:CLOVER"}, delimiter = ':')
        void _순서대로_생성된다(final int start, final int end, final CardShape shape) {
            CardPack cardPack = new CardPack();

            for (int i = start; i < end; i++) {
                Card card = cardPack.get(i);
                Assertions.assertThat(card.getShape()).isEqualTo(shape);
            }
        }

        @Test
        void _52장까지만_생성된다() {
            CardPack cardPack = new CardPack();

            Assertions.assertThatThrownBy(() -> cardPack.get(53))
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
        Card card = cardPack.get(cardPack.size() - 1);
        Assertions.assertThat(card.getShape()).isEqualTo(CardShape.SPADE);
        Assertions.assertThat(card.getNumber()).isEqualTo(CardNumber.ACE);
    }

    private static class NoviceShuffleStrategy implements ShuffleStrategy {
        @Override
        public void shuffle(final List<Card> cards) {
            cards.add(cards.remove(0));
        }
    }
}
