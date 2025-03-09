package domain.game;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    public static final int DEALER_DRAW_BOUND = 16;

    public List<GameResult> judgeGameResult(List<Player> players) {
        List<GameResult> gameResult = new ArrayList<>();
        for (Player player : players) {
            gameResult.add(judgeWin(player));
        }
        return gameResult;
    }

    private GameResult judgeWin(Player player) {
        return GameResult.of(this, player);
    }

    public boolean isOverDrawBound() {
        return calculateTotalCardNumber() > DEALER_DRAW_BOUND;
    }

    public Card openSingleCard() {
        return cards.getFirst();
    }
}
