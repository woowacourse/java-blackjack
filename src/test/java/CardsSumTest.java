import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CardsSumTest {

    @Test
    void cards_sum_without_ace() {
        Cards cards = new Cards(List.of(
                new Card("2", "하트"),
                new Card("3", "스페이드"),
                new Card("J", "다이아몬드"),
                new Card("Q", "클로버")));
        assertThat(cards.sumScore()).isEqualTo(25);
    }

    @Test
    void cards_sum_with_ace() {
        Cards cards1 = new Cards(List.of(
                new Card("2", "하트"),
                new Card("3", "스페이드"),
                new Card("A", "다이아몬드"),
                new Card("J", "클로버"),
                new Card("Q", "하트")));
        Cards cards2 = new Cards(List.of(
                new Card("2", "하트"),
                new Card("3", "스페이드"),
                new Card("A", "다이아몬드")));
        Cards cards3 = new Cards(List.of(
                new Card("2", "하트"),
                new Card("3", "스페이드"),
                new Card("A", "다이아몬드"),
                new Card("A", "클로버")));
        assertThat(cards1.sumScore()).isEqualTo(26);
        assertThat(cards2.sumScore()).isEqualTo(16);
        assertThat(cards3.sumScore()).isEqualTo(17);
    }
}
