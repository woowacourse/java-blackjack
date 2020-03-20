package blackjack.domain.card;

import blackjack.domain.card.component.Figure;
import blackjack.domain.card.component.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CardsTest {

    @DisplayName("카드 합 계산: ACE를 포함하지 않는 경우")
    @Test
    void test1() {
        Cards cards = new Cards();
        cards.add(Card.of(Type.THREE, Figure.CLOVER));
        cards.add(Card.of(Type.TWO, Figure.CLOVER));

        int actualScore = cards.computeScore();

        assertThat(actualScore).isEqualTo(5);
    }

    @DisplayName("카드 합 계산: ACE가 있는 경우")
    @Test
    void test2() {
        Cards cards = new Cards();
        cards.add(Card.of(Type.THREE, Figure.CLOVER));
        cards.add(Card.of(Type.ACE, Figure.CLOVER));
        // 카드 합이 21을 초과하지 않는 경우
        assertThat(cards.computeScore()).isEqualTo(14);

        // 카드 합이 21을 초과하는 경우
        cards.add(Card.of(Type.TEN, Figure.CLOVER));
        assertThat(cards.computeScore()).isEqualTo(14);

        // 카드 합이 21을 초과하고 ACE가 여러 개 일 때
        cards.add(Card.of(Type.ACE, Figure.HEART));
        assertThat(cards.computeScore()).isEqualTo(15);
    }

    @DisplayName("카드들 정보 확인")
    @Test
    void test3() {
        Cards cards = new Cards();
        cards.add(Card.of(Type.ACE, Figure.SPADE));
        cards.add(Card.of(Type.FIVE, Figure.DIAMOND));

        List<String> expectedInfo = Arrays.asList("A스페이드", "5다이아몬드");

        List<String> actualCardInfo = cards.cardsInfo();

        assertThat(actualCardInfo).isEqualTo(expectedInfo);
    }
}
