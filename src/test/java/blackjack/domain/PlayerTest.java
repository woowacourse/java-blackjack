package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 카드를 정상적으로 받았는지 확인한다.")
    void receiveCardTest() {
        //given
        Player player = new Player(new ParticipantName("아코"));
        Card card = new Card(CardNumber.ACE, CardSuit.DIAMOND);

        //when
        player.hit(card);

        //then
        assertTrue(player.getReceivedCards().contains(card));
    }

    @Test
    @DisplayName("Ace 카드를 포함하지 않은 플레이어의 카드를 계산해서 넘겨주기")
    void cardCalculation1() {
        //given
        Player player = new Player(new ParticipantName("아코"));
        List<Card> cards = List.of(
            new Card(CardNumber.FOUR, CardSuit.DIAMOND),
            new Card(CardNumber.EIGHT, CardSuit.SPADE)
        );
        int expect = cards.stream()
            .mapToInt(card -> card.getCardNumber().value)
                .sum();

        //when
        for (Card card : cards) {
            player.hit(card);
        }
        int result = player.calculateCardNumber();

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("Ace 카드를 포함된 플레이어의 카드를 계산해서 넘겨주기(Ace 카드를 11로 계산)")
    void cardCalculation2() {
        //given
        Player player = new Player(new ParticipantName("아코"));
        List<Card> cards = List.of(
            new Card(CardNumber.FOUR, CardSuit.DIAMOND),
            new Card(CardNumber.ACE, CardSuit.SPADE)
        );

        //when
        for (Card card : cards) {
            player.hit(card);
        }
        int result = player.calculateCardNumber();

        //then
        assertThat(result).isEqualTo(15);
    }

    @Test
    @DisplayName("Ace 카드를 포함된 플레이어의 카드를 계산해서 넘겨주기(Ace 카드를 1로 계산)")
    void cardCalculation3() {
        //given
        Player player = new Player(new ParticipantName("아코"));
        List<Card> cards = List.of(
            new Card(CardNumber.QUEEN, CardSuit.DIAMOND),
            new Card(CardNumber.EIGHT, CardSuit.SPADE),
            new Card(CardNumber.ACE, CardSuit.SPADE)
        );

        //when
        for (Card card : cards) {
            player.hit(card);
        }
        int result = player.calculateCardNumber();

        //then
        assertThat(result).isEqualTo(19);
    }
}
