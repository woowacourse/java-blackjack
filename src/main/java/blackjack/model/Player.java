package blackjack.model;

import java.util.List;

public class Player {

    private static final int BLACKJACK_SCORE = 21;

    private final String name;
    private final Hands hands;

    private Player(String name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public static Player of(String name) {
        return new Player(name, Hands.empty());
    }

    public void pickInitCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        hands.addCard(cardDeck.pick());
    }

    //핸즈의 총 점수가 21 초과이면 true를 반환한다.
    public boolean isBust() {
        return hands.isTotalScoreOver(BLACKJACK_SCORE);
    }

    public int getHandsTotalScore() {
        return hands.calculateTotalScore();
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getAllCard() {
        return hands.getAllCard();
    }

    public int getCurrentTotalScore() {
        return hands.calculateTotalScore();
    }

    public List<Card> getHeadCards() {
        return hands.getOpenedCards();
    }

    public void pickAdditionalCard(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
    }
}
