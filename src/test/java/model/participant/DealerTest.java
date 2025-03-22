package model.participant;

import model.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DealerTest {

    @Test
    @DisplayName("딜러 객체가 잘 생성되는 지")
    void newDealer() {

        // given
        final Participant dealer = Dealer.create();
        String expected = "딜러";

        // when
        String nickname = dealer.getNickname();

        // then
        Assertions.assertThat(nickname).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 추가 기능이 잘 작동하는 지")
    void addCardsSuccess() {

        // given
        final int amount = 2;
        final List<Card> cards = new CardDeck().pickCard(2);

        // when
        Participant dealer = Dealer.create();
        dealer.addCards(cards);

        // then
        Assertions.assertThat(dealer.getHands().size()).isEqualTo(amount);
    }

    @Test
    @DisplayName("딜러의 핸즈에 있는 카드 랭크의 합을 잘 구하는 지")
    void sum() {
        // given
        // 총 합이 9
        List<Card> distributeCards = List.of(
                new SingleScoreCard(Suit.HEARTS, Rank.FIVE),
                new SingleScoreCard(Suit.CLUBS, Rank.FOUR)
        );
        int expected = distributeCards.stream()
                .mapToInt(Card::getScore)
                .sum();

        Participant dealer = Dealer.create();
        dealer.addCards(distributeCards);

        // when
        int sum = dealer.getScore();

        // then
        Assertions.assertThat(sum).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: true")
    void isNotUpTrue() {

        // given
        List<Card> distributeCards = List.of(
                new SingleScoreCard(Suit.HEARTS, Rank.JACK),
                new SingleScoreCard(Suit.CLUBS, Rank.FOUR)
        );

        Dealer dealer = Dealer.create();
        dealer.addCards(distributeCards);

        // when
        // then
        Assertions.assertThat(dealer.ableToDraw()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: false")
    void isNotUpFalse() {

        // given
        List<Card> distributeCards = List.of(
                new SingleScoreCard(Suit.HEARTS, Rank.JACK),
                new SingleScoreCard(Suit.CLUBS, Rank.KING)
        );

        Dealer dealer = Dealer.create();
        dealer.addCards(distributeCards);

        // when
        // then
        System.out.println(dealer.getScore());
        Assertions.assertThat(dealer.ableToDraw()).isFalse();
    }
}
