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
    void sumCardScore_9clover_Jheart() {
        Card card1 = new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER);
        Card card2 = new Card(TrumpNumber.JACK, TrumpSymbol.HEART);
        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);

        assertThat(cards.sumScore()).isEqualTo(19);
    }

    @DisplayName("9클로버, J하트, A클로버 점수에는 Ace Advantage가 반영되지 않는다")
    @Test
    void sumCardScore_9clover_Jheart_Aclover() {
        Card card1 = new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER);
        Card card2 = new Card(TrumpNumber.JACK, TrumpSymbol.HEART);
        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);

        cards.add(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(cards.sumScore()).isEqualTo(20);
    }

    @DisplayName("9클로버, A클로버 점수에 Ace Advantage가 반영된다")
    @Test
    void sumCardScore_9clover_Aclover() {
        Card card1 = new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER);
        Card card2 = new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER);
        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);

        assertThat(cards.sumScore()).isEqualTo(20);
    }

    @DisplayName("5하트, 6클로버, A클로버 점수에 Ace Advantage가 반영되지 않는다")
    @Test
    void sumCardScore_5heart_6clover_Aclover() {
        Cards cards = new Cards();
        cards.add(new Card(TrumpNumber.FIVE, TrumpSymbol.HEART));
        cards.add(new Card(TrumpNumber.SIX, TrumpSymbol.CLOVER));
        cards.add(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(cards.sumScore()).isEqualTo(12);
    }
}
