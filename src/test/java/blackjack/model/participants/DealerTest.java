package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import blackjack.view.PlayerResultStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @DisplayName("기준 점수와 비교하여 카드를 더 받을 수 있는지 판단한다.")
    @ParameterizedTest
    @CsvSource(value = {"FIVE,true", "SIX,true", "SEVEN,false", "EIGHT,false"})
    void checkCanGetMoreCard(CardNumber given, boolean expected) {
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(
                new Card(given, CardShape.DIAMOND),
                new Card(CardNumber.KING, CardShape.SPADE)
        );
        dealer.addCards(cards);

        boolean result = dealer.checkCanGetMoreCard();
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("비교하는 카드가 버스트라면 플레이어는 진다.")
    @Test
    void getResultStatus() {
        List<Card> given = List.of(
                new Card(CardNumber.SEVEN, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        List<Card> comparison = List.of(
                new Card(CardNumber.KING, CardShape.HEART),
                new Card(CardNumber.KING, CardShape.CLOVER),
                new Card(CardNumber.KING, CardShape.CLOVER)
        );
        Cards comparisonCards = new Cards();
        comparisonCards.add(comparison);

        Dealer dealer = new Dealer();
        dealer.addCards(given);
        PlayerResultStatus playerResultStatus = dealer.getResultStatus(comparisonCards);
        assertThat(playerResultStatus).isEqualTo(PlayerResultStatus.LOSE);
    }

    @DisplayName("둘다 버스트라면 플레이어는 진다.")
    @Test
    void getResultStatus3() {
        List<Card> given = List.of(
                new Card(CardNumber.KING, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        );
        List<Card> comparison = List.of(
                new Card(CardNumber.KING, CardShape.HEART),
                new Card(CardNumber.KING, CardShape.CLOVER),
                new Card(CardNumber.KING, CardShape.CLOVER)
        );
        Cards comparisonCards = new Cards();
        comparisonCards.add(comparison);

        Dealer dealer = new Dealer();
        dealer.addCards(given);
        PlayerResultStatus playerResultStatus = dealer.getResultStatus(comparisonCards);
        assertThat(playerResultStatus).isEqualTo(PlayerResultStatus.LOSE);
    }

    @DisplayName("딜러 카드만 버스트이면 플레이어는 이긴다.")
    @Test
    void getResultStatus2() {
        List<Card> given = List.of(
                new Card(CardNumber.KING, CardShape.HEART),
                new Card(CardNumber.KING, CardShape.DIAMOND),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        List<Card> comparison = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        Cards comparisonCards = new Cards();
        comparisonCards.add(comparison);

        Dealer dealer = new Dealer();
        dealer.addCards(given);
        PlayerResultStatus playerResultStatus = dealer.getResultStatus(comparisonCards);
        assertThat(playerResultStatus).isEqualTo(PlayerResultStatus.WIN);
    }

    @DisplayName("딜러 카드와 비교 카드 점수가 기준 점수보다 낮으면 두 점수의 대소관계로 결과를 낸다.")
    @ParameterizedTest
    @CsvSource(value = {"ACE,TWO,WIN", "THREE,TWO,LOSE", "FOUR,FOUR,PUSH"})
    void compareScore3(CardNumber cardNumber, CardNumber otherNumber, PlayerResultStatus expected) {
        List<Card> given = List.of(
                new Card(cardNumber, CardShape.HEART),
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        List<Card> comparison = List.of(
                new Card(otherNumber, CardShape.HEART),
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        Cards comparisonCards = new Cards();
        comparisonCards.add(comparison);

        Dealer dealer = new Dealer();
        dealer.addCards(given);
        PlayerResultStatus playerResultStatus = dealer.getResultStatus(comparisonCards);
        assertThat(playerResultStatus).isEqualTo(expected);
    }
}
