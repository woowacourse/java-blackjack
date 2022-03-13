package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends AbstractPlayer {
    private static final String DEALER_NAME = "딜러";
    private static final int HIT_FLAG_SCORE = 16;
    private static final String WIN_DRAW_LOSE_RESULT_DELIMITER = " ";

    private final Map<WinDrawLose, Integer> winDrawLose = new EnumMap<>(WinDrawLose.class);

    public Dealer() {
        super(DEALER_NAME, new Cards(new ArrayList<>()));
    }

    @Override
    public Cards getShowCards() {
        return new Cards(List.of(getCards().getCards().get(0)));
    }

    @Override
    public void win() {
        winDrawLose.merge(WinDrawLose.WIN, 1, Integer::sum);
    }

    @Override
    public void draw() {
        winDrawLose.merge(WinDrawLose.DRAW, 1, Integer::sum);
    }

    @Override
    public void lose() {
        winDrawLose.merge(WinDrawLose.LOSE, 1, Integer::sum);
    }

    @Override
    public String getWinDrawLoseString() {
        return winDrawLose.entrySet().stream()
                .map(set -> set.getValue() + set.getKey().getName())
                .collect(Collectors.joining(WIN_DRAW_LOSE_RESULT_DELIMITER));
    }

    @Override
    public HitFlag checkHitFlag() {
        if (getCards().calculateScore() <= HIT_FLAG_SCORE) {
            return HitFlag.Y;
        }
        return HitFlag.N;
    }
}
