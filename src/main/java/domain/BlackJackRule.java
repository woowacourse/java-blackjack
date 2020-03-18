package domain;

import domain.card.Card;
import domain.player.User;

public class BlackJackRule {
    public boolean isHit(User user) {
        if (user == null) {
            throw new NullPointerException("플레이어를 입력하지 않았습니다.");
        }

        return user.isHit();
    }

    public void hit(User user, Card card) {
        user.hitCard(card);
    }
}
