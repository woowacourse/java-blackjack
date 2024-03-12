package blackjack.player;

import blackjack.card.Card;
import blackjack.card.Deck;
import blackjack.game.BlackJackGame;
import java.util.List;

public class Player {

    private final Name name;
    protected Hand hand;

    Player(String name, Hand hand) {
        this.name = new Name(name);
        this.hand = hand;
    }

    Player(String name) {
        this(name, new Hand());
    }

    public void drawCard(Deck deck) {
        this.hand = hand.addCard(deck.draw());
    }

    public void drawCards(Deck deck, int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard(deck);
        }
    }

    public boolean hasDrawableScore() {
        return hand.getScore() < BlackJackGame.BLACKJACK_MAX_SCORE;
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return hand.getScore();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
