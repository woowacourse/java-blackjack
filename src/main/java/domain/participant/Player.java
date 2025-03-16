package domain.participant;

import domain.card.CardHand;

public class Player extends GameParticipant {
    public Player(String name, CardHand cardHand) {
        super(name, cardHand);
        validatePlayerName(name);
    }

    private void validatePlayerName(String playerName) {
        if (playerName.equals(Dealer.NAME)) {
            throw new IllegalArgumentException(String.format("[ERROR] '%s'는 플레이어 이름으로 사용할 수 없습니다.", Dealer.NAME));
        }
    }

    @Override
    public boolean canHit() {
        return !isBlackJack() && !isBust();
    }
}
