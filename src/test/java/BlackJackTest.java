import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackTest {
    @DisplayName("7클로버를 뽑으면 7을 반환한다.")
    @Test
    void test() {
        //given
        String cardValue = "7클로버";
        Deck deck = new Deck();
        int expected = 7;

        //when
        int result = deck.pick(cardValue);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("K스페이드를 뽑으면 10을 반환한다.")
    @Test
    void test1() {
        //given
        String cardValue = "K스페이드";
        Deck deck = new Deck();
        int expected = 10;

        //when
        int result = deck.pick(cardValue);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("2하트를 뽑으면 2를 반환한다.")
    @Test
    void test2() {
        //given
        String cardValue = "2하트";
        Deck deck = new Deck();
        int expected = 2;

        //when
        int result = deck.pick(cardValue);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("7클로버 카드를 뽑으면 Deck에서 7클로버가 제거된다.")
    @Test
    void test3() {
        //given
        String cardValue = "7클로버";
        Deck deck = new Deck();

        //when
        deck.pick(cardValue);

        //then
        assertThatThrownBy(()-> deck.findValueByKey(cardValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
