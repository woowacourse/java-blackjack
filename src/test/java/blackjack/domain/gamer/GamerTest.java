package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GamerTest {

    @Test
    @DisplayName("플레이어를 생성할 때, 카드 2개를 추가한다.")
    void addCard() {
        Deck deck = new Deck();
        Gamer player = new Player("name", List.of(deck.draw(), deck.draw()), 1000);
        assertThat(player.getCardsSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("보유 카드 번호 합 반환")
    void calculateCardsNumberSum() {
        Card card1 = new Card(CardShape.SPADE, CardNumber.TEN);
        Card card2 = new Card(CardShape.SPADE, CardNumber.FIVE);
        Gamer player = new Player("name", List.of(card1, card2), 1000);

        int sum = player.getCardsNumberSum();
        assertThat(sum).isEqualTo(15);
    }

    @Test
    @DisplayName("Ace가 1로 계산된 보유 카드 번호 합 반환")
    void calculateCardsNumberSumWithAceOne() {
        List<Card> cards = List.of(new Card(CardShape.SPADE, CardNumber.NINE), new Card(CardShape.SPADE, CardNumber.QUEEN));
        Gamer player = new Player("name", cards, 1000);

        Card card3 = new Card(CardShape.SPADE, CardNumber.ACE);
        player.addCard(card3);
        int sum = player.getCardsNumberSum();
        assertThat(sum).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace가 11로 계산된 보유 카드 번호 합 반환")
    void calculateCardsNumberSumWithAceEleven() {
        Card card1 = new Card(CardShape.SPADE, CardNumber.JACK);
        Card card2 = new Card(CardShape.SPADE, CardNumber.ACE);
        List<Card> cards = List.of(card1, card2);
        Gamer player = new Player("name", cards, 1000);

        int sum = player.getCardsNumberSum();
        assertThat(sum).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace가 4장일 때 보유 카드 번호 합 반환")
    void calculateCardsNumberSumWithFourAce() {
        Card card1 = new Card(CardShape.SPADE, CardNumber.ACE);
        Card card2 = new Card(CardShape.SPADE, CardNumber.ACE);
        Card card3 = new Card(CardShape.SPADE, CardNumber.ACE);
        Card card4 = new Card(CardShape.SPADE, CardNumber.ACE);
        List<Card> cards = List.of(card1, card2, card3, card4);
        Gamer player = new Player("name", cards, 1000);

        int sum = player.getCardsNumberSum();
        assertThat(sum).isEqualTo(14);
    }
}