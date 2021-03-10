
package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Gamer extends Player {
    private static final String PLAYER_SCORE_RANGE_ERROR = "[ERROR] 플레이어의 점수가 21을 초과하여 카드를 추가할 수 없습니다.";
    private static final int DRAW_MAXIMUM_SCORE = 21;
    private BettingMoney bettingMoney;

    public Gamer(Name name, Cards cards) {
        super(name, cards);
    }

    public Gamer(Name name, Cards cards, BettingMoney bettingMoney) {
        super(name, cards);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public void addCard(Card card) {
        validateGamerCardScore();
        cards.addCard(card);
    }

    private void validateGamerCardScore() {
        if (calculateScore() >= DRAW_MAXIMUM_SCORE) {
            throw new IllegalArgumentException(PLAYER_SCORE_RANGE_ERROR);
        }
    }

    public int getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }

    @Override
    public boolean canDraw() {
        return calculateScore() < DRAW_MAXIMUM_SCORE;
    }

    public int getProfit(Dealer dealer) {
        int profit = (int)state.calculateProfit(bettingMoney);
        System.out.println(state.getClass() + "/" + dealer.state.getClass());
        if (isBlackJack() && dealer.isBlackJack()) {
            return 0;
        }
        if (isBlackJack() && !dealer.isBlackJack()) {
            return profit;
        }
        if (!isBlackJack() && dealer.isBlackJack()) {
            return getBettingMoney() * (-1);
        }
        if (isbust() && !dealer.isBlackJack()) {
            return getBettingMoney() * (-1);
        }
        if (!isbust() && dealer.isbust()) {
            return profit;
        }
        return getProfitIfAllStay(dealer);
    }

    private int getProfitIfAllStay(Dealer dealer) {
        int profit = (int)state.calculateProfit(bettingMoney);
        if (calculateScore() > dealer.calculateScore()) {
            return profit;
        }
        if (calculateScore() < dealer.calculateScore()) {
            return profit * (-1);
        }
        return 0;
    }
}
