package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private final Gamer dealer;
    private final List<Gamer> players;
    private final CardFactory cardFactory;

    public BlackJackGame(Gamer dealer, List<Gamer> players) {
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
        this.cardFactory = new CardFactory(Card.getCards());
    }

    public void initDistribution() {
        for (int i = 0; i < 2; i++) {
            dealer.addCard(cardFactory.draw());
            players.forEach(player -> player.addCard(cardFactory.draw()));
        }
    }
}
