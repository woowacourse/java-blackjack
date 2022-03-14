package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

<<<<<<< HEAD
=======
    private static final int INIT_DISTRIBUTE_SIZE = 2;

>>>>>>> step1
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateInitCards(cards);
        this.cards = new ArrayList<>();
        addCards(cards);
    }

<<<<<<< HEAD
    private void validateInitCards(final List<Card> cards) {
        if (cards == null || cards.size() != Deck.INIT_DISTRIBUTE_SIZE) {
=======
    public static List<Card> createInitCards(Deck deck) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i< INIT_DISTRIBUTE_SIZE; i++){
            cards.add(deck.draw());
        }
        return cards;
    }

    private void validateInitCards(final List<Card> cards) {
        if (cards == null || cards.size() != INIT_DISTRIBUTE_SIZE) {
>>>>>>> step1
            throw new IllegalArgumentException("[ERROR] 잘못 배분된 카드입니다.");
        }
    }

    private void addCards(final List<Card> cards) {
<<<<<<< HEAD
        cards.forEach(this::addCard);
=======
        for (Card card : cards) {
            addCard(card);
        }
>>>>>>> step1
    }

    public void addCard(final Card card) {
        checkCardNull(card);
        cards.add(card);
    }

    private void checkCardNull(final Card card) {
        if (card == null) {
            throw new IllegalArgumentException("[ERROR] 올바른 카드를 입력해주세요.");
        }
    }

    public int calculateScoreByAceOne() {
        return cards.stream().mapToInt(card -> card.getScore().getAmount()).sum();
    }

    public int calculateMaxScore() {
<<<<<<< HEAD
        if (isContainsAce()) {
=======
        if (containsAce()) {
>>>>>>> step1
            return calculateScoreByAceOne() + Score.getDifferenceAcesScore();
        }
        return calculateScoreByAceOne();
    }

<<<<<<< HEAD
    private boolean isContainsAce() {
        return cards.stream().anyMatch(card -> card.getScore() == Score.ACE);
=======
    private boolean containsAce() {
        return cards.stream().anyMatch(card -> card.isScoreAce());
>>>>>>> step1
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
