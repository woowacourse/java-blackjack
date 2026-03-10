package domain;

import constant.Word;

public class Dealer extends Member {

    public Dealer() {
        super(Word.DEALER.getWord());
    }

    @Override
    public MatchResult compareScoreWith(Member player) {
        int dealerScore = currentValue();
        int playerScore = player.currentValue();

        if (dealerScore > BUST_CONDITION && playerScore > BUST_CONDITION) {
            return MatchResult.WIN;
        }
        if (playerScore > BUST_CONDITION) {
            return MatchResult.WIN;
        }
        if (dealerScore > BUST_CONDITION) {
            return MatchResult.LOSE;
        }

        return calculateResultFromNormalCase(dealerScore, playerScore);
    }
}
