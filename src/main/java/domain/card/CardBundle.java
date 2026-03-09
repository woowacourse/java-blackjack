package domain.card;

import static domain.config.BlackjackGameConstant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardBundle {

    private final List<Card> cardBundle;

    private CardBundle(List<Card> cardBundle) {
        this.cardBundle = cardBundle;
    }

    public static CardBundle of(List<Card> cardBundle) {
        return new CardBundle(cardBundle);
    }

    public static CardBundle empty() {
        return new CardBundle(new ArrayList<>());
    }

    public CardBundle addUp(CardBundle additionalCardBundle) {
        cardBundle.addAll(additionalCardBundle.cardBundle);
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

    public List<String> toDisplay() {
        return cardBundle.stream()
                .map(Card::toDisplay)
                .toList();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        CardBundle that = (CardBundle) object;
        return Objects.equals(cardBundle, that.cardBundle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cardBundle);
    }

    public boolean checkExist(Card targetCard) {
        return cardBundle.stream().
                anyMatch(c -> c.equals(targetCard));
    }
}
