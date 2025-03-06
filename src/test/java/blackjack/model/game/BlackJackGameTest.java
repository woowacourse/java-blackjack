package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackJackGameTest {

    @CsvSource(value = {
            "true,true,1", "false,false,0"
    })
    @ParameterizedTest
    void 딜러의_카드를_뽑아야_한다면_카드를_뽑는다(boolean shouldDealerDraw, boolean expected, int expectedSize) {
        CardDeck cardDeck = new CardDeck(new Cards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR))
        ));
        FakeRule fakeRule = new FakeRule(shouldDealerDraw);
        BlackJackGame blackJackGame = new BlackJackGame(cardDeck, fakeRule);

        Dealer dealer = new Dealer("딜러");

        assertThat(blackJackGame.drawMoreCard(dealer)).isEqualTo(expected);
        assertThat(dealer.getCards().getValues()).hasSize(expectedSize);
    }

    @Test
    void 게임_시작시_플레이어들에게_카드_두장을_나눠준다() {
        CardDeck cardDeck = new CardDeck(new Cards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR),
                        createCard(CardNumber.FIVE))
        ));
        Rule rule = new Rule();
        BlackJackGame blackJackGame = new BlackJackGame(cardDeck, rule);
        User user = new User("pobi");
        Dealer dealer = new Dealer("딜러");
        List<Player> players = List.of(dealer, user);

        blackJackGame.dealInitialCards(players);

        assertThat(dealer.getCards().getValues()).hasSize(2);
        assertThat(user.getCards().getValues()).hasSize(2);
    }

    private static class FakeRule extends Rule {

        private final boolean shouldDealerDraw;

        public FakeRule(boolean shouldDealerDraw) {
            this.shouldDealerDraw = shouldDealerDraw;
        }

        @Override
        public boolean canDrawMoreCard(final Player player) {
            return shouldDealerDraw;
        }

    }

}
