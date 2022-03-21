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

public class HitTest {

    ParticipantCards hittableCardsSet;
    ParticipantCards notHittableCardsSet;

    Card card = new Card(Suit.SPADE, Denomination.TWO);
    Card notHittableCard = new Card(Suit.SPADE, Denomination.TEN);

    @BeforeEach
    void setupCards() {
        hittableCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.TWO)));

        notHittableCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.TWO),
            new Card(Suit.CLOVER, Denomination.TEN)));
    }

    @Test
    @DisplayName("카드를 더 뽑았을 때 Bust일 시 Bust를 반환한다.")
    void hitToBust() {
        Hit hit = new Hit(notHittableCardsSet);

        assertThat(hit.draw(notHittableCard)).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("카드를 더 뽑았을 때 Bust가 아닐 시 Hit을 반환한다.")
    void hitToHit() {
        Hit hit = new Hit(hittableCardsSet);

        assertThat(hit.draw(card)).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Hit 상태일 때 Stay를 부르면 Stay를 반환한다.")
    void readyToHit() {
        Hit hit = new Hit(hittableCardsSet);

        assertThat(hit.stay()).isInstanceOf(Stay.class);
    }

}
