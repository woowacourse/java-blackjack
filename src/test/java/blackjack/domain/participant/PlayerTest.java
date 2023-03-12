package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import blackjack.domain.betting.BettingMoney;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 카드를 정상적으로 받았는지 확인한다.")
    void receiveCardTest() {
        //given
        Player player = generatePlayer();
        Card card = new Card(CardNumber.ACE, CardSuit.DIAMOND);

        //when
        player.hit(card);

        //then
        assertTrue(player.getHand().getReceivedCards().contains(card));
    }

    @Test
    @DisplayName("Ace 카드를 포함하지 않은 플레이어의 카드를 계산해서 넘겨주기")
    void cardCalculation1() {
        //given
        Player player = generatePlayer();
        List<Card> cards = List.of(
            new Card(CardNumber.FOUR, CardSuit.DIAMOND),
            new Card(CardNumber.EIGHT, CardSuit.SPADE)
        );
        int expect = cards.stream()
            .mapToInt(card -> card.getCardNumber().getValue())
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
        Player player = generatePlayer();
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
        Player player = generatePlayer();
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

    @Test
    @DisplayName("플레이어의 숫자가 21보다 작으면 true를 반환한다")
    void decideHit1() {
        //given
        Player player = generatePlayer();
        List<Card> cards = List.of(
            new Card(CardNumber.QUEEN, CardSuit.CLUB),
            new Card(CardNumber.FIVE, CardSuit.SPADE)
        );
        for (Card card : cards) {
            player.hit(card);
        }

        //when
        boolean result = player.decideHit();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어의 숫자가 21보다 크면 false를 반환한다")
    void decideHit2() {
        //given
        Player player = generatePlayer();
        List<Card> cards = List.of(
            new Card(CardNumber.QUEEN, CardSuit.CLUB),
            new Card(CardNumber.ACE, CardSuit.SPADE)
        );

        //when
        for (Card card : cards) {
            player.hit(card);
        }
        boolean result = player.decideHit();

        //then
        assertThat(result).isFalse();
    }

    private Player generatePlayer() {
        return new Player(new ParticipantName("아코"), new Hand(), new BettingMoney("1000"));
    }
}
