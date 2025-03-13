package object.participant;

import java.util.Map;
import object.game.GameResult;

public class Dealer implements Participant {
    public static final int STAY_THRESHOLD = 16;

    private final String nickname;
    private final GameRecord gameRecord;
    private final Wallet wallet;

    private Dealer(final String nickname) {
        this.nickname = nickname;
        this.gameRecord = new GameRecord();
        this.wallet = new Wallet();
    }

    public static Dealer generate() {
        return new Dealer("딜러");
    }

    @Override
    public boolean ableToDraw(final int score) {
        return score <= STAY_THRESHOLD;
    }

    @Override
    public boolean areYouDealer() {
        return true;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void applyGameRecord(GameResult result) {
        gameRecord.add(result);
    }

    @Override
    public Map<GameResult, Integer> getGameRecord() {
        return gameRecord.getGameRecord();
    }

    @Override
    public int getProfit() {
        return wallet.getProfit();
    }

    @Override
    public void bet(int amount) {
        wallet.betMoney(amount);
    }

    public void addEarnedMoney(int amount) {
        wallet.earnMoney(amount);
    }
}
