package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameRule;
import blackjack.domain.value.Nickname;
import blackjack.exception.ExceptionMessage;
import java.util.List;

public class Dealer {

    private final static Nickname DEALER_NICKNAME = new Nickname("딜러");
    private final GameUser gameUser;

    public Dealer() {
        this.gameUser = new GameUser(DEALER_NICKNAME);
    }

    public void addInitialCards(List<Card> cards) {
        gameUser.addCardInHand(cards);
    }

    public boolean canDraw() {
        return gameUser.getPoint() <= GameRule.DEALER_DRAW_THRESHOLD.getValue();
    }

    public void addCardUntilLimit(Card card) {
        boolean isPossible = canDraw();
        if (isPossible) {
            gameUser.addCardInHand(card);
        }
    }

    public Card findFirstCard() {
        validateBeforeDrawInitialCards();
        List<Card> cards = gameUser.getHand();
        return cards.getFirst();
    }

    public Nickname getDealerName() {
        return gameUser.getNickname();
    }

    public List<Card> getHand() {
        return gameUser.getHand();
    }

    public int getPoint() {
        return gameUser.getPoint();
    }

    private void validateBeforeDrawInitialCards() {
        if (gameUser.getHand().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.BEFORE_CARD_DISTRIBUTION.getContent());
        }
    }
}
