package blackjack.fixture;

import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER8;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static blackjack.fixture.CardRepository.CLOVER_KING;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public class CardBundleFixture {

    public static CardBundle cardBundleOf(Card... cards) {
        CardBundle cardBundle = CardBundle.of(cards[0]);
        for (int i = 1; i < cards.length; i++) {
            cardBundle = cardBundle.addAndGenerate(cards[i]);
        }
        return cardBundle;
    }

    public static CardBundle THREE_CARDS_9() {
        return cardBundleOf(CLOVER2, CLOVER3, CLOVER4);
    }

    public static CardBundle CARD_BUNDLE_10() {
        return cardBundleOf(CLOVER4, CLOVER6);
    }

    public static CardBundle CARD_BUNDLE_15() {
        return cardBundleOf(CLOVER5, CLOVER10);
    }

    public static CardBundle CARD_BUNDLE_16() {
        return cardBundleOf(CLOVER6, CLOVER10);
    }

    public static CardBundle CARD_BUNDLE_17() {
        return cardBundleOf(CLOVER7, CLOVER10);
    }

    public static CardBundle CARD_BUNDLE_20() {
        return cardBundleOf(CLOVER10, CLOVER_KING);
    }

    public static CardBundle CARD_BUNDLE_21() {
        return cardBundleOf(CLOVER3, CLOVER8, CLOVER_KING);
    }

    public static CardBundle BLACKJACK_CARD_BUNDLE() {
        return cardBundleOf(CLOVER_ACE, CLOVER_KING);
    }

    public static CardBundle BUST_CARD_BUNDLE() {
        return cardBundleOf(CLOVER8, CLOVER10, CLOVER_KING);
    }
}
