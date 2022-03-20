package blackJack.domain.Card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @Test
    @DisplayName("에이스가 포함된 경우의 점수를 계산한다. - 에이스가 11로 계산")
    void calculateScoreWhenAceIs11Test() {
        Cards cards = new Cards(Arrays.asList(new Card(Suit.CLOVER, Denomination.TWO), new Card(Suit.CLOVER, Denomination.ACE)));
        int actual = cards.calculateScore();
        int expected = 13;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("에이스가 포함된 경우의 점수를 계산한다. - 에이스가 1로 계산")
    void calculateScoreWhenAceIs1Test() {
        Cards cards = new Cards(Arrays.asList(new Card(Suit.CLOVER, Denomination.JACK), new Card(Suit.HEART, Denomination.JACK), new Card(Suit.CLOVER, Denomination.ACE)));
        int actual = cards.calculateScore();
        int expected = 21;
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("에이스가 포함되지 않은 경우의 점수를 계산한다.")
    void calculateScore() {
        Cards cards = new Cards(Arrays.asList(new Card(Suit.CLOVER, Denomination.TWO), new Card(Suit.HEART, Denomination.JACK)));
        int actual = cards.calculateScore();
        int expected = 12;
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("인자로 들어온 카드를 사용자가 소유한 카드목록에 추가한다.")
    void add() {
        Cards cards = new Cards(Arrays.asList(new Card(Suit.CLOVER, Denomination.TWO)));
        cards.add(new Card(Suit.CLOVER,Denomination.ACE));
        int expected = 2;
        assertThat(cards.getCards().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자가 소유한 카드목록 내의 카드가 2개인지(추가로 받은 카드가 없는지) 확인 후 2개라면 true 를 반환한다.")
    void isOnlyTwoCards_True() {
        Cards cards = new Cards(Arrays.asList(new Card(Suit.CLOVER, Denomination.TWO), new Card(Suit.HEART, Denomination.JACK)));
        boolean actual = cards.isOnlyTwoCards();
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자가 소유한 카드목록 내의 카드가 2개인지(추가로 받은 카드가 있는지) 확인 후 2개가 아니라면 false 를 반환한다.")
    void isOnlyTwoCards_False() {
        Cards cards = new Cards(Arrays.asList(new Card(Suit.CLOVER, Denomination.TWO), new Card(Suit.HEART, Denomination.JACK), new Card(Suit.HEART, Denomination.JACK)));
        boolean actual = cards.isOnlyTwoCards();
        boolean expected = false;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 처음에 공개할 카드를 반환한다.")
    void getFirst() {
        Cards cards = new Cards(Arrays.asList(new Card(Suit.CLOVER, Denomination.TWO), new Card(Suit.HEART, Denomination.JACK)));
        String actual = cards.getFirst();
        String expected =  "2클로버";
        assertThat(actual).isEqualTo(expected);
    }
}