package model.blackjackgame;

import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;

public class GameScore {

    private static final int ACE_SCORE_HIGH = 11;
    private static final int ACE_SCORE_LOW = 1;
    private static final int MINIMUM_SCORE_FOR_ACE_HIGH = 12;

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
        return new GameScore("딜러", totalScore);
    }

    private static int calculateTotalScore(Cards cards) {
        List<Card> cardsElement = cards.getCards();
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

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
