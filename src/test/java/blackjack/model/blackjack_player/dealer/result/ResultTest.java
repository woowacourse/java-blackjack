package blackjack.model.blackjack_player.dealer.result;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.blackjack_player.dealer.Dealer;
import blackjack.model.blackjack_player.player.Player;
import blackjack.model.card.BlackJackCard;
import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardType;
import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {

    private static Stream<Arguments> 비겼는지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        true
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.ACE)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.JACK),
                                                new BlackJackCard(CardType.DIAMOND, CardNumber.ACE)
                                        )
                                )
                        ),
                        true
                )
        );
    }

    private static Stream<Arguments> 딜러가_이겼는지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT),
                                                createCard(CardNumber.SIX)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
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
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT),
                                        createCard(CardNumber.SEVEN)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
                                        List.of(
                                                createCard(CardNumber.TEN),
                                                createCard(CardNumber.EIGHT)
                                        )
                                )
                        ),
                        false
                ),
                Arguments.of(
                        new BlackJackCards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.EIGHT)
                                )
                        ),
                        makeUser(
                                "user",
                                new BlackJackCards(
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

    private static Dealer makeDealer(final BlackJackCards blackJackCards) {
        Dealer dealer = new Dealer(new DefaultCardDeckInitializer());
        dealer.getAllCards().addAll(blackJackCards);
        return dealer;
    }

    private static Player makeUser(final String name, final BlackJackCards blackJackCards) {
        Player player = new Player(name, 1);
        player.receiveCards(blackJackCards);
        return player;
    }

    @ParameterizedTest
    @MethodSource("비겼는지_확인한다_테스트_케이스")
    void 비겼는지_확인한다(final BlackJackCards blackJackCards, final Player player, final boolean expected) {
        Dealer dealer = makeDealer(blackJackCards);

        assertThat(Result.calculate(dealer, player) == Result.DRAW).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("딜러가_이겼는지_확인한다_테스트_케이스")
    void 딜러가_이겼는지_확인한다(final BlackJackCards blackJackCards, final Player player, final boolean expected) {
        Dealer dealer = makeDealer(blackJackCards);

        assertThat(Result.calculate(dealer, player) == Result.DEALER_WIN).isEqualTo(expected);
    }
}
