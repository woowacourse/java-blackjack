package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("생성")
    void create() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.HEART, Denominations.ACE));

        assertThatCode(() -> new Cards(cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성된 리스트에 카드 포함여부 확인")
    void create2() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.HEART, Denominations.ACE));

        assertThat(new Cards(cards).getUnmodifiableList())
            .contains(Card.from(Suits.DIAMOND, Denominations.ACE),
                Card.from(Suits.HEART, Denominations.ACE));
    }

    @Test
    @DisplayName("카드추가")
    void takeCard() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.HEART, Denominations.ACE));
        final Cards cardsValue = new Cards(cards);

        cardsValue.takeCard(Card.from(Suits.SPADE, Denominations.ACE));

        assertThat(cardsValue.getUnmodifiableList())
            .contains(Card.from(Suits.SPADE, Denominations.ACE));
    }


    @Test
    @DisplayName("지급된 카드 합계")
    void sumCards() {
        List<Card> cardValues = Arrays.asList(
            Card.from(Suits.CLOVER, Denominations.ACE),
            Card.from(Suits.CLOVER, Denominations.TWO));
        final Cards cards = new Cards(cardValues);

        Score score = cards.sumCards();
        assertThat(score).isEqualTo(Score.of(3));
    }

    @Test
    @DisplayName("결과를 위한 ACE 포함된 카드 합계")
    void sumCardsForResult() {
        List<Card> cardValues = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.DIAMOND, Denominations.SIX));
        final Cards cards = new Cards(cardValues);

        Score score = cards.sumCardsForResult();

        assertThat(score).isEqualTo(Score.of(17));
    }

    @Test
    @DisplayName("Ace 4장인 경우 지지않는 최대 합계")
    void sumCardsForResult1() {
        List<Card> cardsValues = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.DIAMOND, Denominations.ACE));
        final Cards cards = new Cards(cardsValues);

        Score score = cards.sumCardsForResult();

        assertThat(score).isEqualTo(Score.of(14));
    }

}