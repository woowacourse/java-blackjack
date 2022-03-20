package blackjack.fixture;

import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER8;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static blackjack.fixture.CardRepository.CLOVER_KING;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public class CardBundleFixture {

    public static CardBundle cardBundleOf(Card... cards) {
        CardBundle cardBundle = new CardBundle(cards[0]);
        for (int i = 1; i < cards.length; i++) {
            cardBundle = cardBundle.add(cards[i]);
        }
        return cardBundle;
    }

    public static CardBundle CARD_BUNDLE_10() {
        return cardBundleOf(CLOVER4, CLOVER6);
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
