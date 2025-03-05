package blackjack.model;

import static blackjack.model.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RuleTest {

    private final Rule rule = new Rule();

    private static Stream<Arguments> 딜러가_카드를_뽑아야_하는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SIX)
                                )
                        ),
                        true
                )
        );
    }

    private static Stream<Arguments> 플레이어가_카드를_뽑아야_하는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SEVEN),
                                        createCard(CardNumber.THREE)
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SEVEN),
                                        createCard(CardNumber.FOUR)
                                )
                        ),
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("딜러가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 딜러가_카드를_뽑아야_하는지_반환한다(final Cards cards, final boolean expected) {
        Dealer dealer = new Dealer();
        dealer.receiveCards(cards);

        assertThat(rule.canPlayerDrawMoreCard(dealer)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("플레이어가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 플레이어가_카드를_뽑아야_하는지_반환한다(final Cards cards, final boolean expected) {
        User user = new User();
        user.receiveCards(cards);

        assertThat(rule.canPlayerDrawMoreCard(user)).isEqualTo(expected);
    }

}
