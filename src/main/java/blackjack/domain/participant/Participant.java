package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    public static final String EMPTY_NAME_ERROR = "이름에 빈 값을 사용할 수 없습니다.";
    private static final int BLACKJACK_CARD_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    protected final List<Card> cards;
    private final String name;


    public Participant(final String name) {
        validateEmptyName(name);
        this.name = name;
        this.cards = new ArrayList<>();
    }

    private void validateEmptyName(final String name) {
        if ("".equals(name)) {
            throw new IllegalArgumentException(EMPTY_NAME_ERROR);
        }
    }

    public void receiveInitialCards(final Card firstCard, final Card secondCard) {
        cards.add(firstCard);
        cards.add(secondCard);
    }

    public void receiveOneCard(final Card card) {
        cards.add(card);
    }

    public int cardCount() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String getName() {
        return name;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_CARD_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_COUNT && calculateScore() == BLACKJACK_CARD_SCORE;
    }

    public int calculateScore() {
        final int sumOfCards = cards.stream().mapToInt(card -> card.getCardValue().getValue()).sum();
        if (sumOfCards > BLACKJACK_CARD_SCORE && howManyAce() > 0) {
            return calculateScoreWithAce(sumOfCards);
        }
        return sumOfCards;
    }

    private int calculateScoreWithAce(final int sum) {
        int aceCount = howManyAce();
        int changedSum = sum;
        while (aceCount-- > 0 && changedSum > BLACKJACK_CARD_SCORE) {
            changedSum = changedSum - CardNumber.ACE.getValue() + CardNumber.ACE.getExtraValue();
        }
        return changedSum;
    }

    private int howManyAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public abstract List<Card> getInitialCards();

    public abstract boolean checkMoreCardAvailable();
}
