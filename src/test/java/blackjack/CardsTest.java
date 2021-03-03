package blackjack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class CardsTest {
    @Test
    void create(){
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("A하트"));
        assertThatCode(() -> new Cards(cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 초기 리스트 2 초과")
    void create1(){
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("A하트"), Card.from("A스페이드"));
        assertThatThrownBy(() -> new Cards(cards)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void create2(){
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("A하트"));
        assertThat(new Cards(cards).getUnmodifiableList()).contains(Card.from("A다이아몬드"), Card.from("A하트"));
    }

    @Test
    @DisplayName("카드추가")
    void add(){
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("A하트"));
        final Cards cardsValue = new Cards(cards);
        cardsValue.add(Card.from("A스페이드"));
        assertThat(cardsValue.getUnmodifiableList()).contains(Card.from("A스페이드"));
    }
}