package blackjack;

import java.util.List;

public class BlackJack {

    public static boolean play(List<String> dealerCards, List<String> playerCards) {
        int dealerScore = totalScore(dealerCards);
        int playerScore = totalScore(playerCards);
        return dealerScore > playerScore;
    }

    private static int totalScore(List<String> dealerCards) {
        int score = 0;
        for (String s : dealerCards) {
            String value = s.substring(0, 1);
            int number = Integer.parseInt(value);
            score += number;
        }
        return score;
    }
}
