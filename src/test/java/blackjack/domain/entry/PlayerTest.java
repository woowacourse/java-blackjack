package blackjack.domain.entry;

import blackjack.domain.result.PlayerOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("두장의 카드를 지급받아 카드의 합을 계산한다.")
    void getTwoCards() {
        Player player = new Player("jason", 0, HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.KING), Card.valueOf(Suit.SPADE, Denomination.ACE))));

        assertThat(player.calculateCardsSum()).isEqualTo(21);
    }

    @Test
    @DisplayName("기본 카드가 주어진 후 한장의 카드를 더 추가한다.")
    void putCard() {
        Player player = new Player("jason", 0, HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.NINE), Card.valueOf(Suit.SPADE, Denomination.ACE))));
        player.putCard(Card.valueOf(Suit.HEART, Denomination.ACE));

        assertThat(player.calculateCardsSum()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어의 합이 높을 경우 승리를 반환한다.")
    void playerIsLoseByOver21() {
        Player player = new Player("jason", 0, HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.KING), Card.valueOf(Suit.SPADE, Denomination.ACE))));
        Dealer dealer = new Dealer(HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.KING), Card.valueOf(Suit.SPADE, Denomination.JACK))));

        assertThat(player.match(dealer)).isEqualTo(PlayerOutcome.WIN);
    }

    @Test
    @DisplayName("플레이어의 합이 낮을 경우 승리를 반환한다.")
    void playerIsWinByDealerOver21() {
        Player player = new Player("jason", 0, HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.NINE), Card.valueOf(Suit.SPADE, Denomination.ACE))));
        Dealer dealer = new Dealer(HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.KING), Card.valueOf(Suit.SPADE, Denomination.ACE))));

        assertThat(player.match(dealer)).isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("플레이어의 합과 같을 경우 무승부를 반환한다.")
    void playerIsDrawByDealerAndPlayerOver21() {
        Player player = new Player("jason", 0, HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.KING), Card.valueOf(Suit.SPADE, Denomination.ACE))));
        Dealer dealer = new Dealer(HoldCards.init(List.of(Card.valueOf(Suit.SPADE, Denomination.KING), Card.valueOf(Suit.SPADE, Denomination.ACE))));

        assertThat(player.match(dealer)).isEqualTo(PlayerOutcome.DRAW);
    }
}
