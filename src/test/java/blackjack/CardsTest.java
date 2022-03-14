package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("9클로버, J하트 점수의 합은 19이다")
    @Test
    void sumCardScore_9clover_Jheat() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        cards.add(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));

        assertThat(cards.sumScore()).isEqualTo(19);
    }

    @DisplayName("9클로버, J하트, A클로버 점수에는 Ace Advantage가 반영되지 않는다")
    @Test
    void sumCardScore_9clover_Jheart_Aclover() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        cards.add(new Card(TrumpNumber.JACK, TrumpSymbol.HEART));
        cards.add(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(cards.sumScore()).isEqualTo(20);
    }

    @DisplayName("9클로버, A클로버 점수에 Ace Advantage가 반영된다")
    @Test
    void sumCardScore_9clover_Aclover() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        cards.add(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(cards.sumScore()).isEqualTo(20);
    }

    @DisplayName("두장의 카드를 가지고 있으면 true를 반환합니다.")
    @Test
    void hasTwoCard_true() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        cards.add(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(cards.hasTwoCard()).isTrue();
    }

    @DisplayName("카드를 두장보다 많이 가지고 있으면 false를 반환합니다.")
    @Test
    void hasTwoCard_false() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        cards.add(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));

        assertThat(cards.hasTwoCard()).isFalse();
    }
}
