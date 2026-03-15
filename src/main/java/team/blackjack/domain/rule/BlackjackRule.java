package team.blackjack.domain.rule;

import team.blackjack.domain.Hand;
import team.blackjack.domain.Result;

public interface BlackjackRule {
    boolean isDealerMustDraw(int score);

    Result judgePlayerResult(Hand playerHand, Hand dealerHand);
}
