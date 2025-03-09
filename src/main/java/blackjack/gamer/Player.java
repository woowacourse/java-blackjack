package blackjack.gamer;

import blackjack.domain.card.Cards;

public class Player extends GameParticipant {

    private final Nickname nickname;

    public Player(Cards cards, Nickname nickname) {
        super(cards);
        this.nickname = nickname;
    }

    public static Player from(Nickname nickname) {
        return new Player(Cards.empty(), nickname);
    }

    @Override
    public boolean shouldHit() {
        // TODO 뷰와 관련있음
        return false;
    }

    public Nickname getNickname() {
        return nickname;
    }
}
