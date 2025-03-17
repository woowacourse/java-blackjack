package blackjack.model.blackjack_player.player;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import blackjack.model.blackjack_player.Hand;
import blackjack.model.blackjack_player.player.betting.Betting;
import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardNumber;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    private Player player;

    private static Stream<Arguments> 카드를_더_뽑을_수_있는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.FIVE)
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.ACE)
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.FIVE),
                                        createCard(CardNumber.SIX)
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
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

    private static Stream<Arguments> 카드를_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE))),
                        new BlackJackCards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)))
                ),
                Arguments.of(
                        new BlackJackCards(List.of(createCard(CardNumber.NINE), createCard(CardNumber.FIVE),
                                createCard(CardNumber.TWO))),
                        new BlackJackCards(List.of(createCard(CardNumber.NINE), createCard(CardNumber.FIVE),
                                createCard(CardNumber.TWO)))
                )
        );
    }

    private static Stream<Arguments> 수익을_계산할_수_있다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Betting(100, 20),
                        -80
                ),
                Arguments.of(
                        new Betting(200, 400),
                        200
                )
        );
    }

    @BeforeEach
    void setUp() {
        player = new Player("pobi", 1);
    }

    @ParameterizedTest
    @MethodSource("카드를_더_뽑을_수_있는지_반환한다_테스트_케이스")
    void 카드를_더_뽑을_수_있는지_반환한다(final BlackJackCards blackJackCards, final boolean expected) {
        Player player = new Player("pobi", 1);

        player.receiveCards(blackJackCards);

        assertThat(player.canReceiveMoreCard()).isEqualTo(expected);
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        player.receiveCards(new BlackJackCards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(player.getCards().getValues()).hasSize(3);
    }

    @Test
    void 카드를_더_받을_수_없는_경우_예외를_던진다() {
        Player player = new Player("pobi", 1000);
        player.receiveCards(new BlackJackCards(
                List.of(createCard(CardNumber.TEN), createCard(CardNumber.SEVEN), createCard(CardNumber.FOUR))
        ));

        assertThatIllegalStateException()
                .isThrownBy(() -> player.receiveCards(new BlackJackCards(List.of(createCard(CardNumber.ACE)))));
    }

    @Test
    void 자신의_최저_포인트를_계산한다() {
        player.receiveCards(new BlackJackCards(
                List.of(createCard(CardNumber.JACK), createCard(CardNumber.QUEEN), createCard(CardNumber.ACE))
        ));

        assertThat(player.getMinimumPoint()).isEqualTo(21);
    }

    @ParameterizedTest
    @MethodSource("카드를_반환한다_테스트_케이스")
    void 카드를_반환한다(final BlackJackCards blackJackCards, final BlackJackCards expected) {

        player.receiveCards(blackJackCards);

        assertThat(player.getCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("수익을_계산할_수_있다_테스트_케이스")
    void 수익을_계산할_수_있다(final Betting betting, final int expected) {

        Player player = new Player("pobi", betting, Hand.empty());

        assertThat(player.getProfit()).isEqualTo(expected);
    }
}
