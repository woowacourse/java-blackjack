package team.blackjack.domain.rule;

import java.util.List;
import team.blackjack.domain.Card;
import team.blackjack.domain.Result;

public interface BlackjackRule {
    int getBlackjackScore();

    int getDealerStandScore();

    int getBlackjackCardCount();

    boolean isBust(int score);

    boolean isBlackjack(int score, int cardCount);

    boolean isDealerMustDraw(int score);

    boolean canUseAceAsEleven(int currentSum);

    Result judgePlayerResult(int playerScore, int dealerScore);

    int calculateBestScore(List<Card> cards);
}
