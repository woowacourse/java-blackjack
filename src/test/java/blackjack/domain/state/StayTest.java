package blackjack.domain.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.ParticipantCards;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StayTest {

    ParticipantCards blackjackCardsSet;
    ParticipantCards BustCardsSet;
    ParticipantCards HittableCardsWinnerSet;
    ParticipantCards HittableCardsLoserSet;

    @BeforeEach
    void setupCards() {
        blackjackCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.JACK)));

        BustCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.TWO),
            new Card(Suit.DIAMOND, Denomination.JACK),
            new Card(Suit.HEART, Denomination.JACK)));

        HittableCardsWinnerSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.TWO)));

        HittableCardsLoserSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE)));
    }


    @Test
    @DisplayName("딜러가 블랙잭이면 플레이어는 -1의 earningRate를 가진다.")
    void getEarningRateLose() {
        State blackjackDealerState = new Blackjack(blackjackCardsSet);
        Stay playerState = new Stay(HittableCardsWinnerSet);

        assertThat(playerState.earningRate(blackjackDealerState)).isEqualTo(-1);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 버스트가 아니며 동점이면 0의 earningRate를 가진다.")
    void getEarningRateDraw() {
        Stay dealerState = new Stay(HittableCardsWinnerSet);
        Stay playerState = new Stay(HittableCardsWinnerSet);

        assertThat(playerState.earningRate(dealerState)).isEqualTo(0);
    }

    @Test
    @DisplayName("Stay상태에서 플레이어의 점수가 딜러보다 높으면 1의 earningRate을 가진다.")
    void getEarningRateWin1() {
        Stay dealerState = new Stay(HittableCardsLoserSet);
        Stay playerState = new Stay(HittableCardsWinnerSet);

        assertThat(playerState.earningRate(dealerState)).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러는 버스트, 플레이어는 버스트가 아닐 시 1의 earningRate를 가진다.")
    void getEarningRateWin2() {
        Bust dealerState = new Bust(BustCardsSet);
        Stay playerState = new Stay(HittableCardsWinnerSet);

        assertThat(playerState.earningRate(dealerState)).isEqualTo(1);
    }

    @Test
    @DisplayName("참가자 카드를 반환해준다.")
    void participantCards() {
        Stay playerState = new Stay(HittableCardsWinnerSet);

        assertThat(playerState.participantCards()).isSameAs(HittableCardsWinnerSet);
    }

}
