package blackjack.model.participants;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import blackjack.model.results.Result;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {
    @DisplayName("플레이어 이름은 공백일 수 없다")
    @Test
    void validateName() {
        String emptyName = "";

        assertThatThrownBy(() -> new Player(emptyName)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어가 카드 한장을 지급받는다")
    @Test
    void addCard() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );

        Player player = new Player("daon");
        player.addCards(given);
        Card cardToAdd = new Card(CardNumber.FIVE, CardShape.DIAMOND);

        player.addCard(cardToAdd);
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

        assertThat(player.canHit()).isTrue();
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

        assertThat(player.canHit()).isFalse();
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
        comparisonCards.add(comparison);

        Player player = new Player("daon");
        player.addCards(given);
        Result playerResultStatus = player.getResult(comparisonCards);
        assertThat(playerResultStatus).isEqualTo(Result.LOSE);
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
        comparisonCards.add(comparison);

        Player player = new Player("daon");
        player.addCards(given);
        Result playerResultStatus = player.getResult(comparisonCards);
        assertThat(playerResultStatus).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 카드와 비교 카드 점수가 기준 점수보다 낮으면 두 점수의 대소관계로 결과를 낸다.")
    @ParameterizedTest
    @CsvSource(value = {"ACE,TWO,LOSE", "THREE,TWO,WIN", "FOUR,FOUR,PUSH"})
    void compareScore3(CardNumber cardNumber, CardNumber otherNumber, Result expected) {
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

        Player player = new Player("daon");
        player.addCards(given);
        Result playerResultStatus = player.getResult(comparisonCards);
        assertThat(playerResultStatus).isEqualTo(expected);
    }
}
