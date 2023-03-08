package blackjack.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class Player extends Participant {
    private static final List<String> BLACKLIST = List.of("딜러");
    private static final int PLAYER_START_SHOW_COUNT = 2;
    private static final int MAX_SCORE = 21;

    private final Bet bet;

    public Player(String name, List<Card> cards, Bet bet) {
        super(name, cards);
        validateBlacklist(name);
        this.bet = bet;
    }

    private void validateBlacklist(String name) {
        if (BLACKLIST.contains(name)) {
            throw new IllegalArgumentException("[ERROR] " + name + "는 사용할 수 없는 이름입니다.");
        }
    }

    public GameResult matchGame(Dealer dealer) {
        Score dealerScore = dealer.getScore();
        Score myScore = this.getScore();
        return getGameResult(dealerScore, myScore);
    }

    private GameResult getGameResult(Score dealerScore, Score myScore) {
        if (isBlackjack(myScore, dealerScore)) {
            return GameResult.BLACKJACK;
        }
        if (dealerScore.isWinTo(myScore)) {
            return GameResult.LOSE;
        }
        if (myScore.isWinTo(dealerScore)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public Bet matchGameWithBet(Dealer dealer) {
        GameResult gameResult = matchGame(dealer);
        return bet.calculateResult(gameResult);
    }

    private boolean isBlackjack(Score myScore, Score dealerScore) {
        return myScore.isBlackjack() && !dealerScore.isBlackjack();
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
        return getScore().getScore() < MAX_SCORE;
    }
}
