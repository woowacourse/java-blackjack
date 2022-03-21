package blackjack.domain.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.ParticipantCards;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BustTest {

    ParticipantCards bustCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.TWO),
            new Card(Suit.CLOVER, Denomination.TEN)));

    @Test
    @DisplayName("Bust일 시 earningRate는 -1이다.")
    void earningRate() {
        Bust playerBust = new Bust(bustCardsSet);
        Bust dealerBust = new Bust(bustCardsSet);

        assertThat(playerBust.earningRate(dealerBust)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Bust일 시 isBust는 true이다.")
    void isBust() {
        Bust playerBust = new Bust(bustCardsSet);

        assertThat(playerBust.isBust()).isEqualTo(true);
    }

}
