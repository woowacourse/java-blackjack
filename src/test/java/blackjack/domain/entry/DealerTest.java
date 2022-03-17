package blackjack.domain.entry;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.EIGHT;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(Card.valueOf(SPADE, KING), Card.valueOf(SPADE, ACE)));

        assertThat(dealer.countCards()).isEqualTo(21);
    }

    @Test
    @DisplayName("보유한 카드의 합이 16이하이면 True 반환한다.")
    void shouldHaveMoreCard() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(Card.valueOf(SPADE, EIGHT), Card.valueOf(HEART, EIGHT)));

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("보유한 카드의 합이 17이상일 경우 False 반환한다.")
    void shouldNotHaveMoreCard() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(Card.valueOf(SPADE, NINE), Card.valueOf(HEART, EIGHT)));

        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("딜러는 한장의 카드를 오픈한다.")
    void openCardByDealer() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(Card.valueOf(SPADE, NINE), Card.valueOf(HEART, EIGHT)));

        assertThat(dealer.openCard()).hasSize(1);
    }

    @Test
    @DisplayName("딜러의 이름은 딜러이다.")
    void getDealerName() {
        Dealer dealer = new Dealer(HoldCards.initTwoCards(Card.valueOf(SPADE, NINE), Card.valueOf(HEART, EIGHT)));

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

}
