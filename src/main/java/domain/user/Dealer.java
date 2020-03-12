package domain.user;

import domain.card.Card;

public class Dealer extends Player {
    private static final int SHOULD_ADD_CARD_POINT = 16;
    private static final String NAME = "딜러";
    private static final int FIRST_CARD_INDEX = 0;

    public Dealer() {
        super(NAME);
    }

    public boolean shouldAddCard() {
        return getScore() <= SHOULD_ADD_CARD_POINT;
    }

    public boolean isWin(Player opponentPlayer) {
        return isNotBust() && (opponentPlayer.isBust() || getScore() > opponentPlayer.getScore());
    }

    public String getFirstCardInfo() {
        Card FirstCard = getCards().get(FIRST_CARD_INDEX);
        return FirstCard.getCardInfo();
    }
}
