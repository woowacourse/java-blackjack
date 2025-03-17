package blackjack.fixture;

import blackjack.controller.BlackjackController;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Gamer;

public class BlackjackControllerFixture {

    public static BlackjackController BlackjackControllerWith(Deck deck) {
        return new BlackjackController() {
            @Override
            public void drawStartingCards(Gamer gamer) {
                drawCard(gamer, STARTING_CARDS_SIZE);
            }

            @Override
            public void drawCard(Gamer gamer) {
                gamer.drawCard(deck);
            }

            private void drawCard(Gamer gamer, int count) {
                for (int i = 0; i < count; i++) {
                    gamer.drawCard(deck);
                }
            }
        };
    }
}
