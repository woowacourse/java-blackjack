package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Player {

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    @Override
    void addCard(Card card) {
        if (calculateScore() > 16) {
            throw new IllegalArgumentException("[ERROR] 딜러의 점수가 16을 초과하여 카드를 추가할 수 없습니다.");
        }
        cards.addCard(card);
    }
}
