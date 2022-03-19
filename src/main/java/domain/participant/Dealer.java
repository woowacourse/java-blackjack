package domain.participant;

import java.util.List;

public class Dealer extends Participant {

    private static final int MAX_CARD_SUM = 16;
    private static final String NAME = "딜러";
    private final int SHIFT_NUM = -1;

    public Dealer() {
        super(NAME);
    }

    public int getResultMoney(List<Integer> playersResult) {
        return playersResult.stream().mapToInt(i -> i * SHIFT_NUM).sum();
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateScore() <= MAX_CARD_SUM;
    }
}
