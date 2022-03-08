package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러는 생성될 때 두 장의 카드를 받는다.")
    void startWithDraw() {
        // given
        String name = "Dealer";
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        // when
        Dealer dealer = new Dealer(name, cards);

        // then
        assertThat(dealer.getCards()).containsOnly(card1, card2);
    }

    @Test
    @DisplayName("딜러를 생성할 때 카드는 null일 수 없다.")
    void cardsNotNull() {
        // given
        String name = "Dealer";

        // then
        assertThatThrownBy(() -> new Dealer(name, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("딜러를 생성할 때 카드가 두 장이 아니면 예외가 발생한다.")
    void cardsSizeNotTwo() {
        // given
        String name = "Dealer";
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        Card card3 = new Card(Pattern.HEART, Denomination.THREE);
        List<Card> cards = List.of(card1, card2, card3);

        // then
        assertThatThrownBy(() -> new Dealer(name, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드를 두 장 받고 시작해야 합니다.");
    }

    @Test
    @DisplayName("딜러를 생성할 때 카드가 중복되면 예외가 발생한다.")
    void duplicatedCards() {
        String name = "Dealer";
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        List<Card> cards = List.of(card1, card1);

        // then
        assertThatThrownBy(() -> new Dealer(name, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 카드는 중복될 수 없습니다.");
    }
}
