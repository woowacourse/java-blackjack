package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.dealer.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어는 Hit 종료 후 Hit 상태가 아니다.")
    @Test
    void hit() {
        final Card card = Card.of(CardNumber.SEVEN, CardShape.DIA);
        final Dealer dealer = new Dealer(new Deck(List.of(card, card, card, card, card, card)));
        final Player player = Player.of(new PlayerName("pobi"), new BetAmount(0), dealer);

        player.hit(dealer, i -> true, (a, b) -> {});

        assertThat(player.isHit()).isFalse();
    }
}
