package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {


    private static Stream<Arguments> 딜러의_승패들을_계산한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        makeDealer(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO))),
                        List.of(
                                makeUser("user1", new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO))),
                                makeUser("user2", new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO)))
                        ),
                        makeEvaluations(0, 2, 0)
                ),
                Arguments.of(
                        makeDealer(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO))),
                        List.of(
                                makeUser("user1", new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO),
                                        createCard(CardNumber.THREE))),
                                makeUser("user2", new Cards(createCard(CardNumber.ACE)))
                        ),
                        makeEvaluations(1, 0, 1)
                ),
                Arguments.of(
                        makeDealer(new Cards(createCard(CardNumber.FIVE), createCard(CardNumber.TWO))),
                        List.of(
                                makeUser("user1", new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO),
                                        createCard(CardNumber.THREE))),
                                makeUser("user2", new Cards(createCard(CardNumber.TEN), createCard(CardNumber.NINE),
                                        createCard(CardNumber.EIGHT)))
                        ),
                        makeEvaluations(1, 0, 1)
                )
        );
    }


    private static Dealer makeDealer(final Cards cards) {
        Dealer dealer = new Dealer();
        dealer.receiveCards(cards);
        return dealer;
    }

    private static User makeUser(final String name, final Cards cards) {
        User user = new User(name);
        user.receiveCards(cards);
        return user;
    }

    private static Map<Result, Integer> makeEvaluations(final int win, final int draw, final int lose) {
        return Map.of(
                Result.WIN, win,
                Result.DRAW, draw,
                Result.LOSE, lose
        );
    }

    private static Stream<Arguments> 유저의_승패를_계산한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        makeDealer(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO))),
                        makeUser("user", new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO))),
                        Result.DRAW
                ),
                Arguments.of(
                        makeDealer(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO))),
                        makeUser("user", new Cards(createCard(CardNumber.NINE), createCard(CardNumber.TEN),
                                createCard(CardNumber.THREE))),
                        Result.LOSE
                ),
                Arguments.of(
                        makeDealer(new Cards(createCard(CardNumber.FIVE), createCard(CardNumber.TWO))),
                        makeUser("user", new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TWO),
                                createCard(CardNumber.THREE))),
                        Result.WIN
                )
        );
    }

    @ParameterizedTest
    @MethodSource("딜러의_승패들을_계산한다_테스트_케이스")
    void 딜러의_승패들을_계산한다(final Dealer dealer, final List<User> users, final Map<Result, Integer> expected) {

        assertThat(Result.evaluateDealerResults(dealer, users)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("유저의_승패를_계산한다_테스트_케이스")
    void 유저의_승패를_계산한다(final Dealer dealer, final User user, final Result expected) {
        assertThat(Result.evaluateUserResult(dealer, user)).isEqualTo(expected);
    }
}
