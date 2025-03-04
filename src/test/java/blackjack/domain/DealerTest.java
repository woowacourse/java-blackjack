package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드의 합이 16 이하이면 카드를 한장 더 받아야한다.")
    @Test
    void testDealerGenerate() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardGenerator cardGenerator = new CardGenerator();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(cardDeck, cardGenerator);

        // when
        boolean takenExtraCard = dealer.hasTakenExtraCard();

        assertThat(takenExtraCard).isTrue();
    }

    @DisplayName("딜러는 카드의 합이 17 이상이면 카드를 더 받지 않는다.")
    @Test
    void testDealerGenerate2() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardGenerator cardGenerator = new CardGenerator();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(cardDeck, cardGenerator);

        // when
        boolean takenExtraCard = dealer.hasTakenExtraCard();

        assertThat(takenExtraCard).isFalse();
    }
}
