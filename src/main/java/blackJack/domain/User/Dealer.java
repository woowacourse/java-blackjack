package blackJack.domain.User;

public class Dealer extends User {

    public static final int INIT_BETTING_MONEY = 0;
    private static final int DEALER_ADD_CARD_LIMIT = 16;

    public Dealer() {
        super("딜러");
        bettingMoney = new BettingMoney(INIT_BETTING_MONEY, true);
    }

    public boolean isPossibleToAdd() {
        return this.getScore() < DEALER_ADD_CARD_LIMIT;
    }

    public String getFirstCard() {
        return cards.getFirst();
    }
}
