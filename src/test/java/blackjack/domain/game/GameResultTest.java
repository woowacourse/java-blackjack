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
    @DisplayName("플레이어 패배 상태에서 딜러 버스트")
    void getSameLoseResult() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.TEN));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.JACK),
            new Card(Suit.SPADE, Denomination.QUEEN));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        GameResult gameResult = new GameResult(participants);

        assertThat(gameResult.getPlayerResult().get(player)).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("플레이어 승리 상태에서 딜러 버스트")
    void getSameWinResult() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.TEN));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.ACE));

        Player player = new Player("player");
        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();

        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        GameResult gameResult = new GameResult(participants);

        assertThat(gameResult.getPlayerResult().get(player)).isEqualTo(WinningResult.WIN);
    }
}
