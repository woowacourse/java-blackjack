package blackjack.domain.user;

import blackjack.domain.card.Score;
import blackjack.domain.user.exceptions.PlayerException;

public class DefaultPlayer extends AbstractPlayer {
    private DefaultPlayer(String name) {
        super(name);
        validateNameIsDifferentFromDealer(name);
    }

    private void validateNameIsDifferentFromDealer(String name) {
        if (name.equals(Dealer.NAME)) {
            throw new PlayerException("플레이어의 이름은 " + Dealer.NAME + "일 수 없습니다.");
        }
    }

    public static DefaultPlayer of(String name) {
        return new DefaultPlayer(name);
    }

    @Override
    public Boolean isWinner(Score dealerScore) {
        if (isBust()) {
            return false;
        }
        if (dealerScore.isOver(21)) {
            return true;
        }
        return calculateScore().isOver(dealerScore);
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
