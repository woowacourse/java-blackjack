package domain.participant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Rank;
import domain.Score;
import domain.Suit;
import domain.card.Card;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    void 정상적으로_생성되어야_한다() {
        assertDoesNotThrow(Dealer::createReady);
    }

    @Test
    void 손패_중_첫번째_카드만_반환해야_한다() {
        Dealer dealer = Dealer.createReady();

        dealer.addCard(Card.of(Suit.CLUB, Rank.K));
        dealer.addCard(Card.of(Suit.CLUB, Rank.ACE));
        dealer.addCard(Card.of(Suit.CLUB, Rank.NINE));
        Card firstCard = dealer.getFirstCard();

        Assertions.assertThat(firstCard).isEqualTo(Card.of(Suit.CLUB, Rank.K));
    }

    @Test
    void 딜러의_합계가_기준보다_작으면_참을_반환해야_한다() {
        Dealer dealer = Dealer.createReady();

        List<Card> sum15Cards = List.of(Card.of(Suit.CLUB, Rank.FOUR), Card.of(Suit.CLUB, Rank.ACE));
        dealer.addCards(sum15Cards);

        Assertions.assertThat(dealer.isHittable(Score.DEALER_HIT_STAND_BOUNDARY)).isTrue();
    }

    @Test
    void 딜러의_합계가_기준보다_크면_거짓을_반환해야_한다() {
        Dealer dealer = Dealer.createReady();

        List<Card> sum17Cards = List.of(Card.of(Suit.CLUB, Rank.SIX), Card.of(Suit.CLUB, Rank.ACE));
        dealer.addCards(sum17Cards);

        Assertions.assertThat(dealer.isHittable(Score.DEALER_HIT_STAND_BOUNDARY)).isFalse();
    }
}
