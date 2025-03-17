package participant;

import card.Card;
import game.Playable;
import java.util.List;

public class Player implements Playable {

    private static final int BLACKJACK_NUMBER = 21;

    private final Participant participant;
    private final Wallet wallet;

    public Player(String nickname, int bettingMoney) {
        this.participant = new Participant(nickname);
        this.wallet = Wallet.of(bettingMoney);
    }

    @Override
    public void receiveCard(Card card) {
        participant.receiveCard(card);
    }

    @Override
    public boolean canReceiveCard() {
        return score() < BLACKJACK_NUMBER;
    }

    @Override
    public boolean isBlackjack() {
        return participant.isBlackjack();
    }

    @Override
    public boolean isBust() {
        return participant.isBust();
    }

    @Override
    public int score() {
        return participant.score();
    }

    @Override
    public String getNickname() {
        return participant.getNickname();
    }

    @Override
    public List<Card> getCards() {
        return participant.getCards();
    }

    public void updateMoney(int money, boolean isProfitable) {
        if (isProfitable) {
            earnMoney(money);
            return;
        }
        loseMoney(money);
    }

    private void earnMoney(int money) {
        wallet.addMoney(money);
    }

    private void loseMoney(int money) {
        wallet.subtractMoney(money);
    }

    public Profit calculateProfit() {
        return wallet.getProfit();
    }

    public int getBettingMoney() {
        return wallet.getBettingMoney();
    }

    public int getEarnedMoney() {
        return wallet.getEarnedMoney();
    }
}
