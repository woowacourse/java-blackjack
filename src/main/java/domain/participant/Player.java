package domain.participant;

import domain.card.CardHand;
import domain.game.GameResult;

public class Player extends GameParticipant {
    public Player(String name, CardHand cardHand) {
        super(name, cardHand);
        validatePlayerName(name);
    }

    public GameResult calculateGameResult(Dealer dealer) {
        return GameResult.calculatePlayerGameResult(dealer, this);
    }

    private void validatePlayerName(String playerName) {
        if (playerName.equals("딜러")) {
            throw new IllegalArgumentException("[ERROR] '딜러'는 플레이어 이름으로 사용할 수 없습니다.");
        }
    }
}
