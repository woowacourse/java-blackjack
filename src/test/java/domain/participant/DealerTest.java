package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("16점에서 카드받기 시도하면 카드를 받는다")
    void tryReceive_WhenScoreIsSixteen() {
        Dealer dealer = Dealer.withNoCards();
        Card newCard = Card.of(Rank.QUEEN, Symbol.DIAMOND);
        dealer.tryReceive(List.of(
            Card.of(Rank.KING, Symbol.CLUB),
            Card.of(Rank.SIX, Symbol.SPADE)
        ));

        dealer.tryReceive(newCard);

        assertThat(dealer.getCards()).contains(newCard);
    }

    @Test
    @DisplayName("17점에서 카드받기 시도해도 카드를 못 받는다")
    void tryReceive_WhenScoreIsSeventeen() {
        Dealer dealer = Dealer.withNoCards();
        Card newCard = Card.of(Rank.QUEEN, Symbol.DIAMOND);
        dealer.tryReceive(List.of(
            Card.of(Rank.KING, Symbol.CLUB),
            Card.of(Rank.SEVEN, Symbol.HEART)
        ));

        dealer.tryReceive(newCard);

        assertThat(dealer.getCards()).doesNotContain(newCard);
    }

    @Test
    @DisplayName("총점이 22점이면 버스트이다.")
    void isBust_True() {
        Dealer dealer = Dealer.withNoCards();
        dealer.tryReceive(List.of(
            Card.of(Rank.KING, Symbol.CLUB),
            Card.of(Rank.QUEEN, Symbol.CLUB),
            Card.of(Rank.TWO, Symbol.CLUB)
        ));

        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("총점이 21점이면 버스트가 아니다.")
    void isBust_False() {
        Dealer dealer = Dealer.withNoCards();
        dealer.tryReceive(List.of(
            Card.of(Rank.KING, Symbol.CLUB),
            Card.of(Rank.NINE, Symbol.CLUB),
            Card.of(Rank.TWO, Symbol.CLUB)
        ));

        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    @DisplayName("K, 9, 2 -> 총점 21점")
    void score() {
        Dealer dealer = Dealer.withNoCards();
        dealer.tryReceive(List.of(
            Card.of(Rank.KING, Symbol.CLUB),
            Card.of(Rank.NINE, Symbol.CLUB),
            Card.of(Rank.TWO, Symbol.CLUB)
        ));

        assertThat(dealer.score().toInt()).isEqualTo(21);
    }

    @Test
    @DisplayName("첫번째 카드 받아올 수 있다.")
    void firstCard() {
        Dealer dealer = Dealer.withNoCards();
        Card card1 = Card.of(Rank.KING, Symbol.CLUB);
        Card card2 = Card.of(Rank.NINE, Symbol.CLUB);
        dealer.tryReceive(List.of(card1, card2));

        assertThat(dealer.firstCard()).isEqualTo(card1);
    }

    @Test
    @DisplayName("16점이면 카드 받을 수 있다.")
    void isReceivable_True() {
        Dealer dealer = Dealer.withNoCards();
        dealer.tryReceive(List.of(
            Card.of(Rank.KING, Symbol.CLUB),
            Card.of(Rank.SIX, Symbol.CLUB)
        ));

        assertThat(dealer.isReceivable()).isTrue();
    }

    @Test
    @DisplayName("17점이면 카드 받을 수 없다.")
    void isReceivable_False() {
        Dealer dealer = Dealer.withNoCards();
        dealer.tryReceive(List.of(
            Card.of(Rank.KING, Symbol.CLUB),
            Card.of(Rank.SEVEN, Symbol.CLUB)
        ));

        assertThat(dealer.isReceivable()).isFalse();
    }

}
