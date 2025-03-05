package model;

import java.util.List;
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
        int sum = dealer.sum();

        // then
        Assertions.assertThat(sum).isEqualTo(expected);
    }
}
