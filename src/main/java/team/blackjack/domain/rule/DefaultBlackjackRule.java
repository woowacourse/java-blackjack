package team.blackjack.domain.rule;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.domain.Card;
import team.blackjack.domain.Result;

public class DefaultBlackjackRule {
    public static final int BLACKJACK = 21;
    public static final int BLACKJACK_CARD_COUNT = 2;
    public static final int DEALER_STAND_SCORE = 17;

    public static boolean isBust(int score) {
        return score > BLACKJACK;
    }

    public static boolean isBlackjack(int score, int cardCount) {
        if (cardCount != BLACKJACK_CARD_COUNT) {
            return false;
        }
        return score == BLACKJACK;
    }

    public static boolean isDealerMustDraw(int score) {
        return score < DEALER_STAND_SCORE;
    }

    public static boolean canUseAceAsEleven(int currentSum) {
        return currentSum <= 10;
    }

    public static Result judgePlayerResult(int playerScore, int dealerScore) {
        if (playerScore > BLACKJACK) {
            return Result.LOSE;
        }
        if (dealerScore > BLACKJACK) {
            return Result.WIN;
        }
        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore < dealerScore) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    /**
     * 모든 카드를 발급한 이후에, 최종 점수 계산시에 사용하는 함수
     */
    public static int calculateBestScore(List<Card> cards) {
        if (existAceInCards(cards)) {
            final Map<Boolean, List<Card>> result = cards.stream()
                    .collect(Collectors.partitioningBy(Card::isAce));

            final List<Card> aceCards = result.get(true);
            final List<Card> nonAceCards = result.get(false);
            return calculateBestSumWithAce(nonAceCards, aceCards);
        }
        return calculateBestSumWithoutAce(cards);
    }

    private static int calculateBestSumWithAce(List<Card> cardsWithoutAces, List<Card> aceCards) {
        int currentSum = calculateBestSumWithoutAce(cardsWithoutAces);

        for (Card card : aceCards) {
            currentSum += aceScore(card, currentSum);
        }

        return currentSum;
    }

    private static int aceScore(Card card, int currentSum) {
        if (DefaultBlackjackRule.canUseAceAsEleven(currentSum)) {
            return card.getScore().getLast();
        }

        return card.getScore().getFirst();
    }

    private static int calculateBestSumWithoutAce(List<Card> cards) {
        return cards.stream()
                .mapToInt(card -> card.getScore().getFirst())
                .sum();
    }

    private static boolean existAceInCards(List<Card> cards) {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }
}
