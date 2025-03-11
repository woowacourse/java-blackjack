package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;

class BlackJackGameTest {

    @MethodSource("딜러의_카드를_뽑아야_한다면_카드를_뽑는다_테스트_케이스")
    @ParameterizedTest
    void 딜러의_카드를_뽑아야_한다면_카드를_뽑는다(Cards cards, Cards receivedCards, boolean expected, int expectedSize) {
        BlackJackGame blackJackGame = new BlackJackGame(() -> cards);

        Dealer dealer = new Dealer();
        dealer.receiveCards(receivedCards);

        assertThat(blackJackGame.canDrawMoreCard(dealer)).isEqualTo(expected);
        assertThat(dealer.getCards().getValues()).hasSize(expectedSize);
    }

    private static Stream<Arguments> 딜러의_카드를_뽑아야_한다면_카드를_뽑는다_테스트_케이스() {
        Cards cards = new Cards(
                List.of(createCard(CardNumber.TEN), createCard(CardNumber.SIX)));
        return Stream.of(
                Arguments.of(cards, cards, true, 3),
                Arguments.of(cards, new Cards(
                        List.of(createCard(CardNumber.TEN), createCard(CardNumber.SEVEN))
                ), false, 2)
        );
    }

    @Test
    void 게임_시작시_플레이어들에게_카드_두장을_나눠준다() {
        BlackJackGame blackJackGame = new BlackJackGame(() -> new Cards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR),
                        createCard(CardNumber.FIVE))
        ));
        User user = new User("pobi");
        Dealer dealer = new Dealer();
        List<Player> players = List.of(dealer, user);

        blackJackGame.dealInitialCards(players);

        assertThat(dealer.getCards().getValues()).hasSize(2);
        assertThat(user.getCards().getValues()).hasSize(2);
    }

    @MethodSource("플레이어가_시작_카드를_오픈한다_테스트_케이스")
    @ParameterizedTest
    void 플레이어가_시작_카드를_오픈한다(Player player, Cards expectedCards) {
        BlackJackGame blackJackGame = new BlackJackGame(Cards::empty);
        player.receiveCards(new Cards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE)))
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
    void 플레이어들의_승패_결과를_계산한다(Cards dealerCards, Cards userCards, Map<Player, Map<Result, Integer>> expected) {
        Player dealer = new Dealer();
        Player user = new User("pobi");
        dealer.receiveCards(dealerCards);
        user.receiveCards(userCards);
        BlackJackGame blackJackGame = new BlackJackGame(Cards::empty);

        assertThat(blackJackGame.calculateResult(List.of(dealer, user))).containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static Stream<Arguments> 플레이어들의_승패_결과를_계산한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(new Cards(createCard(CardNumber.THREE)), new Cards(createCard(CardNumber.TWO)),
                        Map.of(new Dealer(), Map.of(Result.WIN, 1, Result.DRAW, 0, Result.LOSE, 0),
                                new User("pobi"), Map.of(Result.WIN, 0, Result.DRAW, 0, Result.LOSE, 1)
                        )),
                Arguments.of(new Cards(createCard(CardNumber.THREE)), new Cards(createCard(CardNumber.THREE)),
                        Map.of(new Dealer(), Map.of(Result.WIN, 0, Result.DRAW, 1, Result.LOSE, 0),
                                new User("pobi"), Map.of(Result.WIN, 0, Result.DRAW, 1, Result.LOSE, 0)
                        ))
        );
    }

}
