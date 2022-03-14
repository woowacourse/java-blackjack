package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GamerTest {

    private Gamer gamer = new Gamer("name");

    @Test
    @DisplayName("카드를 추가한다.")
    void addCard() {
        gamer.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.ACE));
        List<Card> cards = gamer.getCards();

        assertThat(cards.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("보유 카드 번호 합 반환")
    void calculateCardsNumberSum() {
        Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.TEN);
        Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.FIVE);

        gamer.addCard(card1);
        gamer.addCard(card2);

        int sum = gamer.getCardsNumberSum();
        assertThat(sum).isEqualTo(15);
    }

    @Test
    @DisplayName("Ace가 1로 계산된 보유 카드 번호 합 반환")
    void calculateCardsNumberSumWithAceOne() {
        Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.NINE);
        Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.QUEEN);
        Card card3 = Card.getInstance(CardShape.SPADE, CardNumber.ACE);

        gamer.addCard(card1);
        gamer.addCard(card2);
        gamer.addCard(card3);

        int sum = gamer.getCardsNumberSum();
        assertThat(sum).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace가 11로 계산된 보유 카드 번호 합 반환")
    void calculateCardsNumberSumWithAceEleven() {
        Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.JACK);
        Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.ACE);

        gamer.addCard(card1);
        gamer.addCard(card2);

        int sum = gamer.getCardsNumberSum();
        assertThat(sum).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace가 4장일 때 보유 카드 번호 합 반환")
    void calculateCardsNumberSumWithFourAce() {
        Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.ACE);
        Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.ACE);
        Card card3 = Card.getInstance(CardShape.SPADE, CardNumber.ACE);
        Card card4 = Card.getInstance(CardShape.SPADE, CardNumber.ACE);

        gamer.addCard(card1);
        gamer.addCard(card2);
        gamer.addCard(card3);
        gamer.addCard(card4);

        int sum = gamer.getCardsNumberSum();
        assertThat(sum).isEqualTo(14);
    }
}