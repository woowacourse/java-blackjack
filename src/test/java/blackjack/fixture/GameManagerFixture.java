package blackjack.fixture;

import blackjack.domain.GameManager;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Gamer;

public class GameManagerFixture {

    public static GameManager GameManagerWith(Deck deck) {
        return new GameManager() {
            @Override
            public void drawStartingCards(Gamer gamer) {
                drawCard(gamer, GameManager.STARTING_CARDS_SIZE);
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
