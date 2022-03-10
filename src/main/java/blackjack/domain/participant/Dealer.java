package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.List;

public class Dealer {

    private final Name name = new Name("딜러");
    private final Hand cardHand = new Hand();
    private Deck deck;

    public Dealer() {
        this.deck = Deck.createFixedCards();
    }

    public Dealer(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void selfDraw() {
        cardHand.add(drawCard());
    }

    public boolean shouldReceive() {
        return cardHand.getScore() < 17;
    }

    public Card getOpenCard() {
        return cardHand.getCards().get(0);
    }

    public void drawCardToPlayers(List<Player> players) {
        for (Player player : players) {
            player.receiveCard(drawCard());
            player.receiveCard(drawCard());
        }
    }
}
