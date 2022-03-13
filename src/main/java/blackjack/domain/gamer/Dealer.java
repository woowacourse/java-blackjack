package blackjack.domain.gamer;

import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Gamer {

    private static final int DRAWABLE_NUMBER = 16;
    private static final String CAN_NOT_DRAWABLE_ERROR_MESSAGE = "%s 카드의 합이 %d을 초과했기 때문에 카드를 뽑을 수 없습니다.";
    private static final String WIN_DRAW_LOSE_RESULT_DELIMITER = " ";

    private final Map<WinDrawLose, Integer> winDrawLose = new EnumMap<>(WinDrawLose.class);

    public Dealer(final Cards cards) {
        super("딜러", cards);
    }

    public boolean checkHitFlag() {
        return getCards().calculateScore() <= DRAWABLE_NUMBER;
    }

    @Override
    public void drawCard(Card card) {
        if (!canDraw()) {
            throw new IllegalArgumentException(String.format(CAN_NOT_DRAWABLE_ERROR_MESSAGE, name, DRAWABLE_NUMBER));
        }
        cards.add(card);
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAWABLE_NUMBER;
    }

    @Override
    public List<Card> getViewCard() {
        return List.of(getCards().getCards().get(0));
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
}
