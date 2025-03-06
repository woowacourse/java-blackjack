import java.util.ArrayList;
import java.util.List;

public class Cards {

    public static final int BURST_BOUND = 21;
    
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void drawCardWhenStart(CardDeck cardDeck) {
        List<Card> drawnCard = cardDeck.drawCardWhenStart();
        cards.addAll(drawnCard);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void drawCard(CardDeck cardDeck) {
        Card drawnCard = cardDeck.drawCard();
        cards.add(drawnCard);
    }

    public boolean checkBurst() {
        int sum = cards.stream()
                .mapToInt(card -> card.getCardNumber().getNumber())
                .sum();
        return sum > BURST_BOUND;
    }
}
