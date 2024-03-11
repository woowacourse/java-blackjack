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

    @DisplayName("카드를 추가발급받는다")
    @Test
    void addCardLessThan() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Dealer dealer = new Dealer();
        dealer.addCards(given);

        Card card = new Card(CardNumber.ACE, CardShape.DIAMOND);
        dealer.addCard(card);

        assertThat(dealer.getCards().getCards()).hasSize(3);
    }

    @DisplayName("딜러 카드가 기준 점수보다 크면 진다.")
    @Test
    void compareScore() {
        List<Card> given = List.of(
                new Card(CardNumber.SEVEN, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        List<Card> comparison = List.of(
                new Card(CardNumber.ACE, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        Cards comparisonCards = new Cards();
        comparisonCards.add(comparison);

        Dealer dealer = new Dealer();
        dealer.addCards(given);
        PlayerResultStatus playerResultStatus = dealer.getResultStatus(comparisonCards);
        assertThat(playerResultStatus).isEqualTo(PlayerResultStatus.LOSE);
    }

    @DisplayName("딜러 카드가 기준 점수보다 낮고 비교 카드 점수가 기준 점수보다 크면 플레이어는 이긴다.")
    @Test
    void compareScore2() {
        List<Card> given = List.of(
                new Card(CardNumber.ACE, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        List<Card> comparison = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER),
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
