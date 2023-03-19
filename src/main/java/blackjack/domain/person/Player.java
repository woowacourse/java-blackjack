package blackjack.domain.person;

import blackjack.domain.blackjackGame.GameResult;
import blackjack.domain.card.Card;

import java.util.List;

public class Player extends Person {

    private static final List<String> BLACKLIST = List.of("딜러");

    public Player(Name name) {
        super(name);
        validateBlacklist(name);
    }

    private void validateBlacklist(Name name) {
        if (BLACKLIST.contains(name.getName())) {
            throw new IllegalArgumentException("[ERROR] " + name.getName() + "는 사용할 수 없는 이름입니다.");
        }
    }

    public GameResult matchGame(Person dealer) {
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (dealer.isBlackjack() && this.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (isBust()) {
            return GameResult.LOSE;
        }
        return compareScoreWith(dealer);
    }

    private GameResult compareScoreWith(Person dealer) {
        int myScore = this.getScore();
        int dealerScore = dealer.getScore();
        if (myScore < dealerScore) {
            return GameResult.LOSE;
        }
        if (myScore > dealerScore) {
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
    public List<Card> getInitCards() {
        return getCards();
    }

    @Override
    public boolean canDrawCard() {
        return getScore() < MAX_SCORE;
    }
}
