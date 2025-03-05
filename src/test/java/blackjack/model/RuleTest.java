package blackjack.model;

import static blackjack.model.CardCreator.createCard;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RuleTest {

    private final Rule rule = new Rule();

    private static Stream<Arguments> 딜러가_카드를_뽑아야_하는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Dealer(
                                new CardDeck(
                                        new Cards(
                                                List.of(
                                                        createCard(CardNumber.TEN),
                                                        createCard(CardNumber.SEVEN)
                                                )
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new Dealer(
                                new CardDeck(
                                        new Cards(
                                                List.of(
                                                        createCard(CardNumber.TEN),
                                                        createCard(CardNumber.SIX)
                                                )
                                        )
                                )
                        ),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("딜러가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 딜러가_카드를_뽑아야_하는지_반환한다(final Dealer dealer, final boolean expected) {

        dealer.drawSelf(2);
        assertThat(rule.shouldDealerDraw(dealer)).isEqualTo(expected);
    }

    @Test
    void 딜러의_카드를_한장_뽑는다() {

        Dealer dealer = new Dealer(
                new CardDeck(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SEVEN)
                                )
                        )
                )
        );

        int expected = dealer.getCards().getValues().size() + 1;

        rule.drawDealerCards(dealer);

        int actual = dealer.getCards().getValues().size();

        assertThat(actual).isEqualTo(expected);
    }
}
