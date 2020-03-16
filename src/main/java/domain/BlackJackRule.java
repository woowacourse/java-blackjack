package domain;

import domain.card.Card;
import domain.player.Player;
import domain.player.User;

public class BlackJackRule {
    private static final int BLACK_JACK = 21;

    public boolean isUserCardSumOverBlackJack(User user) {
        if (user == null) {
            throw new NullPointerException("유저를 입력하지 않았습니다.");
        }

        return user.sumCardNumber() >= BLACK_JACK;
    }

    public boolean isHit(User user, Answer answer) {
        if (user == null || answer == null) {
            throw new NullPointerException("유저 또는 카드 선택 여부를 입력하지 않았습니다.");
        }

        if (isUserCardSumOverBlackJack(user)) {
            return false;
        }
        return answer.isYes();
    }

    public void hit(Player player, Card card) {
        player.hitCard(card);
    }
}
