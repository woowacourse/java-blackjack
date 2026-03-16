package blackjack.domain.deck;


import blackjack.domain.card.Card;
import java.util.List;

public class NoShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(List<Card> cards) {
        // 아무것도 하지 않음 — 입력 순서 그대로 유지
    }
}
