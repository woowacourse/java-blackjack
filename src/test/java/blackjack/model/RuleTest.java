package blackjack.model;

import static blackjack.model.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    private static Stream<Arguments> 플레이어의_점수를_계산하는_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SEVEN),
                                        createCard(CardNumber.FOUR)
                                )
                        ),
                        21
                ),
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SEVEN),
                                        createCard(CardNumber.FOUR),
                                        createCard(CardNumber.ACE)
                                )
                        ),
                        22
                )
        );
    }

    private static Stream<Arguments> 무승부_상황인지_확인하는_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.NINE),
                                        createCard(CardNumber.FOUR)
                                )
                        ),
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.NINE),
                                        createCard(CardNumber.THREE)
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
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SEVEN)
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
                        new Cards(
                                List.of(
                                        createCard(CardNumber.TEN),
                                        createCard(CardNumber.SIX)
                                )
                        ),
                        false
                )
        );
    }

    private static Stream<Arguments> 승자를_반환하는_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        makePlayer(Role.DEALER, createCard(CardNumber.TEN), createCard(CardNumber.SEVEN)),
                        makeBustPlayer(Role.USER
                        )),
                Arguments.of(
                        makePlayer(Role.DEALER, createCard(CardNumber.TEN), createCard(CardNumber.EIGHT)),
                        makePlayer(Role.USER, createCard(CardNumber.TEN), createCard(CardNumber.SEVEN)
                        ))
        );
    }

    private static Stream<Arguments> 게임_결과를_반환하는_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new DealerWinRule(), makePlayerWithName(Role.DEALER, "딜러"),
                        List.of(makePlayerWithName(Role.USER, "pobi"),
                                makePlayerWithName(Role.USER, "json")),
                        Map.of(makePlayerWithName(Role.DEALER, "딜러"), List.of(Result.WIN, Result.WIN),
                                makePlayerWithName(Role.USER, "pobi"), List.of(Result.LOSE),
                                makePlayerWithName(Role.USER, "json"), List.of(Result.LOSE)
                        )),
                Arguments.of(
                        new DealerLoseRule(), makePlayerWithName(Role.DEALER, "딜러"),
                        List.of(makePlayerWithName(Role.USER, "pobi"),
                                makePlayerWithName(Role.USER, "json")),
                        Map.of(makePlayerWithName(Role.DEALER, "딜러"), List.of(Result.LOSE, Result.LOSE),
                                makePlayerWithName(Role.USER, "pobi"), List.of(Result.WIN),
                                makePlayerWithName(Role.USER, "json"), List.of(Result.WIN)
                        )),
                Arguments.of(
                        new DrawRule(), makePlayerWithName(Role.DEALER, "딜러"),
                        List.of(makePlayerWithName(Role.USER, "pobi"),
                                makePlayerWithName(Role.USER, "json")),
                        Map.of(makePlayerWithName(Role.DEALER, "딜러"), List.of(Result.DRAW, Result.DRAW),
                                makePlayerWithName(Role.USER, "pobi"), List.of(Result.DRAW),
                                makePlayerWithName(Role.USER, "json"), List.of(Result.DRAW)
                        ))
        );
    }

    private static Player makePlayer(final Role role, final Card... cards) {
        Player player = new Dealer("딜러");
        if (role == Role.USER) {
            player = new User("pobi");
        }
        player.receiveCards(new Cards(Arrays.stream(cards).toList()));
        return player;
    }

    private static Player makePlayerWithName(final Role role, final String name, final Card... cards) {
        Player player = new Dealer(name);
        if (role == Role.USER) {
            player = new User(name);
        }
        player.receiveCards(new Cards(Arrays.stream(cards).toList()));
        return player;
    }

    private static Player makeBustPlayer(final Role role) {
        return makePlayer(role,
                createCard(CardNumber.TEN), createCard(CardNumber.NINE), createCard(CardNumber.SEVEN)
        );
    }

    @ParameterizedTest
    @MethodSource("딜러가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 딜러가_카드를_뽑아야_하는지_반환한다(final Cards cards, final boolean expected) {
        Dealer dealer = new Dealer("딜러");
        dealer.receiveCards(cards);

        assertThat(rule.canPlayerDrawMoreCard(dealer)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("플레이어가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 플레이어가_카드를_뽑아야_하는지_반환한다(final Cards cards, final boolean expected) {
        User user = new User("pobi");
        user.receiveCards(cards);

        assertThat(rule.canPlayerDrawMoreCard(user)).isEqualTo(expected);
    }

    @MethodSource("플레이어의_점수를_계산하는_테스트_케이스")
    @ParameterizedTest
    void 플레이어의_점수를_계산한다(final Cards cards, final int expected) {
        Player user = new User("pobi");
        user.receiveCards(cards);

        assertThat(rule.calculatePoint(user)).isEqualTo(expected);
    }

    @MethodSource("무승부_상황인지_확인하는_테스트_케이스")
    @ParameterizedTest
    void 무승부_상황인지_확인한다(final Cards dealerCards, final Cards userCards, final boolean expected) {
        Player dealer = new Dealer("딜러");
        Player user = new User("pobi");

        dealer.receiveCards(dealerCards);
        user.receiveCards(userCards);

        assertThat(rule.isDraw(dealer, user)).isEqualTo(expected);
    }

    @MethodSource("승자를_반환하는_테스트_케이스")
    @ParameterizedTest
    void 승자를_반환한다(final Player winner, final Player loser) {
        assertThat(rule.getWinner(winner, loser)).isEqualTo(winner);
        assertThat(rule.getWinner(loser, winner)).isEqualTo(winner);
    }

    @MethodSource("게임_결과를_반환하는_테스트_케이스")
    @ParameterizedTest
    void 게임_결과를_반환한다(final Rule rule, final Player dealer, final List<Player> players,
                         final Map<Player, List<Result>> expected) {
        // 패패(승승), 승승(패패), 무무(무무)
        Map<Player, List<Result>> results = rule.calculateResult(dealer, players);

        assertThat(results).containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static class DealerWinRule extends Rule {

        @Override
        public boolean isDraw(final Player player, final Player otherPlayer) {
            return false;
        }

        @Override
        public Player getWinner(final Player player, final Player otherPlayer) {
            if (player instanceof Dealer) {
                return player;
            }
            if (otherPlayer instanceof Dealer) {
                return otherPlayer;
            }
            throw new IllegalArgumentException();
        }

    }

    private static class DealerLoseRule extends Rule {

        @Override
        public boolean isDraw(final Player player, final Player otherPlayer) {
            return false;
        }

        @Override
        public Player getWinner(final Player player, final Player otherPlayer) {
            if (player instanceof Dealer) {
                return otherPlayer;
            }
            if (otherPlayer instanceof Dealer) {
                return player;
            }
            throw new IllegalArgumentException();
        }

    }

    private static class DrawRule extends Rule {

        @Override
        public boolean isDraw(final Player player, final Player otherPlayer) {
            return true;
        }

    }

}
