package blackjack.domain.participant;

import blackjack.domain.judgement.GameResult;
import blackjack.domain.card.Hand;
import blackjack.domain.judgement.Status;

public class Player extends Participant {

    private final Nickname nickname;

    public Player(final Hand hand, final Status status, final String nickname) {
        super(hand, status);
        this.nickname = Nickname.from(nickname);
    }

    public GameResult calculateGameResult(Dealer dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (dealer.isBlackjack()) {
            return GameResult.LOSE;
        }
        return calculateScoreResult(dealer);
    }

    private GameResult calculateScoreResult(Dealer dealer) {
        if (isBurst()) {
            return GameResult.LOSE;
        }
        if (dealer.isBurst() || dealer.getScore() < getScore()) {
            return GameResult.WIN;
        }
        if (dealer.getScore() == getScore()) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }

    @Override
    public Nickname getNickname() {
        return nickname;
    }
}
