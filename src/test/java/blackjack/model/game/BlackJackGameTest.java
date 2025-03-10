package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

}
