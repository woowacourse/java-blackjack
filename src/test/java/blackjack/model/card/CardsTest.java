package blackjack.model.card;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

    @Test
    void 카드_숫자의_합을_반환한다() {
        Cards cards = new Cards(
                List.of(
                        new Card(CardType.CLOVER, CardNumber.TWO),
                        new Card(CardType.CLOVER, CardNumber.TEN),
                        new Card(CardType.CLOVER, CardNumber.ACE)
                )
        );
        List<Integer> expected = List.of(13, 23);

        assertThat(cards.calculatePossiblePoints()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @CsvSource(value = {"2,true", "1,false"})
    @ParameterizedTest
    void 숫자를_알려주면_카드_개수와_동일한지_알려준다(int size, boolean expected) {
        Cards cards = new Cards(
                List.of(
                        new Card(CardType.CLOVER, CardNumber.TWO),
                        new Card(CardType.CLOVER, CardNumber.TEN)
                )
        );

        assertThat(cards.hasSize(size)).isEqualTo(expected);
    }

    @Test
    void 두_개의_카트들을_합친다() {
        Cards cards = new Cards(List.of(new Card(CardType.CLOVER, CardNumber.TEN)));
        Cards otherCards = new Cards(List.of(new Card(CardType.CLOVER, CardNumber.TEN)));

        cards.merge(otherCards);

        assertThat(cards.hasSize(2)).isTrue();
    }

    @Test
    void 카드를_N_개_뽑는다() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));

        assertThat(cards.pick(2)).hasSize(2);
        assertThat(cards.getValues()).isEmpty();
    }

    @Test
    void 존재하는_카드_개수보다_더_많은_카드를_뽑을_수_없다() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> cards.pick(3));
    }

    @Test
    void 첫_번째_카드를_반환한다() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));

        assertThat(cards.getFirst()).isEqualTo(createCard(CardNumber.TEN));
    }

}
