package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameRule;
import blackjack.exception.ExceptionMessage;
import java.util.List;

public class Player {

    private final GameUser gameUser;
    private BettingAmount bettingAmount;

    public Player(Nickname nickname) {
        this.gameUser = new GameUser(nickname);
    }

    public void registerBettingAmount(BettingAmount bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public void addInitialCards(List<Card> cards) {
        gameUser.addCardInHand(cards);
    }

    public void hit(Card card) {
        validateHitPossibility();
        gameUser.addCardInHand(card);
    }

    public boolean checkHitPossibility() {
        return GameRule.checkPossibilityOfHit(gameUser.getPoint());
    }

    public List<Card> getHand() {
        return gameUser.getHand();
    }

    public int getPoint() {
        return gameUser.getPoint();
    }

    public String getNickname() {
        return gameUser.getNickname();
    }

    public BettingAmount getBettingAmount() {
        return bettingAmount;
    }

    private void validateHitPossibility() {
        if (!checkHitPossibility()) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_HIT.getContent());
        }
    }
}
