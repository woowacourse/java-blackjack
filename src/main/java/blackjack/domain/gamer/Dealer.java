package blackjack.domain.gamer;

import blackjack.domain.GameManager;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardStrategy;

public class Dealer extends Gamer implements GameManager {

    private static final int MIN_SUM_OF_CARDS = 16;

    private final Deck deck = Deck.generateFrom(new RandomCardStrategy());

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canReceiveAdditionalCards() {
        return cards.sum() <= MIN_SUM_OF_CARDS;
    }

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
}
