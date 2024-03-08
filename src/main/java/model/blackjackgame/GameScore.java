package model.blackjackgame;

import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;

public class GameScore {

    private static final String DEALER_NAME = "딜러";
    private static final int ACE_SCORE_HIGH = 11;
    private static final int ACE_SCORE_LOW = 1;
    private static final int MINIMUM_SCORE_FOR_ACE_HIGH = 12;
    private static final int BLACKJACK_SCORE = 21;

    private final String name;
    private final int score;

    public GameScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static GameScore from(Player player) {
        Cards cards = player.getCards();
        int totalScore = calculateTotalScore(cards);
        return new GameScore(player.getName(), totalScore);
    }

    public static GameScore from(Dealer dealer) {
        Cards cards = dealer.getCards();
        int totalScore = calculateTotalScore(cards);
        return new GameScore(DEALER_NAME, totalScore);
    }

    private static int calculateTotalScore(Cards cards) {
        List<Card> cardsElement = cards.getElements();
        int totalScore = cards.calculateTotalNumbers();
        int countA = (int) cardsElement.stream()
            .filter(card -> card.getNumber().equals(CardNumber.ACE))
            .count();

        while (totalScore < MINIMUM_SCORE_FOR_ACE_HIGH && countA > 0) {
            totalScore += ACE_SCORE_HIGH - ACE_SCORE_LOW;
            countA--;
        }
        return totalScore;
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBurst() {
        return score > BLACKJACK_SCORE;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
