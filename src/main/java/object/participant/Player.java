package object.participant;

import java.util.Map;
import object.game.GameResult;

public class Player implements Participant {
    private static final int STAY_THRESHOLD = 21;

    private final String nickname;
    private final GameRecord gameRecord;
    private final Wallet wallet;

    private Player(final String nickname) {
        this.nickname = nickname;
        this.gameRecord = new GameRecord();
        this.wallet = new Wallet();
    }

    public static Player from(final String nickname) {
        return new Player(nickname);
    }

    @Override
    public boolean ableToDraw(final int score) {
        return score < STAY_THRESHOLD;
    }

    @Override
    public boolean areYouDealer() {
        return false;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void applyGameRecord(GameResult result) {
        gameRecord.add(result);
        wallet.winBetRate(result);
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
}
