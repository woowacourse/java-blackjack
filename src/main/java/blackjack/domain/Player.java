package blackjack.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class Player extends Participant {
    private static final List<String> BLACKLIST = List.of("딜러");
    private static final int BUST_SCORE = -1;
    private static final int PLAYER_START_SHOW_COUNT = 2;

    public Player(String name, List<Card> cards) {
        super(name, cards);
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
        return getCards().stream()
                .limit(PLAYER_START_SHOW_COUNT)
                .collect(toList());
    }

    @Override
    public boolean canDrawCard() {
        return getScore() < MAX_SCORE;
    }
}
