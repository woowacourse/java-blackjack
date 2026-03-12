package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.List;

public class FixedCardShuffler implements CardShuffler {

    @Override
    public void shuffle(final List<Card> cards) {
        // 셔플하지 않아 카드 순서 고정
    }
}
