package blackjack.model;

import static blackjack.model.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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

        Dealer dealer = new Dealer();

        assertThat(blackJackGame.drawDealerCards(dealer)).isEqualTo(expected);
        assertThat(dealer.getCards().getValues()).hasSize(expectedSize);
    }

    private static class FakeRule extends Rule {

        private boolean shouldDealerDraw;

        public FakeRule(boolean shouldDealerDraw) {
            this.shouldDealerDraw = shouldDealerDraw;
        }

        @Override
        public boolean shouldDealerDraw(final Dealer dealer) {
            return shouldDealerDraw;
        }

    }

}
