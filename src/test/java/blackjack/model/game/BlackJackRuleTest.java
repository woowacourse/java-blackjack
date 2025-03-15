package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
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

class BlackJackRuleTest {

    private final BlackJackRule blackJackRule = new BlackJackRule();

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
                        makeDealer(createCard(CardNumber.TEN), createCard(CardNumber.SEVEN)),
                        makeBustPlayer(Role.USER)),
                Arguments.of(
                        makeDealer(createCard(CardNumber.TEN), createCard(CardNumber.EIGHT)),
                        makeUserWithName("pobi", createCard(CardNumber.TEN), createCard(CardNumber.SEVEN)
                        ))
        );
    }

    private static Stream<Arguments> 게임_결과를_반환하는_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new DealerWinBlackJackRule(), makeDealer(),
                        List.of(makeUserWithName("pobi"),
                                makeUserWithName("json")),
                        Map.of(makeDealer(), Map.of(GameResult.WIN, 2),
                                makeUserWithName("pobi"), Map.of(GameResult.LOSE, 1),
                                makeUserWithName("json"), Map.of(GameResult.LOSE, 1)
                        )),
                Arguments.of(
                        new DealerLoseBlackJackRule(), makeDealer(),
                        List.of(makeUserWithName("pobi"),
                                makeUserWithName("json")),
                        Map.of(makeDealer(), Map.of(GameResult.LOSE, 2),
                                makeUserWithName("pobi"), Map.of(GameResult.WIN, 1),
                                makeUserWithName("json"), Map.of(GameResult.WIN, 1)
                        )),
                Arguments.of(
                        new DrawBlackJackRule(), makeDealer(),
                        List.of(makeUserWithName("pobi"),
                                makeUserWithName("json")),
                        Map.of(makeDealer(), Map.of(GameResult.DRAW, 2),
                                makeUserWithName("pobi"), Map.of(GameResult.DRAW, 1),
                                makeUserWithName("json"), Map.of(GameResult.DRAW, 1)
                        ))
        );
    }

    private static Stream<Arguments> 플레이어의_시작_카드들을_오픈하는_테스트_케이스() {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));
        return Stream.of(
                Arguments.of(makeDealer(), cards, new Cards(createCard(CardNumber.TEN))),
                Arguments.of(makeUserWithName("pobi"), cards,
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)))
        );
    }

    private static Player makeDealer(final Card... cards) {
        Player player = new Dealer();
        player.receiveCards(new Cards(Arrays.stream(cards).toList()));
        return player;
    }

    private static Player makeUserWithName(final String name, final Card... cards) {
        Player player = new User(name);
        player.receiveCards(new Cards(Arrays.stream(cards).toList()));
        return player;
    }

    private static Player makeBustPlayer(final Role role) {
        if (role == Role.DEALER) {
            return makeDealer(createCard(CardNumber.TEN), createCard(CardNumber.NINE), createCard(CardNumber.SEVEN));
        }
        return makeUserWithName("pobi",
                createCard(CardNumber.TEN), createCard(CardNumber.NINE), createCard(CardNumber.SEVEN)
        );
    }

    @ParameterizedTest
    @MethodSource("딜러가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 딜러가_카드를_뽑아야_하는지_반환한다(final Cards cards, final boolean expected) {
        Dealer dealer = new Dealer();
        dealer.receiveCards(cards);

        assertThat(blackJackRule.canDrawMoreCard(dealer)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("플레이어가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 플레이어가_카드를_뽑아야_하는지_반환한다(final Cards cards, final boolean expected) {
        User user = new User("pobi");
        user.receiveCards(cards);

        assertThat(blackJackRule.canDrawMoreCard(user)).isEqualTo(expected);
    }

    @MethodSource("플레이어의_점수를_계산하는_테스트_케이스")
    @ParameterizedTest
    void 플레이어의_점수를_계산한다(final Cards cards, final int expected) {
        Player user = new User("pobi");
        user.receiveCards(cards);

        assertThat(blackJackRule.calculateOptimalPoint(user)).isEqualTo(expected);
    }

    @MethodSource("무승부_상황인지_확인하는_테스트_케이스")
    @ParameterizedTest
    void 무승부_상황인지_확인한다(final Cards dealerCards, final Cards userCards, final boolean expected) {
        Player dealer = new Dealer();
        Player user = new User("pobi");

        dealer.receiveCards(dealerCards);
        user.receiveCards(userCards);

        assertThat(blackJackRule.isDraw(dealer, user)).isEqualTo(expected);
    }

    @MethodSource("승자를_반환하는_테스트_케이스")
    @ParameterizedTest
    void 승자를_반환한다(final Player winner, final Player loser) {
        assertThat(blackJackRule.getWinner(winner, loser)).isEqualTo(winner);
        assertThat(blackJackRule.getWinner(loser, winner)).isEqualTo(winner);
    }

    @MethodSource("게임_결과를_반환하는_테스트_케이스")
    @ParameterizedTest
    void 게임_결과를_반환한다(final BlackJackRule blackJackRule, final Player dealer, final List<Player> users,
                     final Map<Player, Map<GameResult, Integer>> expected) {
        Map<Player, Map<GameResult, Integer>> results = blackJackRule.calculateResult(dealer, users);

        assertThat(results).containsAllEntriesOf(expected);
    }

    @MethodSource("플레이어의_시작_카드들을_오픈하는_테스트_케이스")
    @ParameterizedTest
    void 플레이어의_시작_카드들을_오픈한다(final Player player, final Cards cards, final Cards expected) {
        player.receiveCards(cards);

        assertThat(blackJackRule.openInitialCards(player).getValues())
                .containsAll(expected.getValues());
    }

    @Test
    void 플레이어의_모든_카드들을_오픈한다() {
        Player player = new User("pobi");
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));
        player.receiveCards(cards);

        assertThat(blackJackRule.openAllCards(player)).isEqualTo(
                new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE))));
    }

    private static class DealerWinBlackJackRule extends BlackJackRule {

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

    private static class DealerLoseBlackJackRule extends BlackJackRule {

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

    private static class DrawBlackJackRule extends BlackJackRule {

        @Override
        public boolean isDraw(final Player player, final Player challenger) {
            return true;
        }

    }

}
