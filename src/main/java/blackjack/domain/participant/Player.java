package blackjack.domain.participant;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Bet;
import blackjack.domain.card.Card;
import blackjack.domain.GameResult;
import blackjack.domain.Score;
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

    public Bet matchGameWithBet(Dealer dealer) {
        GameResult gameResult = matchGame(dealer);
        return bet.calculateResult(gameResult);
    }

    private GameResult matchGame(Dealer dealer) {
        Score dealerScore = dealer.getScore();
        Score myScore = this.getScore();
        if (this.isBlackjack() && !dealer.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        return getGameResult(dealerScore, myScore);
    }

    private GameResult getGameResult(Score dealerScore, Score myScore) {
        if (dealerScore.isWinTo(myScore)) {
            return GameResult.LOSE;
        }
        if (myScore.isWinTo(dealerScore)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
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
