package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {

    @ParameterizedTest
    @DisplayName("숫자 합이 21이 넘지 않고 에이스가 존재하지 않을 때까지 에이스를 하나씩 1로 바꿔 계산한다.")
    @CsvSource(value = {"ACE,NINE,TWO,TWO,14",
            "ACE,TWO,TWO,TWO,17",
            "ACE,ACE,NINE,NINE,20",
            "ACE,ACE,TWO,TWO,16",
            "ACE,ACE,ACE,TEN,13",
            "ACE,ACE,ACE,ACE,14",})
    void calculateWith2Ace(CardNumber cardNumber1,
                           CardNumber cardNumber2,
                           CardNumber cardNumber3,
                           CardNumber cardNumber4,
                           int expected) {
        // given
        Card card1 = new Card(cardNumber1, CardShape.DIA);
        Card card2 = new Card(cardNumber2, CardShape.HEART);
        Card card3 = new Card(cardNumber3, CardShape.CLOVER);
        Card card4 = new Card(cardNumber4, CardShape.SPADE);
        List<Card> cards = List.of(card1, card2, card3, card4);
        Player player = Player.from("kirby");
        cards.forEach(player::addCard);

        // when
        Score sum = player.calculate();

        // then
        assertThat(sum).isEqualTo(new Score(expected));
    }

    @Test
    @DisplayName("카드의 합이 21이면 블랙잭이다.")
    void isBlackjack() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.TEN, CardShape.CLOVER);
        List<Card> cards = List.of(card1, card2);
        Player player = Player.from("kirby");
        cards.forEach(player::addCard);

        // when
        BlackjackStatus blackjackStatus = player.getStatus();

        // then
        assertThat(blackjackStatus).isEqualTo(BlackjackStatus.BLACKJACK);
    }

    @Test
    @DisplayName("카드의 합이 21 초과면 죽는다.")
    void isNotBlackjack() {
        // given
        Card card1 = new Card(CardNumber.TEN, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.NINE, CardShape.HEART);
        Card card3 = new Card(CardNumber.NINE, CardShape.CLOVER);
        List<Card> cards = List.of(card1, card2, card3);
        Player player = Player.from("kirby");
        cards.forEach(player::addCard);

        // when
        BlackjackStatus blackjackStatus = player.getStatus();

        // then
        assertThat(blackjackStatus).isEqualTo(BlackjackStatus.DEAD);
    }

    @Test
    @DisplayName("참여자의 카드의 합이 21 미만이면 블랙잭은 아니지만 살았다.")
    void isAlive() {
        // given
        Card card1 = new Card(CardNumber.ACE, CardShape.CLOVER);
        Card card2 = new Card(CardNumber.NINE, CardShape.CLOVER);
        List<Card> cards = List.of(card1, card2);
        Player player = Player.from("kirby");
        cards.forEach(player::addCard);

        // when
        BlackjackStatus blackjackStatus = player.getStatus();

        // then
        assertThat(blackjackStatus).isEqualTo(BlackjackStatus.ALIVE);
    }

}
