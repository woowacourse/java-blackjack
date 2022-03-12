package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class GameResultTest {

    @Test
    @DisplayName("승리 결과 도출")
    void getWinResult() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.FOUR),
            new Card(Suit.CLOVER, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.EIGHT));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TWO),
            new Card(Suit.SPADE, Denomination.EIGHT),
            new Card(Suit.CLOVER, Denomination.TEN));

        Player player = new Player("player");

        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();
        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        GameResult gameResult = new GameResult(participants);

        assertThat(gameResult.getDealerResult().get(WinningResult.WIN)).isEqualTo(1);
    }

    @Test
    @DisplayName("패배 결과 도출")
    void getLoseResult() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.EIGHT));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.TWO),
            new Card(Suit.SPADE, Denomination.EIGHT),
            new Card(Suit.CLOVER, Denomination.ACE));

        Player player = new Player("player");

        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();
        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        GameResult gameResult = new GameResult(participants);

        assertThat(gameResult.getDealerResult().get(WinningResult.LOSE)).isEqualTo(1);
    }

    @Test
    @DisplayName("무승부 결과 도출")
    void getDrawResult() {
        List<Card> dealerCards = List.of(new Card(Suit.DIAMOND, Denomination.THREE),
            new Card(Suit.CLOVER, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.EIGHT));
        List<Card> playerCards = List.of(new Card(Suit.HEART, Denomination.THREE),
            new Card(Suit.SPADE, Denomination.NINE),
            new Card(Suit.CLOVER, Denomination.EIGHT));

        Player player = new Player("player");

        Participants participants = new Participants(List.of(player));
        Dealer dealer = participants.getDealer();
        dealer.initCards(dealerCards);
        player.initCards(playerCards);

        GameResult gameResult = new GameResult(participants);

        assertThat(gameResult.getDealerResult().get(WinningResult.DRAW)).isEqualTo(1);
    }
}
