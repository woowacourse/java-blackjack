package domain.participants;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Dealer extends Gamer {
    public static final int BUST_THRESHOLD = 16;

    private final Deck deck;

    public Dealer(Deck deck) {
        super();
        this.deck = deck;
    }

    private Dealer(Dealer dealer){
        super(dealer);
        this.deck = dealer.deck;
    }

    @Override
    public Gamer newInstance() {
        return new Dealer(this);
    }

    public boolean isDrawable() {
        return this.isDrawable(BUST_THRESHOLD);
    }

    public Card drawCard() {
        return deck.draw();
    }

    public Card showAnyOneCard() {
        List<Card> dealerCard = getCards();
        if (dealerCard.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다.");
        }
        return dealerCard.getFirst();
    }
}
