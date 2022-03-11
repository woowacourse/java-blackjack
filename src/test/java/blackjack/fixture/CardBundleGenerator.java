package blackjack.fixture;

import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static blackjack.fixture.CardRepository.CLOVER_KING;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public class CardBundleGenerator {

    public static CardBundle generateCardBundleOf(Card... cards) {
        if (cards.length < 2) {
            throw new IllegalArgumentException("최소 두 장의 카드를 입력하여 테스트해야 합니다.");
        }
        CardBundle cardBundle = CardBundle.of(cards[0], cards[1]);
        for (int i = 2; i < cards.length; i++) {
            cardBundle.add(cards[i]);
        }
        return cardBundle;
    }

    public static CardBundle ofBlackjack() {
        return CardBundle.of(CLOVER_ACE, CLOVER_KING);
    }
}
