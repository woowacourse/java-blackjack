package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.winningstrategy.BlackjackWinningStrategy;
import blackjack.domain.game.winningstrategy.FinalWinningStrategy;
import blackjack.domain.game.winningstrategy.PlayingWinningStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class GameResultTest {

    @Test
    @DisplayName("딜러가 버스트나도 이미 결정된 결과는 그대로여야함 - 패배 상태")
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
        gameResult.evaluateWinningResult(dealer, new BlackjackWinningStrategy());
        gameResult.evaluateWinningResult(dealer, new PlayingWinningStrategy());

        dealer.addCard(new Card(Suit.CLOVER, Denomination.KING));
        gameResult.evaluateWinningResult(dealer, new FinalWinningStrategy());

        assertThat(gameResult.getPlayerResult().get(player)).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트나도 이미 결정된 결과는 그대로여야함 - 승리 상태")
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
        gameResult.evaluateWinningResult(dealer, new BlackjackWinningStrategy());

        dealer.addCard(new Card(Suit.CLOVER, Denomination.EIGHT));
        gameResult.evaluateWinningResult(dealer, new FinalWinningStrategy());

        assertThat(gameResult.getPlayerResult().get(player)).isEqualTo(WinningResult.WIN);
    }
}
