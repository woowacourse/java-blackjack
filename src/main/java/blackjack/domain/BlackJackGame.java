package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;

import java.util.List;

public class BlackJackGame {

    private static final int INIT_DISTRIBUTION_COUNT = 2;

    private final CardFactory cardFactory;

    public BlackJackGame() {
        this.cardFactory = new CardFactory(Card.getCards());
    }

    public void initDistribution(Gamer dealer, List<Gamer> players) {
        for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
            distributeCard(dealer);
            players.forEach(this::distributeCard);
        }
    }

    public void distributeCard(Gamer gamer) {
        gamer.addCard(cardFactory.draw());
    }
}
