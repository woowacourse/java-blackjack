package domain;

import common.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck implements Iterable<Card> {
    private static final int MAXIMUM_SCORE = 21;
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createTotalDeckAndShuffle(CardShuffleStrategy strategy) {
        List<Card> cards = createAllCards();
        shuffleCards(cards, strategy);
        return new Deck(cards);
    }

    private static List<Card> createAllCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            for (CardContents cardContents : CardContents.values()) {
                Card card = new Card(cardShape, cardContents);
                cards.add(card);
            }
        }
        return cards;
    }

    private static void shuffleCards(List<Card> cards, CardShuffleStrategy strategy) {
        strategy.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
        }

        return cards.removeFirst();
    }

    public boolean isLessThanMaxScore() {
        return calculateCardScoreSum() < MAXIMUM_SCORE;
    }

    public boolean isBust() {
        return calculateCardScoreSum() > MAXIMUM_SCORE;
    }

    public int calculateCardScoreSum() {
        int sumExceptAce = calculateCardScoreSumExceptAce();
        int sumAce = new AceScoreDiscriminator().calculateAceCardsSum(cards, sumExceptAce);

        return sumAce + sumExceptAce;
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

    public boolean isDrawable() {
        return !cards.isEmpty();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public Iterator<Card> iterator() {
        return Collections.unmodifiableList(cards).iterator();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
