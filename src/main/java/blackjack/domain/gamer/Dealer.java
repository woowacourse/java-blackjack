package blackjack.domain.gamer;

import blackjack.domain.MatchResult;
import blackjack.domain.Score;
import blackjack.domain.card.Cards;

public class Dealer extends Person {
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super.name = DEALER_NAME;
        super.cards = new Cards();
    }

    public MatchResult matchGame(Player player) {
        return MatchResult.getPlayerMatchResult(Score.calculatorScore(player.cards), Score.calculatorScore(cards));
    }

    @Override
    public boolean canDraw() {
        return Score.calculatorScore(cards) <= 16;
    }
}

