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

class DealerTest {

    private Dealer dealer;

    private static Stream<Arguments> 최초로_받은_카드를_오픈한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE))),
                        new Cards(List.of(createCard(CardNumber.TEN)))
                ),
                Arguments.of(
                        new Cards(List.of(createCard(CardNumber.NINE), createCard(CardNumber.FIVE),
                                createCard(CardNumber.TWO))),
                        new Cards(List.of(createCard(CardNumber.NINE)))
                )
        );
    }

    private static Stream<Arguments> 자신이_카드를_더_뽑을_수_있는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SIX)
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        false
                )
        );
    }

    private static Stream<Arguments> 비겼는지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        makeUser(
                                "user",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        false
                )
        );
    }

    private static Stream<Arguments> 이겼는지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        makeUser(
                                "user",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        makeUser(
                                "user",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new Cards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        true
                )
        );
    }

    private static Player makeUser(final String name, final Cards cards) {
        Player player = new Player(name);
        player.receiveCards(cards);
        return player;
    }

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
    void 자신의_최저_포인트를_계산한다() {
        dealer.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.ACE))
        ));

        assertThat(dealer.getMinimumPoint()).isEqualTo(16);
    }

    @ParameterizedTest
    @MethodSource("최초로_받은_카드를_오픈한다_테스트_케이스")
    void 최초로_받은_카드를_오픈한다(final Cards cards, final Cards expected) {

        dealer.receiveCards(cards);

        assertThat(dealer.openInitialCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("자신이_카드를_더_뽑을_수_있는지_반환한다_테스트_케이스")
    void 자신이_카드를_더_뽑을_수_있는지_반환한다(final Cards cards, final boolean expected) {

        dealer.receiveCards(cards);

        assertThat(dealer.canDrawMoreCard()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("비겼는지_확인한다_테스트_케이스")
    void 비겼는지_확인한다(final Cards cards, final Player player, final boolean expected) {
        dealer.receiveCards(cards);

        assertThat(dealer.isDraw(player)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("이겼는지_확인한다_테스트_케이스")
    void 이겼는지_확인한다(final Cards cards, final Player player, final boolean expected) {
        dealer.receiveCards(cards);

        assertThat(dealer.isWin(player)).isEqualTo(expected);
    }
}
