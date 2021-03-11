package blackjack;

import blackjack.domain.Card;
import blackjack.domain.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandTest {
    @Test
    @DisplayName("Hand 생성")
    void create1() {
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("A하트"));
        assertThatCode(() -> new Hand(cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 초기 리스트 2 초과")
    void create2() {
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("A하트"), Card.from("A스페이드"));
        assertThatThrownBy(() -> new Hand(cards)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Hand의 카드 구성 확인")
    void create3() {
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("A하트"));
        assertThat(new Hand(cards).getUnmodifiableList()).contains(Card.from("A다이아몬드"), Card.from("A하트"));
    }

    @Test
    @DisplayName("카드추가")
    void add() {
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("A하트"));
        final Hand handValue = new Hand(cards);
        handValue.add(Card.from("A스페이드"));
        assertThat(handValue.getUnmodifiableList()).contains(Card.from("A스페이드"));
    }
}
