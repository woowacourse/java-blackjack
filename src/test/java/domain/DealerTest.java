package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Letter;
import type.Shape;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    private final List<Card> cards = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cards.add(Card.of(Shape.DIAMOND, Letter.TWO));
        cards.add(Card.of(Shape.CLOVER, Letter.JACK));
    }

    @Test
    @DisplayName("새로운 카드를 추가한다.")
    void pickNewCard() {
        Dealer dealer = new Dealer(new Hand(cards));

        dealer.pick(Card.of(Shape.DIAMOND, Letter.NINE));

        assertThat(dealer.getCardList().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러의 점수가 21점을 넘기면 bust 이다.")
    void bustDealer() {
        Dealer dealer = new Dealer(new Hand(cards));

        dealer.pick(Card.of(Shape.HEART, Letter.QUEEN));

        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("딜러의 점수가 21점 이하이면 bust 가 아니다.")
    void notBustDealer() {
        Dealer dealer = new Dealer(new Hand(cards));

        dealer.pick(Card.of(Shape.HEART, Letter.ACE));

        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    @DisplayName("딜러의 점수가 17점 이상이면 더 이상 카드를 받지 못한다.")
    void noMoreCard() {
        Dealer dealer = new Dealer(new Hand(cards));

        dealer.pick(Card.of(Shape.HEART, Letter.FIVE));

        assertThat(dealer.isMoreCardAble()).isFalse();
    }

    @Test
    @DisplayName("딜러의 점수가 16점 이하이면 카드를 더 받을 수 있다.")
    void isMoreCardAble() {
        Dealer dealer = new Dealer(new Hand(cards));

        dealer.pick(Card.of(Shape.HEART, Letter.FOUR));

        assertThat(dealer.isMoreCardAble()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드는 첫 번째 한 장만 보여주어야 한다.")
    void showOnlyOneCardOfDealer() {
        Dealer dealer = new Dealer(new Hand(cards));

        Card card = dealer.showOneCard();

        assertThat(card).isEqualTo(Card.of(Shape.DIAMOND, Letter.TWO));
    }

}
