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

public class BlackjackTest {

    ParticipantCards blackjackCardsSet;
    ParticipantCards hittableCardsSet;

    @BeforeEach
    void setupCards() {
        blackjackCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.JACK)));

        hittableCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.TWO)));
    }

    @Test
    @DisplayName("플레이어만 블랙잭인 경우 earningRate는 1이다.")
    void earningRatePlayerBlackjack() {
        Blackjack playerState = new Blackjack(blackjackCardsSet);
        Stay dealerState = new Stay(hittableCardsSet);

        assertThat(playerState.earningRate(dealerState)).isEqualTo(1.5);
    }

    @Test
    @DisplayName("딜러가 블랙잭인 경우 earningRate는 0이다.")
    void earningRateDealerBlackjack() {
        Blackjack playerState = new Blackjack(blackjackCardsSet);
        Blackjack dealerState = new Blackjack(blackjackCardsSet);

        assertThat(playerState.earningRate(dealerState)).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭인 경우 isBlackjack은 true이다.")
    void isBlackjack() {
        Blackjack playerState = new Blackjack(blackjackCardsSet);

        assertThat(playerState.isBlackjack()).isEqualTo(true);
    }

}
