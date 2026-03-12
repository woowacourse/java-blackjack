package team.blackjack.domain.rule;

import static team.blackjack.domain.Result.BLACKJACK;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.domain.Card;
import team.blackjack.domain.Participant;
import team.blackjack.domain.Result;

public class DefaultBlackjackRule {
    private static final int BLACKJACK = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int DEALER_STAND_SCORE = 17;

    private DefaultBlackjackRule() {
    }

    public static boolean isBust(int score) {
        return score > BLACKJACK;
    }

    public static boolean isBlackjack(int score, int cardCount) {
        if (cardCount != BLACKJACK_CARD_COUNT) {
            return false;
        }
        return score == BLACKJACK;
    }

    public static boolean shouldDealerHit(int score) {
        return score < DEALER_STAND_SCORE;
    }

    public static boolean canUseAceAsEleven(int currentSum) {
        return currentSum <= 10;
    }

    public static Result judge(Participant me, Participant target) {
        if (me.isBust()) {
            return Result.LOSE;
        }

        if (target.isBust()) {
            return Result.WIN;
        }

        final int score = me.getScore();
        final int targetScore = target.getScore();

        if (score < targetScore) {
            return Result.LOSE;
        }

        if (me.isBlackjack() && !target.isBlackjack()) {
            return Result.BLACKJACK;
        }

        if (score > targetScore) {
            return Result.WIN;
        }

        return Result.DRAW;
    }

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
            return card.getScore().stream()
                    .max(Integer::compareTo)
                    .orElseThrow(() -> new IllegalStateException("ACE 카드의 점수가 존재하지 않습니다."));
        }

        return card.getScore().stream()
                .min(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("ACE 카드의 점수가 존재하지 않습니다."));
    }

    private static int calculateBestSumWithoutAce(List<Card> cards) {
        return cards.stream()
                .flatMap(card -> card.getScore().stream())
                .mapToInt(Integer::intValue)
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
