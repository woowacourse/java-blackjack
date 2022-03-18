package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private static final int CAPACITY = 8;
    public static final int ONE_INDEX = 1;

    private final List<Participant> players;
    private int currentTurnIndex;

    public Participants(List<Participant> players) {
        this.players = new ArrayList<>(players);
        this.currentTurnIndex = 0;
        validateCapacity(this.players);
    }

    private void validateCapacity(List<Participant> players) {
        if (players.size() > CAPACITY) {
            throw new IllegalArgumentException("인원수는 8명을 넘을 수 없습니다.");
        }
    }

    public void drawAll(Drawable drawable) {
        for (Participant player : players) {
            player.drawCard(drawable.draw());
        }
    }

    public ProfitResult compete(Participant dealer) {
        ProfitResult profitResult = new ProfitResult();

        for (Participant player : players) {
            caclculateProfitResult(profitResult, dealer, player);
        }
        return profitResult;
    }

    private void caclculateProfitResult(ProfitResult profitResult, Participant dealer, Participant player) {
        Score competeResult = player.compete(dealer);
        if (player instanceof Player) {
            double playerTotalProfit = ((Player) player).getTotalProfit(competeResult);
            profitResult.putPlayerProfit(player, playerTotalProfit);
        }
    }

    public void drawPlayerCard(Drawable drawable) {
        getCurrentTurnPlayer().drawCard(drawable.draw());
    }

    public boolean isBustCurrentTurnPlayer() {
        return getCurrentTurnPlayer().isBust();
    }

    public boolean isEndPlayersTurn() {
        return currentTurnIndex + ONE_INDEX > players.size();
    }

    public void proceedTurn() {
        if (isEndPlayersTurn()) {
            throw new IllegalStateException("proceed 할 수 없습니다.");
        }
        currentTurnIndex++;
    }

    public Participant getCurrentTurnPlayer() {
        return players.get(currentTurnIndex);
    }

    public List<Participant> getValue() {
        return List.copyOf(players);
    }
}
