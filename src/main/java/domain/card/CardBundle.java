package domain.card;

import static config.BlackjackGameConstant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardBundle {

    private final List<Card> cards;

    private CardBundle(List<Card> cards) {
        this.cards = cards;
    }

    public static CardBundle from(List<Card> cardBundle) {
        return new CardBundle(cardBundle);
    }

    public static CardBundle empty() {
        return new CardBundle(new ArrayList<>());
    }

    public CardBundle addUp(CardBundle additionalCardBundle) {
        cards.addAll(additionalCardBundle.cards);
        return this;
    }

    public int getResultScore() {
        int basicScore = getBasicScore();
        boolean hasAce = hasAce();

        if (hasAce && (basicScore + ACE_BONUS_SCORE <= BUSTED_CONDITION)) {
            return basicScore + ACE_BONUS_SCORE;
        }

        return basicScore;
    }

    public int getBasicScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isBusted() {
        return getBasicScore() > BUSTED_CONDITION;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean checkExist(Card targetCard) {
        return cards.stream().
                anyMatch(c -> c.equals(targetCard));
    }

    public List<String> toDisplay() {
        return cards.stream()
                .map(Card::toDisplay)
                .toList();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        CardBundle that = (CardBundle) object;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
