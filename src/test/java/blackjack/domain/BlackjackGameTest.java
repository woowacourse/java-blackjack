package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    @Test
    @DisplayName("블랙잭 게임 생성")
    void createBlackjackGame() {

        assertThatCode(() -> new BlackjackGame(new Participants(
            List.of(new Player("마루"), new Player("엔젤앤지")))))
            .doesNotThrowAnyException();

    }

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
        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        BlackjackGame blackjackGame = new BlackjackGame(participants);
        blackjackGame.calculatePlayerResult();

        assertThat(blackjackGame.getDealerResult().get(WinningResult.WIN)).isEqualTo(1);
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
        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        BlackjackGame blackjackGame = new BlackjackGame(participants);
        blackjackGame.calculatePlayerResult();

        assertThat(blackjackGame.getDealerResult().get(WinningResult.LOSE)).isEqualTo(1);
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
        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        BlackjackGame blackjackGame = new BlackjackGame(participants);
        blackjackGame.calculatePlayerResult();

        assertThat(blackjackGame.getDealerResult().get(WinningResult.DRAW)).isEqualTo(1);
    }

}
