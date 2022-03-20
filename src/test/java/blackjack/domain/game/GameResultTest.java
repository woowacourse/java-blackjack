package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class GameResultTest {

    @Test
    @DisplayName("딜러의 손익은 플레이어의 손익의 반대값이다")
    void getDealerProfitResult() {
        Player player = new Player("player", 2000);
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(List.of(
            new Card(Suit.DIAMOND, Denomination.SEVEN),
            new Card(Suit.SPADE, Denomination.TEN)
        ));
        player.initCards(List.of(
            new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.THREE),
            new Card(Suit.SPADE, Denomination.SEVEN)
        ));

        GameResult gameResult = new GameResult(participants);

        assertThat(gameResult.calculateTotalProfitResult().get(dealer)).isEqualTo(-2000);
    }
}
