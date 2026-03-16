package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;
import java.util.List;

public abstract class Participant {

    protected static final int BLACKJACK_SCORE = 21;

    protected final String name;
    protected final Hands hands;

    public Participant(String name, Hands hands) {
        validate(name, hands);

        this.name = name;
        this.hands = hands;
    }

    private void validate(String name, Hands hands) {
        if (name == null) {
            throw new IllegalArgumentException("name이 null입니다.");
        }

        if (hands == null) {
            throw new IllegalArgumentException("hands가 null입니다.");
        }
    }

    public abstract void pickInitialCards(CardDeck cardDeck);

    public boolean isBust() {
        return hands.isTotalScoreOver(BLACKJACK_SCORE);
    }

    public boolean isBlackjack() {
        return hands.calculateInitialCardScore() == BLACKJACK_SCORE;
    }

    public List<Card> getOpenedCards() {
        return hands.getOpenedCards();
    }

    public int getCurrentTotalScore() {
        return hands.calculateTotalScore();
    }

    public void pickAdditionalCard(CardDeck cardDeck) {
        validateCardDeck(cardDeck);
        hands.addCard(cardDeck.pick());
    }

    public List<Card> getAllCard() {
        return hands.getAllCard();
    }

    public String getName() {
        return this.name;
    }

    protected void validateCardDeck(CardDeck cardDeck) {
        if (cardDeck == null) {
            throw new IllegalArgumentException("카드 덱이 null입니다.");
        }
    }
}
