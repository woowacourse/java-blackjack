package domain;

import common.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final int BUST_CRITERIA = 21;
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeck(CardCreationStrategy strategy) {
        List<Card> cards = strategy.create();
        return new Deck(cards);
    }

    public static Deck createParticipantDeck(Deck totaldeck) {
        List<Card> cards = new ArrayList<>(
                List.of(
                        totaldeck.drawCard(),
                        totaldeck.drawCard()
                )
        );
        return new Deck(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
        }

        return cards.removeFirst();
    }

    public boolean isBust() {
        return calculateCardScoreSum() > BUST_CRITERIA;
    }

    public int calculateCardScoreSum() {
        int sumExceptAce = calculateCardScoreSumExceptAce();
        int sumAce = new AceScoreDiscriminator().calculateAceCardsSum(cards, sumExceptAce);

        return sumAce + sumExceptAce;
    }

    public int addCard(Card card) {
        this.cards.add(card);
        return cards.size();
    }

    private int calculateCardScoreSumExceptAce() {
        int sum = 0;
        for (Card card : cards) {
            sum = addCardScoreExceptAce(card, sum);
        }

        return sum;
    }

    private int addCardScoreExceptAce(Card card, int sum) {
        if (!card.isAce()) {
            sum += card.getCardContents().getScore();
        }
        return sum;
    }
}
