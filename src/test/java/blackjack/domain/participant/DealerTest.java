package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import blackjack.util.BlackjackTestUtil;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러는 처음에 맨 앞 한 장만 보여준다.")
    void showOnlyOneCard() {
        // given
        Card card1 = Card.of(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = Card.of(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        Dealer dealer = new Dealer(cards);

        // when
        List<Card> actual = dealer.showInitialCards();

        // then
        assertThat(actual).containsOnly(card1);
    }

    @Test
    @DisplayName("딜러가 카드 한 장을 더 받는 경우")
    void addCard() {
        // given
        CardDeck deck = new CardDeck(List.of(Card.of(Pattern.HEART, Denomination.THREE)));
        Dealer dealer = BlackjackTestUtil.createDealer(16);

        // when
        dealer.hit(deck);

        // then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러의 카드의 총합이 16 이하면 힛 해야 한다.")
    void hittable() {
        // given
        Dealer dealer = BlackjackTestUtil.createDealer(16);

        // when
        boolean actual = dealer.shouldHit();

        // then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러의 카드의 총합이 17 이상이면 힛 할 수 없다.")
    void notHittable() {
        // given
        Dealer dealer = BlackjackTestUtil.createDealer(17);

        // when
        boolean actual = dealer.shouldHit();

        // then
        assertThat(actual).isEqualTo(false);
    }
}
