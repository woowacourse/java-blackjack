package blackjack.domain.player;

import blackjack.domain.BettingMoney;
import blackjack.domain.DrawStatus;
import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player extends Participant {

    private static final int FIRST_OPEN_COUNT = 2;

    private final BettingMoney bettingMoney;

    public Player(String name, Cards cards) {
        super(name, cards);
        this.bettingMoney = BettingMoney.emptyMoney();
    }

    public Player(String name, int money, Cards cards) {
        super(name, cards);
        this.bettingMoney = new BettingMoney(money);
    }

    public GameResult findResult(Dealer dealer) {
        return GameResult.findPlayerResult(this, dealer);
    }

    public boolean isPossibleToHit(DrawStatus drawStatus) {
        return isBust() && isHit(drawStatus);
    }

    public boolean isHit(DrawStatus drawStatus) {
        return drawStatus == DrawStatus.YES;
    }

    @Override
    public List<Card> openFirstCards() {
        return cards.getCards().subList(0, FIRST_OPEN_COUNT);
    }

    @Override
    public boolean isBust() {
        return cards.calculateScore() > MAX_BLACKJACK_SCORE;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
