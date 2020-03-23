package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends User {

    private static final String DEALER = "딜러";
    public static final int THRESHOLD = 16;
    private static final int CARD_SHOW_LIMIT = 1;

    public Dealer() {
        super(DEALER);
    }

    @Override
    public List<Card> getInitialCards() {
        return this.cards.subList(0, CARD_SHOW_LIMIT);
    }

    //TODO: 딜러는 16이하, 플레이어는 21이하 구현 합치기
    public boolean isUnderThreshold() {
        return (this.getTotalScore() <= THRESHOLD && !this.isBusted());
    }
}
