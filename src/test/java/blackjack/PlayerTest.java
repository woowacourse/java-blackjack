package blackjack;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import blackjack.model.Card;
import blackjack.model.CardGenerator;
import blackjack.model.CardNumber;
import blackjack.model.CardShape;
import blackjack.model.Cards;
import blackjack.model.Player;
import blackjack.model.ResultStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {
    @DisplayName("플레이어가 카드 한장을 지급받는다")
    @Test
    void addCard() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );

        Player player = new Player("daon");
        player.addCards(given);
        CardGenerator cardGenerator = new CardGenerator(maxRange -> 3);

        player.addCard(cardGenerator.drawCard());
        assertThat(player.getCards().getCards()).hasSize(3);
    }

    @DisplayName("카드를 추가로 받을 수 있는지 확인한다")
    @Test
    void checkDrawCardState() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Player player = new Player("daon");
        player.addCards(given);

        assertThat(player.checkDrawCardState()).isTrue();
    }

    @DisplayName("카드를 추가로 받을 수 있는지 확인한다")
    @Test
    void checkDrawCardStateOverWinningScore() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        Player player = new Player("daon");
        player.addCards(given);

        assertThat(player.checkDrawCardState()).isFalse();
    }

    @DisplayName("플레이어 카드가 기준 점수보다 크면 진다.")
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
        comparisonCards.addCard(comparison);

        Player player = new Player("daon");
        player.addCards(given);
        ResultStatus resultStatus = player.getResultStatus(comparisonCards);
        assertThat(resultStatus).isEqualTo(ResultStatus.LOSE);
    }

    @DisplayName("플레이어 카드가 기준 점수보다 낮고 비교 카드 점수가 기준 점수보다 크면 이긴다.")
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
        comparisonCards.addCard(comparison);

        Player player = new Player("daon");
        player.addCards(given);
        ResultStatus resultStatus = player.getResultStatus(comparisonCards);
        assertThat(resultStatus).isEqualTo(ResultStatus.WIN);
    }

    @DisplayName("플레이어 카드와 비교 카드 점수가 기준 점수보다 낮으면 두 점수의 대소관계로 결과를 낸다.")
    @ParameterizedTest
    @CsvSource(value = {"ACE,TWO,LOSE", "THREE,TWO,WIN", "FOUR,FOUR,PUSH"})
    void compareScore3(CardNumber cardNumber, CardNumber otherNumber, ResultStatus expected) {
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
        comparisonCards.addCard(comparison);

        Player player = new Player("daon");
        player.addCards(given);
        ResultStatus resultStatus = player.getResultStatus(comparisonCards);
        assertThat(resultStatus).isEqualTo(expected);
    }
}
