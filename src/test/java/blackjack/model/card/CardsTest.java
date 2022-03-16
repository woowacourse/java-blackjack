package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("Cards가 정상적으로 생성되는지 확인한다.")
    @Test
    void construct(){
        Cards cards = new Cards();

        assertThat(cards).isInstanceOf(Cards.class);
    }

    @DisplayName("Cards에 Card를 추가하면 새로운 Cards 인스턴스를 반환한다.")
    @Test
    void add_new_Cards() {
        Cards cards = new Cards();
        Cards otherCards = cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));

        assertThat(cards).isNotEqualTo(otherCards);
    }

    @DisplayName("9클로버, J하트를 가지고 있으면 19점 이다.")
    @Test
    void isBust_9clover_Jheat() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.SPADE));
        cards.add(new Card(TrumpNumber.JACK, TrumpSymbol.DIAMOND));

        assertThat(cards.sumScore()).isEqualTo(19);
    }

    @DisplayName("9클로버, J하트, A클로버를 가지고 있으면 Ace Advantage가 반영 되지 않는다..")
    @Test
    void isBust_9clover_Jheart_Aclover() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        cards.add(new Card(TrumpNumber.JACK, TrumpSymbol.HEART));
        cards.add(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(cards.sumScore()).isEqualTo(20);
    }

    @DisplayName("9클로버, A클로버를 가지고 있으면 점수에 Ace Advantage가 반영된다")
    @Test
    void sumCardScore_9clover_Aclover() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.HEART));
        cards.add(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(cards.sumScore()).isEqualTo(20);
    }

    @DisplayName("카드를 두장 가지고 있으면 true를 반환한다.")
    @Test
    void hasOnlyStartCards_true() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.HEART));
        cards.add(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(cards.hasOnlyStartCards()).isTrue();
    }

    @DisplayName("카드를 두장이상 가지고 있으면 false를 반환한다.")
    @Test
    void hasOnlyStartCards_false() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.HEART));
        cards.add(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));
        cards.add(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));

        assertThat(cards.hasOnlyStartCards()).isFalse();
    }
}
