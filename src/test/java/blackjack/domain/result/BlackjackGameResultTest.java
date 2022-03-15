package blackjack.domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameResultTest {

    @Test
    @DisplayName("블랙잭 게임 생성")
    void createBlackjackGame() {

        assertThatCode(() -> new BlackjackGameResult(Participants.from(List.of("마루", "엔젤앤지"))))
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

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        BlackjackGameResult blackjackGameResult = new BlackjackGameResult(participants);
        blackjackGameResult.calculatePlayerResult();

        assertThat(blackjackGameResult.getDealerResult().get(WinningResult.WIN)).isEqualTo(1);
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

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        BlackjackGameResult blackjackGameResult = new BlackjackGameResult(participants);
        blackjackGameResult.calculatePlayerResult();

        assertThat(blackjackGameResult.getDealerResult().get(WinningResult.LOSE)).isEqualTo(1);
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

        Participants participants = Participants.from(List.of("player"));

        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);

        dealer.receiveInitCards(dealerCards);
        player.receiveInitCards(playerCards);

        BlackjackGameResult blackjackGameResult = new BlackjackGameResult(participants);
        blackjackGameResult.calculatePlayerResult();

        assertThat(blackjackGameResult.getDealerResult().get(WinningResult.DRAW)).isEqualTo(1);
    }

}
