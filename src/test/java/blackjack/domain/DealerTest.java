package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("처음의 받은 2장의 카드의 합이 16이하일 때 true 반환")
    void judgmentBelow16() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"));
        List<Card> cards = List.of(
            new Card(CardNumber.QUEEN, CardSuit.CLUB),
            new Card(CardNumber.FOUR, CardSuit.SPADE)
        );
        for (Card card : cards) {
            dealer.hit(card);
        }
        //when
        boolean result = dealer.decideHit();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("처음의 받은 2장의 카드의 합이 17이상일 때 false 반환")
    void judgmentOver16() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"));
        List<Card> cards = List.of(
            new Card(CardNumber.QUEEN, CardSuit.CLUB),
            new Card(CardNumber.NINE, CardSuit.SPADE)
        );
        for (Card card : cards) {
            dealer.hit(card);
        }
        //when
        boolean result = dealer.decideHit();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("딜러는 소프트 17을 적용한다.(Ace는 11로 게산)")
    void soft17() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"));
        List<Card> cards = List.of(
            new Card(CardNumber.ACE, CardSuit.SPADE),
            new Card(CardNumber.SIX, CardSuit.HEART)
        );
        for (Card card : cards) {
            dealer.hit(card);
        }
        int expect = cards.stream()
            .mapToInt(card -> card.getCardNumber().value)
            .sum() + 10;

        //when
        int result = dealer.calculateDealerCardNumber();

        //then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("딜러가 플레이어를 이길 시 WIN 리턴")
    void judgeWinner() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"));
        Player player = new Player(new ParticipantName("홍실"));
        dealer.hit(new Card(CardNumber.TEN, CardSuit.DIAMOND));
        dealer.hit(new Card(CardNumber.TEN, CardSuit.DIAMOND));
        player.hit(new Card(CardNumber.TWO, CardSuit.SPADE));
        player.hit(new Card(CardNumber.TWO, CardSuit.SPADE));

        //when
        WinningResult result = dealer.judgeWinOrLose(player);

        //then
        assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("딜러가 플레이어에게 졌을 시 LOSE 리턴")
    void judgeWinner2() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"));
        Player player = new Player(new ParticipantName("홍실"));
        dealer.hit(new Card(CardNumber.TWO, CardSuit.SPADE));
        dealer.hit(new Card(CardNumber.TWO, CardSuit.SPADE));
        player.hit(new Card(CardNumber.TEN, CardSuit.DIAMOND));
        player.hit(new Card(CardNumber.TEN, CardSuit.DIAMOND));

        //when
        WinningResult result = dealer.judgeWinOrLose(player);

        //then
        assertThat(result).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 플레이어와 같은 값을 가질 시 PUSH 리턴")
    void judgeWinner3() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"));
        Player player = new Player(new ParticipantName("홍실"));
        dealer.hit(new Card(CardNumber.NINE, CardSuit.SPADE));
        dealer.hit(new Card(CardNumber.EIGHT, CardSuit.SPADE));
        player.hit(new Card(CardNumber.ACE, CardSuit.DIAMOND));
        player.hit(new Card(CardNumber.SIX, CardSuit.DIAMOND));

        //when
        WinningResult result = dealer.judgeWinOrLose(player);

        //then
        assertThat(result).isEqualTo(WinningResult.PUSH);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 블랙잭일 때 PUSH 리턴")
    void judgeWinner4() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"));
        Player player = new Player(new ParticipantName("홍실"));
        dealer.hit(new Card(CardNumber.ACE, CardSuit.SPADE));
        dealer.hit(new Card(CardNumber.TEN, CardSuit.SPADE));
        player.hit(new Card(CardNumber.ACE, CardSuit.DIAMOND));
        player.hit(new Card(CardNumber.QUEEN, CardSuit.DIAMOND));

        //when
        WinningResult result = dealer.judgeWinOrLose(player);

        //then
        assertThat(result).isEqualTo(WinningResult.PUSH);
    }

}