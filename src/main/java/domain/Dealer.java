package domain;

import java.util.List;
import meesage.OutputMessage;

public class Dealer {

    private static final String NAME = "딜러";

    private List<Card> cards;

    private Dealer(List<Card> cards) {
        this.cards = cards;
    }

    public static Dealer of(List<Card> cards) {
        return new Dealer(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int cardScore = calculateRawScore();
        int aceCount = countAce();

        for (int i = 0; i < aceCount; i++) {
            cardScore = adjustForAce(cardScore);
        }

        return cardScore;
    }

    private int calculateRawScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private int adjustForAce(int cardScore) {
        if (isBustWithAce(cardScore)) {
            cardScore -= 10;
        }
        return cardScore;
    }

    private boolean isBustWithAce(int cardScore) {
        return cardScore > 21;
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public String getDealerInfo() {
        return NAME + OutputMessage.CARD_TEXT.getMessage() + OutputMessage.format(getCardsInfo());
    }

    public String getDealerInitialInfo() {
        return NAME + OutputMessage.CARD_TEXT.getMessage() + getCardsInfo().getFirst();
    }

    public boolean shouldHit(){
        return calculateScore() < 16;
    }

}