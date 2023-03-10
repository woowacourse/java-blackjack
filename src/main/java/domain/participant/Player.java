package domain.participant;

import domain.card.Card;

public final class Player extends Participant {

    private Player(final String name) {
        super(name);
    }

    public static Player create(final String name) {
        validatePlayerName(name);
        return new Player(name);
    }

    private static void validatePlayerName(final String name) {
        if (DEALER_NAME.isSame(name)) {
            throw new IllegalArgumentException("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
        }
    }

    @Override
    public Card getFirstCard() {
        throw new UnsupportedOperationException("딜러만 카드 한 장을 보여줄 수 있습니다.");
    }

    @Override
    public boolean canGiveCard() {
        throw new UnsupportedOperationException("딜러만 16점 이하일 때 카드를 추가로 받을 수 있습니다.");
    }
}
