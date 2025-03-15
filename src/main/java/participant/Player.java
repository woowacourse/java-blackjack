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

    public void updateMoney(double rate) {
        wallet.updateMoney(rate);
    }

    public double calculateProfit() {
        return wallet.calculateProfit();
    }

    public double getEarnedMoney() {
        return wallet.getEarnedMoney();
    }
}
