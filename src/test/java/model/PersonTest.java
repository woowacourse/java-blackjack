package model;

import static org.assertj.core.api.Assertions.assertThat;

import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

    @DisplayName("초기 사람이 가지고 있는 카드 수는 0이다.")
    @Test
    void initCardSizeZero() {
        assertThat(new Person().getCards()).hasSize(0);
    }

    @DisplayName("카드를 추가할 수 있다.")
    @Test
    void initCardSizeZe2ro() {
        Person person = new Person();
        Card card = new Card(CardShape.CLOVER, CardNumber.EIGHT);
        person.addCard(card);
        assertThat(person.getCards()).hasSize(1);
    }

    @DisplayName("카드의 합을 계산한다.")
    @Test
    void sumCardNumbers() {
        Person person = new Person();
        person.addCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        person.addCard(new Card(CardShape.CLOVER, CardNumber.FIVE));
        assertThat(person.sumCardNumbers()).isEqualTo(13);
    }

    @DisplayName("Ace가 나왔을 때는 21을 초과하지 않으며 21과 가까운 수가 되도록 11이나 1을 고른다.")
    @Test
    void sumAce() {
        Person person = new Person();
        person.addCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        person.addCard(new Card(CardShape.CLOVER, CardNumber.FIVE));
        person.addCard(new Card(CardShape.SPACE, CardNumber.ACE));
        assertThat(person.sumCardNumbers()).isEqualTo(14);
    }
}
