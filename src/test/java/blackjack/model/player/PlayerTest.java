package blackjack.model.player;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    private Player player;

    private static Stream<Arguments> 최초로_받은_카드를_오픈한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE))),
                        new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)))
                ),
                Arguments.of(
                        new Cards(List.of(createCard(CardNumber.NINE), createCard(CardNumber.FIVE),
                                createCard(CardNumber.TWO))),
                        new Cards(List.of(createCard(CardNumber.NINE), createCard(CardNumber.FIVE),
                                createCard(CardNumber.TWO)))
                )
        );
    }

    private static Stream<Arguments> 자신이_카드를_더_뽑을_수_있는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.FIVE)
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.FIVE),
                                        createCard(CardNumber.SIX)
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.FIVE),
                                        createCard(CardNumber.SIX),
                                        createCard(CardNumber.ACE)
                                )
                        ),
                        false
                )
        );
    }

    @BeforeEach
    void setUp() {
        player = new Player("pobi");
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        player.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(player.getCards().getValues()).hasSize(3);
    }

    @Test
    void 자신의_최저_포인트를_계산한다() {
        player.receiveCards(new Cards(
                List.of(createCard(CardNumber.JACK), createCard(CardNumber.QUEEN), createCard(CardNumber.ACE))
        ));

        assertThat(player.getMinimumPoint()).isEqualTo(21);
    }

    @ParameterizedTest
    @MethodSource("최초로_받은_카드를_오픈한다_테스트_케이스")
    void 최초로_받은_카드를_오픈한다(final Cards cards, final Cards expected) {

        player.receiveCards(cards);

        assertThat(player.openInitialCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("자신이_카드를_더_뽑을_수_있는지_반환한다_테스트_케이스")
    void 자신이_카드를_더_뽑을_수_있는지_반환한다(final Cards cards, final boolean expected) {

        player.receiveCards(cards);

        assertThat(player.canDrawMoreCard()).isEqualTo(expected);
    }
}
