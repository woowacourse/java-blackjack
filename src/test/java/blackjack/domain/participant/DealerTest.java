package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러는 생성될 때 두 장의 카드를 받는다.")
    void startWithDraw() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        // when
        Dealer dealer = new Dealer(cards);

        // then
        assertThat(dealer.getCards()).containsOnly(card1, card2);
    }

    @Test
    @DisplayName("딜러를 생성할 때 카드는 null일 수 없다.")
    void cardsNotNull() {
        // then
        assertThatThrownBy(() -> new Dealer(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("딜러를 생성할 때 카드가 두 장이 아니면 예외가 발생한다.")
    void cardsSizeNotTwo() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Card card3 = new Card(Pattern.HEART, Denomination.THREE);
        List<Card> cards = List.of(card1, card2, card3);

        // then
        assertThatThrownBy(() -> new Dealer(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드를 두 장 받고 시작해야 합니다.");
    }

    @Test
    @DisplayName("딜러를 생성할 때 카드가 중복되면 예외가 발생한다.")
    void duplicatedCards() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        List<Card> cards = List.of(card1, card1);

        // then
        assertThatThrownBy(() -> new Dealer(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("딜러가 카드 한 장을 더 받는 경우")
    void addCard() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        CardDeck deck = new CardDeck(() -> new ArrayList<>(List.of(new Card(Pattern.HEART, Denomination.THREE))));
        Dealer dealer = new Dealer(cards);

        // when
        dealer.hit(deck);

        // then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러의 카드의 총합이 16 이하면 hit이 가능하다.")
    void hittable() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.SIX);
        List<Card> cards = List.of(card1, card2);

        Dealer dealer = new Dealer(cards);

        // when
        boolean actual = dealer.isHittable();

        // then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러의 카드의 총합이 17 이상이면 hit이 불가능하다.")
    void notHittable() {

        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.SEVEN);
        List<Card> cards = List.of(card1, card2);

        Dealer dealer = new Dealer(cards);

        // when
        boolean actual = dealer.isHittable();

        // then
        assertThat(actual).isEqualTo(false);
    }
}
