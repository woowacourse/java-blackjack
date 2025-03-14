package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import blackjack.model.player.BlackJackPlayer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackJackGameTest {

    @CsvSource(value = {
            "true,true,1", "false,false,0"
    })
    @ParameterizedTest
    void 딜러의_카드를_뽑아야_한다면_카드를_뽑는다(boolean canDrawMoreCard, boolean expected, int expectedSize) {
        CardDeck cardDeck = new CardDeck(new Cards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR))
        ));
        BlackJackGame blackJackGame = new BlackJackGame(cardDeck);

        Dealer dealer = new FakeDealer("딜러", canDrawMoreCard);

        assertThat(blackJackGame.drawMoreCard(dealer)).isEqualTo(expected);
        assertThat(dealer.getCards().getValues()).hasSize(expectedSize);
    }

    @Test
    void 게임_시작시_플레이어들에게_카드_두장을_나눠준다() {
        CardDeck cardDeck = new CardDeck(new Cards(
                List.of(createCard(CardNumber.TWO), createCard(CardNumber.THREE), createCard(CardNumber.FOUR),
                        createCard(CardNumber.FIVE))
        ));
        BlackJackGame blackJackGame = new BlackJackGame(cardDeck);
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        List<BlackJackPlayer> blackJackPlayers = List.of(dealer, player);

        blackJackGame.dealInitialCards(blackJackPlayers);

        assertThat(dealer.getCards().getValues()).hasSize(2);
        assertThat(player.getCards().getValues()).hasSize(2);
    }

    private static class FakeDealer extends Dealer {

        private final boolean canDrawMoreCard;

        public FakeDealer(String name, boolean canDrawMoreCard) {
            super(name);
            this.canDrawMoreCard = canDrawMoreCard;
        }

        @Override
        public boolean canDrawMoreCard() {
            return canDrawMoreCard;
        }
    }

}
