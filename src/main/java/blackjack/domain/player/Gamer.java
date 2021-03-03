package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Gamer extends Player {

    public Gamer(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    void addCard(Card card) {
        if (calculateScore() >= 21) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 점수가 21을 초과하여 카드를 추가할 수 없습니다.");
        }
        cards.addCard(card);
    }
}
