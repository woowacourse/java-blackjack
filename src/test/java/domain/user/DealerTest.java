package domain.user;

import card.Card;
import card.CardDeck;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.Dealer;

class DealerTest {
    @DisplayName("딜러는 자신이 가진 하나의 카드만 공개해야 한다")
    @Test
    void test2() {
        // given
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        dealer.drawCard(cardDeck.drawCard());
        dealer.drawCard(cardDeck.drawCard());

        // when
        List<Card> cards = dealer.openInitialCard();

        // then
        Assertions.assertThat(cards).hasSize(1);
    }
}