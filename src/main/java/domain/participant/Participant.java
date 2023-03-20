package domain.participant;

import domain.card.Card;
import domain.card.Cards;

public abstract class Participant {

    private static final int INITIAL_CARDS_SIZE = 2;
    protected static final String INITIAL_CARD_SIZE_MSG = "초기 카드는 2장이어야 합니다.";
    protected static final String NO_CARDS_MSG = "카드를 먼저 받으세요.";

    protected final String name;
    protected Cards cards;

    public Participant(String name) {
        this.name = name;
    }

    public Participant(String name, Cards cards) {
        validateCardsSize(cards);
        this.name = name;
        this.cards = cards;
    }

    public void initCards(Cards cards) {
        validateCardsSize(cards);
        this.cards = cards;
    }

    private void validateCardsSize(Cards cards) {
        if (cards.getSize() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException(INITIAL_CARD_SIZE_MSG);
        }
    }

    public void hit(Card card) {
        validateExistenceCards();
        cards.add(card);
    }

    protected void validateExistenceCards() {
        if (cards == null) {
            throw new IllegalArgumentException(NO_CARDS_MSG);
        }
    }

    public boolean isBurst() {
        return cards.isBurst();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean isHighScoreThan(Participant otherParticipant) {
        return cards.getScore() > otherParticipant.cards.getScore();
    }

    public String getName() {
        return name;
    }

    public abstract boolean canAddCard();

    public Cards getCards() {
        validateExistenceCards();
        return cards;
    }
}
