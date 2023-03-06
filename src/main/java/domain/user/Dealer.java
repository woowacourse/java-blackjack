package domain.user;

import domain.card.Card;
import domain.game.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int RECORD_INITIAL_VALUE = 0;

    private DealerStatus status = DealerStatus.UNDER_MIN_SCORE;
    private Map<Result, Integer> winningRecord;

    public Dealer() {
        super();
        initWinningRecord();
    }

    private void initWinningRecord() {
        winningRecord = new HashMap<>();
        winningRecord.put(Result.WIN, RECORD_INITIAL_VALUE);
        winningRecord.put(Result.DRAW, RECORD_INITIAL_VALUE);
        winningRecord.put(Result.LOSE, RECORD_INITIAL_VALUE);
    }

    @Override
    public void receiveCard(Card card) {
        super.receiveCard(card);
        status = score.calculateDealerStatus();
    }

    @Override
    public void receiveCards(List<Card> receivedCards) {
        super.receiveCards(receivedCards);
        status = score.calculateDealerStatus();
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }

    @Override
    public void win(User player) {
        winningRecord.put(Result.WIN, winningRecord.getOrDefault(Result.WIN, RECORD_INITIAL_VALUE) + 1);
        player.lose();
    }

    public void draw(Player player) {
        winningRecord.put(Result.DRAW, winningRecord.getOrDefault(Result.DRAW, RECORD_INITIAL_VALUE) + 1);
        player.draw();
    }

    @Override
    public void lose() {
        winningRecord.put(Result.LOSE, winningRecord.getOrDefault(Result.LOSE, RECORD_INITIAL_VALUE) + 1);
    }

    @Override
    public DealerStatus getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    public Map<Result, Integer> getWinningRecord() {
        return winningRecord;
    }
}
