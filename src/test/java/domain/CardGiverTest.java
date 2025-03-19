package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.CustomException;
import fixture.CardFixture;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardGiverTest {

    @DisplayName("모든 참여자들에게 카드를 2장 배분한다")
    @Test
    void test1() {
        // given
        List<Card> fixedCards = CardFixture.createFixedCards();
        CardGiver cardGiver = new CardGiver(new Deck(new ArrayList<>(fixedCards)));
        Player player = new Player("이름");
        Dealer dealer = new Dealer();
        //when
        cardGiver.dealingTo(new ArrayList<>(List.of(player, dealer)));

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(player.getHand()).isEqualTo(new Hand(List.of(fixedCards.get(0), fixedCards.get(1))));
            softly.assertThat(dealer.getHand()).isEqualTo(new Hand(List.of(fixedCards.get(2), fixedCards.get(3))));
        });
    }

    @DisplayName("플레이어가 hit을 원하지 않는다면 hit하지 않는다")
    @Test
    void test2() {
        // given
        Player player = new Player("웨이드");
        Hand originHand = new Hand(new ArrayList<>(player.getHand().getCards()));
        boolean isRequestHit = false;
        CardGiver cardGiver = new CardGiver(Deck.createShuffledBlackJackCards());
        // when
        cardGiver.hit(player, isRequestHit);
        // then
        assertThat(originHand).isEqualTo(player.getHand());
    }

    @DisplayName("플레이어의 카드 합산이 21이 넘는데, hit을 시도한다면 예외를 발생시킨다")
    @Test
    void test3() {
        // given
        Hand hand = new Hand(new ArrayList<>(List.of(
                new Card(CardNumberType.SEVEN, CardType.CLOVER),
                new Card(CardNumberType.JACK, CardType.CLOVER),
                new Card(CardNumberType.SEVEN, CardType.CLOVER)
        )));
        Player player = new Player("웨이드", hand);
        boolean isRequestHit = true;
        CardGiver cardGiver = new CardGiver(Deck.createShuffledBlackJackCards());
        // when // then
        assertThatThrownBy(() -> {
            cardGiver.hit(player, isRequestHit);
        }).isInstanceOf(CustomException.class)
                .hasMessageContaining("카드의 합이 21을 초과하였습니다. 더이상 카드를 받을 수 없습니다.");
    }

    @DisplayName("플레이어가 hit한다면 카드를 추가한다")
    @Test
    void test4() {
        // given
        Player player = new Player("웨이드");
        Hand originHand = new Hand(new ArrayList<>(player.getHand().getCards()));
        boolean isRequestHit = true;
        CardGiver cardGiver = new CardGiver(Deck.createShuffledBlackJackCards());
        // when
        cardGiver.hit(player, isRequestHit);
        // then
        assertThat(originHand).isNotEqualTo(player.getHand());
    }

    @DisplayName("딜러는 draw가 가능할 시 카드 한장을 추가로 받는다")
    @Test
    void test5() {
        // given
        Dealer dealer = new Dealer();
        Hand originHand = new Hand(new ArrayList<>(dealer.getHand().getCards()));
        CardGiver cardGiver = new CardGiver(Deck.createShuffledBlackJackCards());
        // when
        cardGiver.draw(dealer);
        // then
        assertThat(originHand).isNotEqualTo(dealer.getHand());
    }

    @DisplayName("딜러는 draw가 불가능할 시 카드 한장을 추가로 받지 않는다")
    @Test
    void test6() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(CardNumberType.ACE, CardType.CLOVER),
                new Card(CardNumberType.EIGHT, CardType.HEART)
        ));
        Dealer dealer = new Dealer(dealerHand);
        Hand originHand = new Hand(new ArrayList<>(dealer.getHand().getCards()));
        CardGiver cardGiver = new CardGiver(Deck.createShuffledBlackJackCards());
        // when
        cardGiver.draw(dealer);
        // then
        assertThat(originHand).isEqualTo(dealer.getHand());
    }
}
