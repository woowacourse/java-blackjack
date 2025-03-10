package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;

class BlackJackGameTest {

    @CsvSource(value = {
            "true,1", "false,0"
    })
    @ParameterizedTest
    void 딜러의_카드를_뽑아야_한다면_카드를_뽑는다(boolean expected, int expectedSize) {
        BlackJackGame blackJackGame = new BlackJackGame(() -> new Cards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR))
        ));

        Dealer dealer = new Dealer();

        assertThat(blackJackGame.canDrawMoreCard(dealer)).isEqualTo(expected);
        assertThat(dealer.getCards().getValues()).hasSize(expectedSize);
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
