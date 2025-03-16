package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;

class BlackJackRuleTest {

    private final BlackJackRule blackJackRule = new BlackJackRule();

    @ParameterizedTest
    @MethodSource("딜러가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 딜러가_카드를_뽑아야_하는지_반환한다(final Cards cards, final boolean expected) {
        Dealer dealer = new Dealer();
        dealer.receiveCards(cards);

        assertThat(blackJackRule.canDrawMoreCard(dealer)).isEqualTo(expected);
    }

    private static Stream<Arguments> 딜러가_카드를_뽑아야_하는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.SIX))
                        ), true
                ),
                Arguments.of(new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.SEVEN))
                        ), false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("플레이어가_카드를_뽑아야_하는지_반환한다_테스트_케이스")
    void 플레이어가_카드를_뽑아야_하는지_반환한다(final Cards cards, final boolean expected) {
        User user = new User("pobi");
        user.receiveCards(cards);

        assertThat(blackJackRule.canDrawMoreCard(user)).isEqualTo(expected);
    }

    private static Stream<Arguments> 플레이어가_카드를_뽑아야_하는지_반환한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.SEVEN), createCard(CardNumber.THREE))
                        ), true
                ),
                Arguments.of(new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.SEVEN), createCard(CardNumber.FOUR))
                        ), false
                )
        );
    }

    @MethodSource("플레이어의_시작_카드들을_오픈하는_테스트_케이스")
    @ParameterizedTest
    void 플레이어의_시작_카드들을_오픈한다(final Player player, final Cards expected) {
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));
        player.receiveCards(cards);

        assertThat(blackJackRule.openInitialCards(player))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> 플레이어의_시작_카드들을_오픈하는_테스트_케이스() {
        return Stream.of(
                Arguments.of(makeDealer(), new Cards(createCard(CardNumber.TEN))),
                Arguments.of(makeUserWithName("pobi"),
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.FIVE))
                )
        );
    }

    @MethodSource("플레이어의_점수를_계산하는_테스트_케이스")
    @ParameterizedTest
    void 플레이어의_점수를_계산한다(final Cards cards, final int expected) {
        Player player = new User("pobi");
        player.receiveCards(cards);

        assertThat(blackJackRule.calculateOptimalPoint(player)).isEqualTo(expected);
    }

    private static Stream<Arguments> 플레이어의_점수를_계산하는_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(List.of(
                                createCard(CardNumber.ACE), createCard(CardNumber.ACE), createCard(CardNumber.TEN))
                        ), 12
                ),
                Arguments.of(new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.ACE), createCard(CardNumber.JACK))
                        ), 21
                ),
                Arguments.of(new Cards(List.of(createCard(CardNumber.TEN),
                                createCard(CardNumber.SEVEN), createCard(CardNumber.FOUR), createCard(CardNumber.ACE))
                        ), 22
                )
        );
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

    private static Stream<Arguments> 무승부_상황인지_확인하는_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.ACE)
                        )), new Cards(List.of(
                                createCard(CardNumber.JACK), createCard(CardNumber.ACE)
                        )), true
                ),
                Arguments.of(new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.SEVEN)
                        )), new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.SEVEN)
                        )), true
                ),
                Arguments.of(
                        new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.JACK), createCard(CardNumber.TWO)
                        )),
                        new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.JACK), createCard(CardNumber.TWO)
                        )), false
                ),
                Arguments.of(
                        new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.ACE)
                        )),
                        new Cards(List.of(
                                createCard(CardNumber.TEN), createCard(CardNumber.JACK)
                        )), false
                )
        );
    }

    @Test
    void 플레이어의_모든_카드들을_오픈한다() {
        Player player = new User("pobi");
        Cards cards = new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE)));
        player.receiveCards(cards);

        assertThat(blackJackRule.openAllCards(player)).isEqualTo(
                new Cards(List.of(createCard(CardNumber.TEN), createCard(CardNumber.FIVE))));
    }

    @MethodSource("플레이어가_블래잭인지_확인한다_테스트_케이스")
    @ParameterizedTest
    void 플레이어가_블래잭인지_확인한다(Cards cards, boolean expected) {
        Player player = new User("pobi");
        player.receiveCards(cards);

        assertThat(blackJackRule.isBlackjack(player)).isEqualTo(expected);
    }

    private static Stream<Arguments> 플레이어가_블래잭인지_확인한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TEN)), true),
                Arguments.of(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.JACK)), true),
                Arguments.of(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.KING)), true),
                Arguments.of(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.QUEEN)), true),
                Arguments.of(
                        new Cards(createCard(CardNumber.JACK), createCard(CardNumber.TEN), createCard(CardNumber.ACE)),
                        false)
        );
    }

    @CsvSource(value = {"22,true", "21,false"})
    @ParameterizedTest
    void 버스트_된_점수인지_확인한다(int point, boolean expected) {
        assertThat(blackJackRule.isBust(point)).isEqualTo(expected);
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

}
