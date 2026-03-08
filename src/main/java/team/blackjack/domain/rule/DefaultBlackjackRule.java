package team.blackjack.domain.rule;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.domain.Card;
import team.blackjack.domain.Result;

public class DefaultBlackjackRule implements BlackjackRule {
    public static final int BLACKJACK = 21;
    public static final int BLACKJACK_CARD_COUNT = 2;
    public static final int DEALER_STAND_SCORE = 17;
    public static final int ACE_UPGRADE_SCORE_THRESHOLD = 10;

    @Override
    public int getBlackjackScore() {
        return BLACKJACK;
    }

    @Override
    public int getBlackjackCardCount() {
        return BLACKJACK_CARD_COUNT;
    }

    @Override
    public int getDealerStandScore() {
        return DEALER_STAND_SCORE;
    }

    @Override
    public boolean isBust(int score) {
        return score > BLACKJACK;
    }

    @Override
    public boolean isBlackjack(int score, int cardCount) {
        if (cardCount != BLACKJACK_CARD_COUNT) {
            return false;
        }
        return score == BLACKJACK;
    }

    @Override
    public boolean isDealerMustDraw(int score) {
        return score < DEALER_STAND_SCORE;
    }

    @Override
    public boolean canUseAceAsEleven(int currentSum) {
        return currentSum <= ACE_UPGRADE_SCORE_THRESHOLD;
    }

    @Override
    public Result judgePlayerResult(int playerScore, int dealerScore) {
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
    public int calculateBestScore(List<Card> cards) {
        if (existAceInCards(cards)) {
            final Map<Boolean, List<Card>> result = cards.stream()
                    .collect(Collectors.partitioningBy(Card::isAce));

            final List<Card> aceCards = result.get(true);
            final List<Card> nonAceCards = result.get(false);
            return calculateBestSumWithAce(nonAceCards, aceCards);
        }
        return calculateBestSumWithoutAce(cards);
    }

    private boolean existAceInCards(List<Card> cards) {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    private int calculateBestSumWithAce(List<Card> cardsWithoutAces, List<Card> aceCards) {
        int currentSum = calculateBestSumWithoutAce(cardsWithoutAces);

        for (Card card : aceCards) {
            currentSum += getAceScore(card, currentSum);
        }

        return currentSum;
    }

    private int calculateBestSumWithoutAce(List<Card> cards) {
        return cards.stream()
                .mapToInt(card -> card.getScore().getFirst())
                .sum();
    }

    private int getAceScore(Card card, int currentSum) {
        if (canUseAceAsEleven(currentSum)) {
            return card.getScore().getLast();
        }

        return card.getScore().getFirst();
    }
}
