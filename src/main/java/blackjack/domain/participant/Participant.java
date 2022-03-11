package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public abstract class Participant {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_NUMBER = 1;
    private static final int ADD_ALTERNATIVE_ACE_VALUE = 10;

    protected String name;
    protected List<Card> cards = new ArrayList<>();

    protected void drawTwoCard(final Deck deck) {
        drawCard(deck);
        drawCard(deck);
    }

    public void drawCard(final Deck deck) {
        cards.add(deck.drawCard());
    }

    public void drawCards(final Deck deck, final CardDrawCallback callback) {
        while (isPossibleToDrawCard() && callback.isContinuable(getParticipantName())) {
            drawCard(deck);
            callback.onUpdate(this);
        }
    }

    public abstract boolean isPossibleToDrawCard();

    protected int calculateScore() {
        int totalSum = calculateWithoutAce();

        if (hasAceCard()) {
            totalSum += 10;
            if (totalSum > BLACKJACK_NUMBER) {
                totalSum -= ADD_ALTERNATIVE_ACE_VALUE;
            }
        }

        return totalSum;
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(card -> card.getNumber() == ACE_NUMBER);
    }

    protected int calculateWithoutAce() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    public boolean isBurst() {
        return calculateScore() > BLACKJACK_NUMBER;
    }

    public String getParticipantName() {
        return name;
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getScore() {
        return calculateScore();
    }

}
