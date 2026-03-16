package blackjack.domain.deck.shuffler;

import blackjack.domain.card.Card;
import blackjack.domain.deck.CardShuffler;
import java.util.List;

public class FixedCardShuffler implements CardShuffler {

    @Override
    public void shuffle(final List<Card> cards) {
        // 셔플하지 않아 카드 순서 고정
    }
}
