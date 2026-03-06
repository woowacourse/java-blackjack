package domain.card;

import java.util.ArrayList;
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

    public static CardBundle empty() {
        return new CardBundle(new ArrayList<>());
    }

    public CardBundle addUp(CardBundle newCardBundle) {
        for (Card card : newCardBundle.cardBundle) {
            addUp(card);
        }
        return this;
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

    public boolean checkExist(Card targetCard) {
        return cardBundle.stream().
                anyMatch(c -> c.equals(targetCard));
    }

    public List<String> toDisplay() {
        return cardBundle.stream()
                .map(Card::toDisplay)
                .toList();
    }

    public int getTotalScore() {
        return cardBundle.stream()
                .mapToInt(Card::getScore) // Card 객체를 점수(int)로 변환
                .sum();                   // 합계 계산
    }

    public boolean hasAce() {
        return cardBundle.stream()
                .anyMatch(Card::isAce);
    }

}
