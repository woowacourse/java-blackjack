package model.participant;

import model.card.Card;
import model.card.CardDeck;
import model.card.Rank;
import model.card.Suit;
import model.score.MatchResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class DealerTest {

    @Test
    @DisplayName("딜러 객체가 잘 생성되는 지")
    void newDealer() {

        // given
        final Participant dealer = Dealer.newInstance();
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
        Participant dealer = Dealer.newInstance();
        dealer.addCards(cards);

        // then
        Assertions.assertThat(dealer.getHands().size()).isEqualTo(amount);
    }

    @Test
    @DisplayName("딜러의 핸즈에 있는 카드 랭크의 합을 잘 구하는 지")
    void sum() {
        // given
        // 총 합이 9
        List<Card> divideCards = List.of(
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.CLUBS, Rank.FOUR)
        );
        int expected = divideCards.stream()
                .mapToInt(Card::getRankScore)
                .sum();

        Participant dealer = Dealer.newInstance();
        dealer.addCards(divideCards);

        // when
        int sum = dealer.getSum();

        // then
        Assertions.assertThat(sum).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: true")
    void isNotUpTrue() {

        // given
        List<Card> divideCards = List.of(
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.CLUBS, Rank.FOUR)
        );

        Dealer dealer = Dealer.newInstance();
        dealer.addCards(divideCards);

        // when
        // then
        Assertions.assertThat(dealer.isNotUp()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: false")
    void isNotUpFalse() {

        // given
        List<Card> divideCards = List.of(
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.CLUBS, Rank.KING)
        );

        Dealer dealer = Dealer.newInstance();
        dealer.addCards(divideCards);

        // when
        // then
        System.out.println(dealer.getSum());
        Assertions.assertThat(dealer.isNotUp()).isFalse();
    }

    @Test
    @DisplayName("딜러의 게임 결과 업데이트가 잘 되는 지")
    void dealerGameResult() {
        Dealer dealer = Dealer.newInstance();
        dealer.updateResult(MatchResult.WIN);
        Map<MatchResult, Integer> matchResult = dealer.getMatchResult();
        Assertions.assertThat(matchResult.get(MatchResult.WIN)).isEqualTo(1);
    }
}
