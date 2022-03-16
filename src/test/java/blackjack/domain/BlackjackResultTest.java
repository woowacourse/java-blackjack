package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackResultTest {

    @Test
    @DisplayName("결과 맵이 제대로 담겨서 나오는지 확인")
    void resultMapTest() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.JACK, Suit.CLUBS));
        dealer.hit(new Card(Denomination.JACK, Suit.HEARTS));
        dealer.hit(new Card(Denomination.JACK, Suit.SPADES));
        Guest guest1 = new Guest("a", (p) -> HitFlag.Y);
        guest1.hit(new Card(Denomination.ACE, Suit.CLUBS));
        guest1.hit(new Card(Denomination.FIVE, Suit.CLUBS));
        Guest guest2 = new Guest("a", (p) -> HitFlag.Y);
        guest2.hit(new Card(Denomination.TEN, Suit.SPADES));
        guest2.hit(new Card(Denomination.JACK, Suit.CLUBS));
        guest2.hit(new Card(Denomination.FIVE, Suit.CLUBS));
        BlackjackResult result = BlackjackResult.match(dealer, List.of(guest1, guest2));

        Map<String, String> resultMap = result.getResultMap();
        assertThat(resultMap.get("딜러")).isEqualTo("1승 1패");
    }
}
