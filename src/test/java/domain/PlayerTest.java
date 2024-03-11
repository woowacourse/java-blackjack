package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("플레이어는 자신의 패를 반환할 수 있다.")
    void getHand() {
        Player dealer = new Player(new Name("test"));
        List<Card> cards = dealer.getCards();
        Assertions.assertThat(cards).isNotNull();
    }

    @Test
    @DisplayName("플레이어는 처음에 카드 2장을 받을 수 있다.")
    void pickTwoCard() {
        Player player = new Player(new Name("test"));
        Card one = new Card(CardType.CLOVER,CardNumber.ACE);
        Card two = new Card(CardType.CLOVER,CardNumber.TWO);
        Deck deck = new Deck(one,two);
        player.pickTwoCards(deck);
        List<Card> cards = player.getCards();
        Assertions.assertThat(cards)
                .contains(one)
                .contains(two);
    }

    @Test
    @DisplayName("플레이어는 카드를 한 번만 뽑을 수 있다.")
    void takeCard() {
        Player player = new Player(new Name("test"));
        Card card = new Card(CardType.SPADE, CardNumber.TEN);
        Deck deck = new Deck(card);
        player.hit(deck);
        List<Card> cards = player.getCards();
        Assertions.assertThat(cards).isEqualTo(List.of(card));
    }

    @Test
    @DisplayName("문자열로 플레이어의 이름을 검증할 수 있다.")
    void createName() {
        Player player = new Player(new Name("test"));
        Assertions.assertThat(player.isName("test")).isTrue();
    }

    @Test
    @DisplayName("게이머의 점수 합계를 반환한다.")
    void getTotalScore() {
        Player player = new Player(new Name("test"));
        Deck deck = new Deck(
                new Card(CardType.SPADE, CardNumber.TEN));
        player.hit(deck);
        Assertions.assertThat(player.getTotalScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("Bust이면 true를 반환한다.")
    void isBustTrue() {
        Player player = new Player(new Name("test"));
        Deck deck = new Deck(
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.TEN));

        player.hit(deck);
        player.hit(deck);
        player.hit(deck);

        Assertions.assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("Bust가 아니면 false를 반환한다.")
    void isBustFalse() {
        Player player = new Player(new Name("test"));
        Deck deck = new Deck(
                new Card(CardType.SPADE, CardNumber.TEN),
                new Card(CardType.SPADE, CardNumber.TEN));
        player.pickTwoCards(deck);
        Assertions.assertThat(player.isBust()).isFalse();
    }
}