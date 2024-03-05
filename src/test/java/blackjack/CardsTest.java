package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("카드 목록을 포함한 일급 컬렉션을 생성 한다.")
    public void Cards_Instance_create_with_CardList() {
        List<Card> cards = List.of(Card.EIGHT, Card.FOUR);

        assertThatCode(() -> new Cards(cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드 목록의 숫자 합을 계산 한다. (케이스 1)")
    public void Cards_Sum_of_cards_case1() {
        List<Card> cards = List.of(Card.TWO, Card.FIVE);
        var sut = new Cards(cards);

        var sum = sut.sum();
        assertThat(sum).isEqualTo(7);
    }

    @Test
    @DisplayName("카드 목록의 숫자 합을 계산 한다. (케이스 2)")
    public void Cards_Sum_of_cards_case2() {
        List<Card> cards2 = List.of(Card.FIVE, Card.EIGHT);
        var sut2 = new Cards(cards2);

        var sum2 = sut2.sum();
        assertThat(sum2).isEqualTo(13);
    }
}
