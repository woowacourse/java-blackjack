package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Role;
import blackjack.model.player.User;

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
                        Map.of(makePlayerWithName(Role.DEALER, "딜러"), makeResultMap(2, 0, 0),
                                makePlayerWithName(Role.USER, "pobi"), makeResultMap(0, 0, 1),
                                makePlayerWithName(Role.USER, "json"), makeResultMap(0, 0, 1)
                        )),
                Arguments.of(
                        new DealerLoseRule(), makePlayerWithName(Role.DEALER, "딜러"),
                        List.of(makePlayerWithName(Role.USER, "pobi"),
                                makePlayerWithName(Role.USER, "json")),
                        Map.of(makePlayerWithName(Role.DEALER, "딜러"), makeResultMap(0, 0, 2),
                                makePlayerWithName(Role.USER, "pobi"), makeResultMap(1, 0, 0),
                                makePlayerWithName(Role.USER, "json"), makeResultMap(1, 0, 0)
                        )),
                Arguments.of(
                        new DrawRule(), makePlayerWithName(Role.DEALER, "딜러"),
                        List.of(makePlayerWithName(Role.USER, "pobi"),
                                makePlayerWithName(Role.USER, "json")),
                        Map.of(makePlayerWithName(Role.DEALER, "딜러"), makeResultMap(0, 2, 0),
                                makePlayerWithName(Role.USER, "pobi"), makeResultMap(0, 1, 0),
                                makePlayerWithName(Role.USER, "json"), makeResultMap(0, 1, 0)
                        ))
        );
    }

    private static Stream<Arguments> 플레이어의_시작_카드들을_오픈하는_테스트_케이스() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));
        return Stream.of(
                Arguments.of(
                        new Dealer("딜러"), cards, new Cards(createCard(CardNumber.TEN))
                ),
                Arguments.of(
                        new User("pobi"), cards, new Cards(createCard(CardNumber.TEN), createCard(CardNumber.FIVE))
                )
        );
    }

    private static Map<Result, Integer> makeResultMap(final int winCount, final int drawCount, final int loseCount) {
        Map<Result, Integer> result = new LinkedHashMap<>(Result.getResultBoard());
        result.put(Result.WIN, winCount);
        result.put(Result.DRAW, drawCount);
        result.put(Result.LOSE, loseCount);

        return result;
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

        assertThat(rule.canDrawMoreCard(dealer)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("플레이어가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 플레이어가_카드를_뽑아야_하는지_반환한다(final Cards cards, final boolean expected) {
        User user = new User("pobi");
        user.receiveCards(cards);

        assertThat(rule.canDrawMoreCard(user)).isEqualTo(expected);
    }

    @MethodSource("플레이어의_점수를_계산하는_테스트_케이스")
    @ParameterizedTest
    void 플레이어의_점수를_계산한다(final Cards cards, final int expected) {
        Player user = new User("pobi");
        user.receiveCards(cards);

        assertThat(rule.calculateOptimalPoint(user)).isEqualTo(expected);
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
    void 게임_결과를_반환한다(final Rule rule, final Dealer dealer, final List<User> users,
                     final Map<Player, Map<Result, Integer>> expected) {
        Map<Player, Map<Result, Integer>> results = rule.calculateResult(dealer, users);

        assertThat(results).containsAllEntriesOf(expected);
    }

    @MethodSource("플레이어의_시작_카드들을_오픈하는_테스트_케이스")
    @ParameterizedTest
    void 플레이어의_시작_카드들을_오픈한다(final Player player, final Cards cards, final Cards expected) {
        player.receiveCards(cards);

        assertThat(rule.openInitialCards(player).getValues())
                .containsAll(expected.getValues());
    }

    @Test
    void 플레이어의_모든_카드들을_오픈한다() {
        Player player = new User("pobi");
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));
        player.receiveCards(cards);

        assertThat(rule.openAllCards(player)).isEqualTo(
                new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE))));
    }

    private static class DealerWinRule extends Rule {

        @Override
        public boolean isDraw(final Player player, final Player challenger) {
            return false;
        }

        @Override
        public Player getWinner(final Player player, final Player challenger) {
            if (player instanceof Dealer) {
                return player;
            }
            if (challenger instanceof Dealer) {
                return challenger;
            }
            throw new IllegalArgumentException();
        }

    }

    private static class DealerLoseRule extends Rule {

        @Override
        public boolean isDraw(final Player player, final Player challenger) {
            return false;
        }

        @Override
        public Player getWinner(final Player player, final Player challenger) {
            if (player instanceof Dealer) {
                return challenger;
            }
            if (challenger instanceof Dealer) {
                return player;
            }
            throw new IllegalArgumentException();
        }

    }

    private static class DrawRule extends Rule {

        @Override
        public boolean isDraw(final Player player, final Player challenger) {
            return true;
        }

    }

}
