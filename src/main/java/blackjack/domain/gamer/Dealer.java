package blackjack.domain.gamer;

import blackjack.domain.MatchResult;
import blackjack.domain.Score;
import blackjack.domain.card.Cards;

public class Dealer extends Person {

    public Dealer() {
        super.name = "딜러";
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

