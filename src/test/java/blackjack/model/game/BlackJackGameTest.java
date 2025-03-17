package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static java.util.List.of;
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
import blackjack.model.card.initializer.DefaultCardDeckInitializer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Players;
import blackjack.model.player.User;

class BlackJackGameTest {

    @Test
    void 게임_시작시_플레이어들에게_카드_두장을_나눠준다() {
        BlackJackGame blackJackGame = new BlackJackGame(() -> new Cards(
                of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR),
                        createCard(CardNumber.FIVE))
        ));
        User user = new User("pobi", 1_000);
        Dealer dealer = new Dealer();
        Players players = new Players(dealer, of(user));

        blackJackGame.drawInitialCards(players);

        assertThat(dealer.getCards().getValues()).hasSize(2);
        assertThat(user.getCards().getValues()).hasSize(2);
    }

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

    @MethodSource("유저의_게임_결과를_계산한다_테스트_케이스")
    @ParameterizedTest
    void 유저의_게임_결과를_계산한다(Cards userCards, Cards dealerCards, Map<User, GameResult> expected) {
        User user = new User("pobi", 1_000);
        Dealer dealer = new Dealer();
        user.receiveCards(userCards);
        dealer.receiveCards(dealerCards);
        Players players = new Players(dealer, List.of(user));
        BlackJackGame blackJackGame = new BlackJackGame(new DefaultCardDeckInitializer());

        assertThat(blackJackGame.calculateUserGameResults(players))
                .containsExactlyInAnyOrderEntriesOf(expected);
    }

    private static Stream<Arguments> 유저의_게임_결과를_계산한다_테스트_케이스() {
        User pobi = new User("pobi", 1_000);
        return Stream.of(
                Arguments.of(
                        new Cards(createCard(CardNumber.ACE), createCard(CardNumber.JACK)),
                        new Cards(createCard(CardNumber.TEN)),
                        Map.of(pobi, GameResult.BLACKJACK_WIN)
                ),
                Arguments.of(
                        new Cards(createCard(CardNumber.ACE), createCard(CardNumber.JACK)),
                        new Cards(createCard(CardNumber.ACE), createCard(CardNumber.TEN)),
                        Map.of(pobi, GameResult.DRAW)
                ),
                Arguments.of(
                        new Cards(createCard(CardNumber.JACK)),
                        new Cards(createCard(CardNumber.TEN)),
                        Map.of(pobi, GameResult.DRAW)
                ),
                Arguments.of(
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.JACK)),
                        new Cards(createCard(CardNumber.TEN)),
                        Map.of(pobi, GameResult.WIN)
                ),
                Arguments.of(
                        new Cards(createCard(CardNumber.TWO)),
                        new Cards(createCard(CardNumber.THREE)),
                        Map.of(pobi, GameResult.LOSE)
                ),
                Arguments.of(
                        new Cards(createCard(CardNumber.TEN), createCard(CardNumber.JACK), createCard(CardNumber.TWO)),
                        new Cards(createCard(CardNumber.THREE)),
                        Map.of(pobi, GameResult.LOSE)
                )
        );
    }

}
