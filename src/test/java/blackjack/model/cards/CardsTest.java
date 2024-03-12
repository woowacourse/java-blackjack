package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.blackjackgame.PlayerProfitCalculator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @DisplayName("카드를 지급받는다")
    @Test
    void add() {
        List<Card> cards = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        );
        Cards given = new Cards();
        given.add(cards);
        Card card = new Card(CardNumber.ACE, CardShape.SPADE);

        given.add(card);

        assertThat(given.getCards()).hasSize(3);
    }

    @DisplayName("점수 총합을 반환한다.")
    @Test
    void getCardsScore() {
        List<Card> cards = List.of(
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        );
        Cards given = new Cards();
        given.add(cards);
        Score score = given.getCardsScore();

        assertThat(score).isEqualTo(new Score(12));
    }

    @DisplayName("Ace를 포함하면 다른 점수를 더한다.")
    @Test
    void getCardScore() {
        List<Card> cards = List.of(
                new Card(CardNumber.ACE, CardShape.DIAMOND),
                new Card(CardNumber.TWO, CardShape.HEART),
                new Card(CardNumber.FIVE, CardShape.CLOVER)
        );
        Cards given = new Cards();
        given.add(cards);

        Score score = given.getCardsScore();

        assertThat(score).isEqualTo(new Score(18));
    }

    @DisplayName("내 카드와 상대카드 모두 블랙잭이면 푸시다.")
    @Test
    void getPlayerResultStatusBothBlackJack() {
        Cards cards = new Cards();
        cards.add(List.of(
                new Card(CardNumber.ACE, CardShape.SPADE),
                new Card(CardNumber.KING, CardShape.DIAMOND)
        ));
        Cards otherCards = new Cards();
        otherCards.add(List.of(
                new Card(CardNumber.KING, CardShape.HEART),
                new Card(CardNumber.ACE, CardShape.CLOVER)
        ));

        PlayerProfitCalculator result = cards.getPlayerResultStatus(otherCards);
        assertThat(result).isEqualTo(PlayerProfitCalculator.PUSH);
    }

    @DisplayName("내 카드만 블랙잭이면 블랙잭이다.")
    @Test
    void getPlayerResultStatusMyBlackJack() {
        Cards cards = new Cards();
        cards.add(List.of(
                new Card(CardNumber.ACE, CardShape.SPADE),
                new Card(CardNumber.KING, CardShape.DIAMOND)
        ));
        Cards otherCards = new Cards();
        otherCards.add(List.of(
                new Card(CardNumber.KING, CardShape.HEART),
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        ));

        PlayerProfitCalculator result = cards.getPlayerResultStatus(otherCards);
        assertThat(result).isEqualTo(PlayerProfitCalculator.BLACKJACK);
    }
}
