package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameResultType;
import blackjack.domain.game.GameRule;
import blackjack.domain.game.PlayerProfit;
import blackjack.domain.value.BettingAmount;
import blackjack.domain.value.Nickname;
import java.util.List;

public class Player {

    private final GameUser gameUser;
    private BettingAmount bettingAmount;

    public Player(Nickname nickname) {
        this.gameUser = new GameUser(nickname);
        this.bettingAmount = new BettingAmount(0);
    }

    public Player(Nickname nickname, BettingAmount bettingAmount) {
        this.gameUser = new GameUser(nickname);
        this.bettingAmount = bettingAmount;
    }

    public void addBettingAmount(BettingAmount bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public void addInitialCards(List<Card> cards) {
        gameUser.addCardInHand(cards);
    }

    public boolean canHit() {
        return gameUser.getPoint() < GameRule.BUST_THRESHOLD.getValue();
    }

    public void hitUntilLimit(Card card) {
        if (canHit()) {
            gameUser.addCardInHand(card);
        }
    }

    public PlayerProfit calculateProfit(int dealerPoint) {
        GameResultType gameResult = GameResultType.parse(gameUser.getHand().size(), gameUser.getPoint(), dealerPoint);
        int profit = bettingAmount.calculateMultiplication(gameResult.getProfitRate());
        return new PlayerProfit(gameUser.getNickname(), profit);
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
}
