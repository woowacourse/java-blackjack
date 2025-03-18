package blackjack.model.card;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @Test
    void 카드_숫자의_최적합을_계산한다() {
        Cards cards = new Cards(
                List.of(
                        new Card(CardType.CLOVER, CardNumber.TWO),
                        new Card(CardType.CLOVER, CardNumber.TEN),
                        new Card(CardType.CLOVER, CardNumber.ACE)
                )
        );

        assertThat(cards.calculateOptimalPoint()).isEqualTo(13);
    }

    @Test
    void 두_개의_카트들을_합친다() {
        Cards cards = new Cards(List.of(new Card(CardType.CLOVER, CardNumber.TEN)));
        Cards otherCards = new Cards(List.of(new Card(CardType.CLOVER, CardNumber.TEN)));

        cards.merge(otherCards);

        assertThat(cards.getValues()).hasSize(2);
    }

    @Test
    void 카드를_N_개_뽑는다() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));

        assertThat(cards.pick(2)).hasSize(2);
    }

    @Test
    void 존재하는_카드_개수보다_더_많은_카드를_뽑을_수_없다() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));

        assertThatThrownBy(() -> cards.pick(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("남은 카드가 부족합니다.");
    }

    @Test
    void 첫_번째_카드를_반환한다() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));

        assertThat(cards.getFirst()).isEqualTo(createCard(CardNumber.TEN));
    }

    @MethodSource("카드가_블랙잭인지_확인한다_테스트_케이스")
    @ParameterizedTest
    void 카드가_블랙잭인지_확인한다(List<Card> cardList, boolean expected) {
        Cards cards = new Cards(cardList);

        assertThat(cards.isBlackjack()).isEqualTo(expected);
    }

    private static Stream<Arguments> 카드가_블랙잭인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(List.of(createCard(CardNumber.TEN), createCard(CardNumber.ACE)), true),
                Arguments.of(List.of(createCard(CardNumber.JACK), createCard(CardNumber.ACE)), true),
                Arguments.of(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.JACK), createCard(CardNumber.ACE)),
                        false
                ), Arguments.of(List.of(createCard(CardNumber.JACK), createCard(CardNumber.TEN)), false)
        );
    }

    @MethodSource("카드가_버스트인지_확인한다_테스트_케이스")
    @ParameterizedTest
    void 카드가_버스트인지_확인한다(Card card, boolean expected) {
        Cards cards = new Cards(createCard(CardNumber.TEN), createCard(CardNumber.JACK), card);

        assertThat(cards.isBust()).isEqualTo(expected);
    }

    private static Stream<Arguments> 카드가_버스트인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(createCard(CardNumber.TWO), true),
                Arguments.of(createCard(CardNumber.ACE), false)
        );
    }

}
