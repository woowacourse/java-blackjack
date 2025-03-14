package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameResultType;
import blackjack.domain.game.GameRule;
import blackjack.domain.game.PlayerProfit;
import blackjack.exception.ExceptionMessage;
import java.util.List;

public class Player {

    private final GameUser gameUser;
    private BettingAmount bettingAmount;

    public Player(Nickname nickname) {
        this.gameUser = new GameUser(nickname);
        this.bettingAmount = new BettingAmount(0);
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

    public PlayerProfit calculateProfit(Dealer dealer) {
        if (checkBlackjackWithInitialCard()) {
            return PlayerProfit.createWhenPlayerBlackjackWithInitialCard(this);
        }
        if (dealer.isBust()) {
            return PlayerProfit.createWhenDealerBust(this);
        }
        if (isBust()) {
            return PlayerProfit.createWhenPlayerBust(this);
        }
        return PlayerProfit.createByWinningType(this, GameResultType.parse(getPoint(), dealer.getPoint()));
    }

    public boolean isBust() {
        return gameUser.isBust();
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

    public int getBettingAmount() {
        return bettingAmount.getValue();
    }

    private void validateHitPossibility() {
        if (!checkHitPossibility()) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_HIT.getContent());
        }
    }

    private boolean checkBlackjackWithInitialCard() {
        boolean isBlackjack = GameRule.checkBlackJack(gameUser.getPoint());
        boolean isInitialHand = GameRule.checkInitialHand(gameUser.getHand().size());
        return isBlackjack && isInitialHand;
    }
}
