package blackjack.domain;

import java.util.List;

public class Player extends Person {
    private static final List<String> BLACKLIST = List.of("딜러");
    private static final int BUST_SCORE = -1;

    public Player(String name) {
        super(name);
        validateBlacklist(name);
    }

    private void validateBlacklist(String name) {
        if (BLACKLIST.contains(name)) {
            throw new IllegalArgumentException("[ERROR] " + name + "는 사용할 수 없는 이름입니다.");
        }
    }

    public GameResult matchGame(Dealer dealer) {
        int dealerScore = correctionOverScore(dealer.getScore());
        int myScore = correctionOverScore(this.getScore());
        if (myScore <= BUST_SCORE || dealerScore > myScore) {
            return GameResult.LOSE;
        }
        if (myScore > dealerScore) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private int correctionOverScore(int score) {
        if (score > MAX_SCORE) {
            return BUST_SCORE;
        }
        return score;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public List<Card> getStartCards() {
        return getCards();
    }

    @Override
    public boolean canDrawCard() {
        return getScore() < MAX_SCORE;
    }
}
