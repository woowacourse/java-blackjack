package blackjack.model.player;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        dealer.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(dealer.getCards().getValues()).hasSize(3);
    }

    @Test
    void 딜러인지_확인해준다() {
        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    void 자신이_가진_카드의_최적합을_반환한다() {
        dealer.receiveCards(new Cards(
                List.of(createCard(CardNumber.JACK), createCard(CardNumber.QUEEN), createCard(CardNumber.ACE))
        ));

        assertThat(dealer.calculatePoint()).isEqualTo(21);
    }

    @Test
    void 딜러의_카드를_오픈한다() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));
        dealer.receiveCards(cards);

        assertThat(dealer.openCards()).isEqualTo(new Cards(createCard(CardNumber.TEN)));
    }

    @MethodSource("딜러가_카드를_뽑을_수_있는지_알려준다_테스트_케이스")
    @ParameterizedTest
    void 딜러가_카드를_뽑을_수_있는지_알려준다(Cards cards, boolean expected) {
        dealer.receiveCards(cards);

        assertThat(dealer.canDrawMoreCard()).isEqualTo(expected);
    }

    private static Stream<Arguments> 딜러가_카드를_뽑을_수_있는지_알려준다_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(createCard(CardNumber.TEN), createCard(CardNumber.SIX)), true),
                Arguments.of(new Cards(createCard(CardNumber.TEN), createCard(CardNumber.SEVEN)), false)
        );
    }

    @MethodSource("딜러가_블랙잭인지_확인한다_테스트_케이스")
    @ParameterizedTest
    void 딜러가_블랙잭인지_확인한다(Cards cards, boolean expected) {
        dealer.receiveCards(cards);

        assertThat(dealer.isBlackjack()).isEqualTo(expected);
    }

    private static Stream<Arguments> 딜러가_블랙잭인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.ACE))), true),
                Arguments.of(new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.JACK))), false),
                Arguments.of(new Cards(List.of(
                        createCard(CardNumber.TEN), createCard(CardNumber.JACK), createCard(CardNumber.ACE))), false
                )
        );
    }

    @MethodSource("딜러가_버스트인지_확인한다_테스트_케이스")
    @ParameterizedTest
    void 딜러가_버스트인지_확인한다(Cards cards, boolean expected) {
        dealer.receiveCards(cards);

        assertThat(dealer.isBust()).isEqualTo(expected);
    }

    private static Stream<Arguments> 딜러가_버스트인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.TEN), createCard(CardNumber.TWO)),
                        true),
                Arguments.of(
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.TEN), createCard(CardNumber.ACE)),
                        false)
        );
    }

}
