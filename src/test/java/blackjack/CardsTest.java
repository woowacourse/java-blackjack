package blackjack;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.utils.FixedCardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
    void add() {
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
    void sum_cards() {
        List<Card> cardValues = Arrays.asList(
            Card.from(Suits.CLOVER, Denominations.ACE),
            Card.from(Suits.CLOVER, Denominations.TWO));
        final Cards cards = new Cards(cardValues);

        Score score = cards.sumCards();
        assertThat(score).isEqualTo(Score.of(3));
    }

    @Test
    @DisplayName("결과를 위한 ACE 포함된 카드 합계")
    void sum_cards_for_result() {
        List<Card> cardValues = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.DIAMOND, Denominations.SIX));
        final Cards cards = new Cards(cardValues);

        Score score = cards.sumCardsForResult();

        assertThat(score).isEqualTo(Score.of(17));
    }

    @Test
    @DisplayName("Ace 4장인 경우 지지않는 최대 합계")
    void sum_cards_for_result1() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.DIAMOND, Denominations.ACE));
        Participant player = new Player(new Name("sarah"), new Cards(cards));

        player.takeCard(Card.from(Suits.DIAMOND, Denominations.ACE));
        player.takeCard(Card.from(Suits.DIAMOND, Denominations.ACE));

        Score score = player.sumCardsForResult();

        assertThat(score).isEqualTo(Score.of(14));
    }

}