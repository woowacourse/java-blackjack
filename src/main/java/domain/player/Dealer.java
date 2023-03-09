package domain.player;

import domain.card.CardHolder;
import domain.gameresult.GameResult;
import domain.gameresult.GameResultDecider;

public class Dealer extends Player {

    public static final int DEALER_MIN_SCORE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer(CardHolder cardHolder) {
        super(cardHolder, Name.dealerName());
    }

    public void battle(Player participant, GameResult gameResult) {
        GameResultDecider.decideGameResult(this, participant, gameResult);
    }

    @Override
    public boolean isNameEqualTo(String name) {
        return getName().equals(name);
    }
}
