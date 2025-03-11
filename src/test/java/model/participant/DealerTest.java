package model.participant;

import java.util.List;
import java.util.Map;

import model.card.Card;
import model.card.CardDeck;
import model.card.Suit;
import model.card.NormalRank;
import model.score.MatchType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 객체가 잘 생성되는 지")
    void newDealer() {

        // given
        final Participant dealer = Dealer.from(new CardDeck());
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
        final Card card = new CardDeck().pickCard();

        // when
        Participant dealer = Dealer.from(new CardDeck());
        dealer.addCard(card);

        // then
        Assertions.assertThat(dealer.getCards()).contains(card);
    }

    @Test
    @DisplayName("딜러의 핸즈에 있는 카드 랭크의 합을 잘 구하는 지")
    void sum() {
        // given
        // 총 합이 9
        List<Card> divideCards = List.of(
                new Card(Suit.HEARTS, NormalRank.FIVE),
                new Card(Suit.CLUBS, NormalRank.FOUR)
        );
        int expected = divideCards.stream()
                .mapToInt(card -> card.getRank().getScore())
                .sum();

        Participant dealer = Dealer.from(new CardDeck());
        for (Card divideCard : divideCards) {
            dealer.addCard(divideCard);
        }

        // when
        int sum = dealer.getScore();

        // then
        Assertions.assertThat(sum).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: true")
    void isNotUpTrue() {

        // given
        List<Card> divideCards = List.of(
                new Card(Suit.HEARTS, NormalRank.JACK),
                new Card(Suit.CLUBS, NormalRank.FOUR)
        );

        Dealer dealer = Dealer.from(new CardDeck());
        for (Card divideCard : divideCards) {
            dealer.addCard(divideCard);
        }
        // when
        // then
        Assertions.assertThat(dealer.isHit()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: false")
    void isNotUpFalse() {

        // given
        List<Card> divideCards = List.of(
                new Card(Suit.HEARTS, NormalRank.JACK),
                new Card(Suit.CLUBS, NormalRank.KING)
        );

        Dealer dealer = Dealer.from(new CardDeck());
        for (Card divideCard : divideCards) {
            dealer.addCard(divideCard);
        }
        // when
        // then
        System.out.println(dealer.getScore());
        Assertions.assertThat(dealer.isHit()).isFalse();
    }

    @Test
    @DisplayName("딜러의 게임 결과 업데이트가 잘 되는 지")
    void dealerGameResult() {
        Dealer dealer = Dealer.from(new CardDeck());
        dealer.updateResult(MatchType.WIN);
        Map<MatchType, Integer> matchResult = dealer.getMatchResult();
        Assertions.assertThat(matchResult.get(MatchType.WIN)).isEqualTo(1);
    }
}
