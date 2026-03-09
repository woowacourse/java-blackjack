package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardBundle {

    private static final int BUSTED_CONDITION = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final List<Card> cardBundle;

    private CardBundle(List<Card> cardBundle) {
        this.cardBundle = new ArrayList<>(cardBundle);
    }

    public static CardBundle from(List<Card> cardBundle) {
        return new CardBundle(cardBundle);
    }

    public static CardBundle empty() {
        return new CardBundle(new ArrayList<>());
    }

    public CardBundle add(CardBundle newCardBundle) {
        List<Card> combinedCards = new ArrayList<>(this.cardBundle);
        combinedCards.addAll(newCardBundle.cardBundle);
        return new CardBundle(combinedCards);
    }

    public CardBundle add(final Card card) {
        List<Card> newCards = new ArrayList<>(this.cardBundle);
        newCards.add(card);
        return new CardBundle(newCards);
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

    public boolean checkExist(Card targetCard) {
        return cardBundle.stream().
                anyMatch(card -> card.equals(targetCard));
    }

    public List<String> toDisplay() {
        return cardBundle.stream()
                .map(Card::toDisplay)
                .toList();
    }

    public int getResultScore() {
        int basicScore = getBasicScore();
        if (hasAce() && (basicScore + ACE_BONUS_SCORE <= BUSTED_CONDITION)) {
            return basicScore + ACE_BONUS_SCORE;
        }

        return basicScore;
    }

    public int getBasicScore() {
        return cardBundle.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isBusted() {
        return getBasicScore() > BUSTED_CONDITION;
    }

    public boolean hasAce() {
        return cardBundle.stream()
                .anyMatch(Card::isAce);
    }

}
