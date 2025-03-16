package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;

class BlackJackGameTest {

    @MethodSource("딜러의_카드를_뽑아야_하는지_알려준다_테스트_케이스")
    @ParameterizedTest
    void 딜러의_카드를_뽑아야_하는지_알려준다(Cards cards, Cards receivedCards, boolean expected) {
        BlackJackGame blackJackGame = new BlackJackGame(() -> cards);

        Dealer dealer = new Dealer();
        dealer.receiveCards(receivedCards);

        assertThat(blackJackGame.canDrawMoreCard(dealer)).isEqualTo(expected);
    }

    private static Stream<Arguments> 딜러의_카드를_뽑아야_하는지_알려준다_테스트_케이스() {
        Cards cards = new Cards(
                of(createCard(CardNumber.TEN), createCard(CardNumber.SIX)));
        return Stream.of(
                Arguments.of(cards, cards, true, 3),
                Arguments.of(cards,
                        new Cards(of(createCard(CardNumber.TEN), createCard(CardNumber.SEVEN))), false)
        );
    }

    @Test
    void 게임_시작시_플레이어들에게_카드_두장을_나눠준다() {
        BlackJackGame blackJackGame = new BlackJackGame(() -> new Cards(
                of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR),
                        createCard(CardNumber.FIVE))
        ));
        User user = new User("pobi");
        Dealer dealer = new Dealer();
        List<Player> players = of(dealer, user);

        blackJackGame.dealInitialCards(players);

        assertThat(dealer.getCards().getValues()).hasSize(2);
        assertThat(user.getCards().getValues()).hasSize(2);
    }

    @MethodSource("플레이어가_시작_카드를_오픈한다_테스트_케이스")
    @ParameterizedTest
    void 플레이어가_시작_카드를_오픈한다(Player player, Cards expectedCards) {
        BlackJackGame blackJackGame = new BlackJackGame(Cards::empty);
        player.receiveCards(new Cards(
                of(createCard(CardNumber.TWO), createCard(CardNumber.THREE)))
        );

        assertThat(blackJackGame.openInitialCards(player)).isEqualTo(expectedCards);
    }

    private static Stream<Arguments> 플레이어가_시작_카드를_오픈한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Dealer(), new Cards(createCard(CardNumber.TWO))),
                Arguments.of(new User("pobi"), new Cards(createCard(CardNumber.TWO), createCard(CardNumber.THREE)))
        );
    }

    @MethodSource("플레이어들의_승패_결과를_계산한다_테스트_케이스")
    @ParameterizedTest
    void 플레이어들의_승패_결과를_계산한다(Cards dealerCards, Cards userCards, Map<Player, Map<GameResult, Integer>> expected) {
        Player dealer = new Dealer();
        Player user = new User("pobi");
        dealer.receiveCards(dealerCards);
        user.receiveCards(userCards);
        BlackJackGame blackJackGame = new BlackJackGame(Cards::empty);

        assertThat(blackJackGame.calculateResult(of(dealer, user))).containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static Stream<Arguments> 플레이어들의_승패_결과를_계산한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(createCard(CardNumber.THREE)), new Cards(createCard(CardNumber.TWO)),
                        Map.of(new Dealer(), Map.of(GameResult.WIN, 1),
                                new User("pobi"), Map.of(GameResult.LOSE, 1)
                        )),
                Arguments.of(new Cards(createCard(CardNumber.THREE)), new Cards(createCard(CardNumber.THREE)),
                        Map.of(new Dealer(), Map.of(GameResult.DRAW, 1),
                                new User("pobi"), Map.of(GameResult.DRAW, 1)
                        ))
        );
    }

    @Test
    void 플레이어들의_최적_포인트를_계산한다() {
        Player pobi = new User("pobi");
        pobi.receiveCards(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TEN)));
        Player json = new User("json");
        json.receiveCards(new Cards(createCard(CardNumber.ACE), createCard(CardNumber.ACE),
                createCard(CardNumber.TEN), createCard(CardNumber.TEN)));
        BlackJackGame blackJackGame = new BlackJackGame(Cards::empty);

        assertThat(blackJackGame.calculateOptimalPoints(of(pobi, json)))
                .containsExactlyInAnyOrderEntriesOf(Map.of(pobi, 21, json, 22));
    }

    @MethodSource("플레이어의_수익을_계산한다_테스트_케이스")
    @ParameterizedTest
    void 플레이어의_수익을_계산한다(Cards dealerCards, Cards playerCards, Map<Player, BigDecimal> expected) {
        BlackJackGame blackJackGame = new BlackJackGame(new DefaultCardDeckInitializer());
        Player dealer = new Dealer();
        dealer.receiveCards(dealerCards);
        Player pobi = new User("pobi");
        blackJackGame.bet(pobi, 10_000);
        pobi.receiveCards(playerCards);

        assertThat(blackJackGame.calculatePlayerWinnings(blackJackGame.calculateResult(of(dealer, pobi))))
                .containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static Stream<Arguments> 플레이어의_수익을_계산한다_테스트_케이스() {
        Player pobi = new User("pobi");
        Player dealer = new Dealer();
        return Stream.of(
                Arguments.of(
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.JACK)),
                        new Cards(createCard(CardNumber.ACE), createCard(CardNumber.JACK)),
                        Map.ofEntries(Map.entry(pobi, BigDecimal.valueOf(15_000)),
                                Map.entry(dealer, BigDecimal.valueOf(-15_000))
                        )
                ), Arguments.of(
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.JACK)),
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.JACK), createCard(CardNumber.ACE)),
                        Map.ofEntries(Map.entry(pobi, BigDecimal.valueOf(10_000)),
                                Map.entry(dealer, BigDecimal.valueOf(-10_000))
                        )
                ), Arguments.of(
                        new Cards(createCard(CardNumber.ACE), createCard(CardNumber.JACK)),
                        new Cards(createCard(CardNumber.ACE), createCard(CardNumber.JACK)),
                        Map.ofEntries(Map.entry(pobi, BigDecimal.valueOf(0)), Map.entry(dealer, BigDecimal.valueOf(0)))
                ), Arguments.of(
                        new Cards(createCard(CardNumber.ACE), createCard(CardNumber.NINE)),
                        new Cards(createCard(CardNumber.ACE), createCard(CardNumber.NINE)),
                        Map.ofEntries(Map.entry(pobi, BigDecimal.valueOf(0)), Map.entry(dealer, BigDecimal.valueOf(0)))
                ), Arguments.of(
                        new Cards(createCard(CardNumber.ACE), createCard(CardNumber.JACK)),
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.JACK), createCard(CardNumber.TWO)),
                        Map.ofEntries(Map.entry(pobi, BigDecimal.valueOf(-10_000)),
                                Map.entry(dealer, BigDecimal.valueOf(10_000))
                        )
                )
        );

    }

}
