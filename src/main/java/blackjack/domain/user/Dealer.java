package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameRule;
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

    public boolean checkPossibilityOfDrawing() {
        return GameRule.checkPossibilityOfDealerDrawing(gameUser.getPoint());
    }

    public void addCardUntilLimit(Card card) {
        boolean isPossible = GameRule.checkPossibilityOfDealerDrawing(gameUser.getPoint());
        if (isPossible) {
            gameUser.addCardInHand(card);
        }
    }

    public Card findFirstCard() {
        validateBeforeDrawInitialCards();
        List<Card> cards = gameUser.getHand();
        return cards.getFirst();
    }

    public String getDealerName() {
        return gameUser.getNickname();
    }

    public List<Card> getHand() {
        return gameUser.getHand();
    }

    public int getPoint() {
        return gameUser.getPoint();
    }

    public boolean isBust() {
        return gameUser.isBust();
    }

    private void validateBeforeDrawInitialCards() {
        if (gameUser.getHand().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.BEFORE_CARD_DISTRIBUTION.getContent());
        }
    }
}
