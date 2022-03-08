package blackjack;

import java.util.List;

public class BlackJack {

    public static boolean play(List<String> dealerCards, List<String> playerCards) {
        int dealerScore = totalScore(dealerCards);
        int playerScore = totalScore(playerCards);
        if (isBust(dealerScore)) {
            return false;
        }
        return dealerScore > playerScore;
    }

    private static boolean isBust(int dealerScore) {
        return dealerScore > 21;
    }

    private static int totalScore(List<String> value) {
        int score1 = totalScore(value, 1);
        int score2 = totalScore(value, 11);

        if (isBust(score2)) {
            return score1;
        }

        return score2;
    }

    private static int totalScore(List<String> dealerCards, int aceValue) {
        int score = 0;
        for (String s : dealerCards) {
            String value = s.substring(0, 1);
            score += number(value, aceValue);
        }
        return score;
    }

    private static int number(String value, int aceValue) {
        if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10;
        }
        if (value.equals("A")) {
            return aceValue;
        }
        return Integer.parseInt(value);
    }
}
