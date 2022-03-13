package blackjack.domain.gamer;

import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Player extends Gamer {

    private static final int DRAWABLE_NUMBER = 21;
    private static final String CAN_NOT_DRAWABLE_ERROR_MESSAGE = "%s 카드의 합이 %d을 초과했기 때문에 카드를 뽑을 수 없습니다.\n";

    private WinDrawLose winDrawLose;

    public Player(String name, Cards cards) {
        super(name, cards);
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
        return getCards().getCards();
    }

    @Override
    public void win() {
        winDrawLose = WinDrawLose.WIN;
    }

    @Override
    public void draw() {
        winDrawLose = WinDrawLose.DRAW;
    }

    @Override
    public void lose() {
        winDrawLose = WinDrawLose.LOSE;
    }

    @Override
    public String getWinDrawLoseString() {
        return winDrawLose.getName();
    }
}
