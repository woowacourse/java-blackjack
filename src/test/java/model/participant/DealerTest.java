package model.participant;

import java.util.List;
import java.util.Map;

import model.card.Card;
import model.card.CardDeck;
import model.card.RankType;
import model.card.SuitType;
import model.score.MatchType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 객체가 잘 생성되는 지")
    void newDealer() {

        // given
        final Participant dealer = Dealer.of();
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
        Participant dealer = Dealer.of();
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
                new Card(SuitType.HEARTS, RankType.FIVE),
                new Card(SuitType.CLUBS, RankType.FOUR)
        );
        int expected = divideCards.stream()
                .mapToInt(card -> card.getRank().getScore().getFirst())
                .sum();

        Participant dealer = Dealer.of();
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
                new Card(SuitType.HEARTS, RankType.JACK),
                new Card(SuitType.CLUBS, RankType.FOUR)
        );

        Dealer dealer = Dealer.of();
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
                new Card(SuitType.HEARTS, RankType.JACK),
                new Card(SuitType.CLUBS, RankType.KING)
        );

        Dealer dealer = Dealer.of();
        dealer.addCards(divideCards);

        // when
        // then
        System.out.println(dealer.getSum());
        Assertions.assertThat(dealer.isNotUp()).isFalse();
    }

    @Test
    @DisplayName("딜러의 게임 결과 업데이트가 잘 되는 지")
    void dealerGameResult() {
        Dealer dealer = Dealer.of();
        dealer.updateResult(MatchType.WIN);
        Map<MatchType, Integer> matchResult = dealer.getMatchResult();
        Assertions.assertThat(matchResult.get(MatchType.WIN)).isEqualTo(1);
    }
}
