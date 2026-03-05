package domain.card;

import java.util.List;
import java.util.Objects;

public class CardBundle {

    private List<Card> cardBundle;

    private CardBundle(List<Card> cardBundle) {
        this.cardBundle = cardBundle;
    }

    public static CardBundle of(List<Card> cardBundle) {
        return new CardBundle(cardBundle);
    }

    // TODO Method Name Refactor
    public void addUp(Card card) {
        cardBundle.add(card);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        CardBundle that = (CardBundle) object;
        return Objects.equals(cardBundle, that.cardBundle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cardBundle);
    }

}
