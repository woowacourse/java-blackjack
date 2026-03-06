package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_SCORE = 21;

    protected final String name;
    protected final Hands hands;

    public Participant(String name, Hands hands) {
        if (hands == null) {
            throw new IllegalArgumentException("hands가 null입니다.");
        }

        this.name = name;
        this.hands = hands;
    }

    public abstract void pickInitCards(CardDeck cardDeck);

    //핸즈의 총 점수가 21 초과이면 true를 반환한다.
    public boolean isBust() {
        return hands.isTotalScoreOver(BLACKJACK_SCORE);
    }

    public List<Card> getAllCard() {
        return hands.getAllCard();
    }

    public List<Card> getOpenedCards() {
        return hands.getOpenedCards();
    }

    public int getCurrentTotalScore() {
        return hands.calculateTotalScore();
    }

    public void pickAdditionalCard(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
    }

    public String getName() {
        return this.name;
    }
}
