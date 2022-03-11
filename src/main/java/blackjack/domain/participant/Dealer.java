package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    private static final Name DEALER_NAME = new Name("딜러");
    private static final int DEALER_OVER_SCORE = 17;

    private final Deck deck;

    public Dealer(Hand hand) {
        this(hand, Deck.createShuffledCards());
    }

    public Dealer(Hand hand, Deck deck) {
        this(DEALER_NAME, hand, deck);
    }

    public Dealer(Name name, Hand hand, Deck deck) {
        super(name, hand);
        this.deck = deck;
        validateNull(deck);
    }

    private void validateNull(Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("[ERROR] 덱은 null일 수 없습니다.");
        }
    }

    public static Dealer getInstance() {
        return new Dealer(new Hand());
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void drawBaseCardHand() {
        selfDraw();
        selfDraw();
    }

    public void selfDraw() {
        cardHand.add(drawCard());
    }

    public boolean shouldReceive() {
        return cardHand.getScore() < DEALER_OVER_SCORE;
    }

    public Card getOpenCard() {
        return cardHand.getCards().get(0);
    }

    public void drawCardToPlayers(List<Player> players) {
        for (Player player : players) {
            giveCard(player);
            giveCard(player);
        }
    }

    public void giveCard(Player player) {
        player.receiveCard(drawCard());
    }
}
