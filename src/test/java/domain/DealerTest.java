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
        cards.add(new Card(Shape.DIAMOND, Letter.TWO));
        cards.add(new Card(Shape.CLOVER, Letter.JACK));
    }

    @Test
    @DisplayName("새로운 카드를 추가한다.")
    void pickNewCard() {
        Participant dealer = new Dealer(new CardDeck(cards));

        dealer.pick(new Card(Shape.DIAMOND, Letter.NINE));

        assertThat(dealer.getCardDeck().getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러의 점수가 21점을 넘기면 bust 이다.")
    void bustDealer() {
        Participant dealer = new Dealer(new CardDeck(cards));

        dealer.pick(new Card(Shape.HEART, Letter.QUEEN));

        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("딜러의 점수가 21점 이하이면 bust 가 아니다.")
    void notBustDealer() {
        Participant dealer = new Dealer(new CardDeck(cards));

        dealer.pick(new Card(Shape.HEART, Letter.ACE));

        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    @DisplayName("딜러의 점수가 17점 이상이면 더 이상 카드를 받지 못한다.")
    void noMoreCard() {
        Participant dealer = new Dealer(new CardDeck(cards));

        dealer.pick(new Card(Shape.HEART, Letter.FIVE));

        assertThat(dealer.isMoreCardAble()).isFalse();
    }

    @Test
    @DisplayName("딜러의 점수가 16점 이하이면 카드를 더 받을 수 있다.")
    void isMoreCardAble() {
        Participant dealer = new Dealer(new CardDeck(cards));

        dealer.pick(new Card(Shape.HEART, Letter.FOUR));

        assertThat(dealer.isMoreCardAble()).isTrue();
    }

}
