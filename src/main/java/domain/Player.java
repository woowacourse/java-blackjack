package domain;

import java.util.List;
import meesage.OutputMessage;

public class Player {

    private List<Card> cards;
    private final String name;

    private Player(List<Card> cards, String name) {
        this.cards = cards;
        this.name = name;
    }

    public static Player of(List<Card> cards, String name) {
        return new Player(cards, name);
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

    public boolean isBustWithAce(int cardScore) {
        return cardScore > 21;
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public String getPlayerInfo() {
        return getName() + OutputMessage.CARD_TEXT.getMessage() + OutputMessage.format(getCardsInfo());
    }

    public String getPlayerScoreResult() {
        return getPlayerInfo() + OutputMessage.RESULT_TEXT.getMessage() + calculateScore();
    }

    public String getName() {
        return name;
    }
}
