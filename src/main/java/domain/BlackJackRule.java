package domain;

import domain.card.Card;
import domain.player.Player;
import domain.player.User;

public class BlackJackRule {
    private static final int BLACK_JACK = 21;

    private boolean isUserCardSumOverBlackJack(Player player) {
        if (player == null) {
            throw new NullPointerException("유저를 입력하지 않았습니다.");
        }

        return player.sumCardNumber() >= BLACK_JACK;
    }

    public boolean isHit(Player player) {
        if (player == null) {
            throw new NullPointerException("유저 또는 카드 선택 여부를 입력하지 않았습니다.");
        }

       return !isUserCardSumOverBlackJack(player);
    }

    public void hit(User user, Card card) {
        user.hitCard(card);
    }
}
