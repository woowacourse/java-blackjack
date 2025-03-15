package model.cards;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CardTest {

    @DisplayName("주어진 랭크와 무늬를 가진 카드를 생성한다.")
    @Test
    void testCard_String() {
        Card card = new Card("A", "스페이드");

        assertThat(card).isNotNull();
    }

    @DisplayName("랭크와 무늬 값을 가진 카드를 생성한다.")
    @Test
    void testCard_Enum() {
        Card card = new Card(Rank.ACE, Suit.DIAMONDS);

        assertThat(card).isNotNull();
    }

    @DisplayName("카드의 랭크가 ACE일 때 스코어를 반환한다.")
    @Test
    void testCardScore_Ace() {
        Card card = new Card("A", "하트");

        List<Integer> scores = card.getRank();

        assertThat(scores).containsAll(List.of(1, 11));
    }

    @DisplayName("카드의 랭크가 JACK, QUEEN, KING일 때 스코어를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"JACK", "QUEEN", "KING"})
    void testCardScore_Face(Rank rank) {
        Card card = new Card(rank, Suit.CLUBS);

        List<Integer> scores = card.getRank();

        assertThat(scores).isEqualTo(List.of(10));
    }

    @DisplayName("카드의 랭크가 숫자일 때 스코어를 반환한다.")
    @Test
    void testCardScore_Number() {
        Card card = new Card(Rank.SEVEN, Suit.HEARTS);

        List<Integer> scores = card.getRank();

        assertThat(scores).isEqualTo(List.of(7));
    }
}
