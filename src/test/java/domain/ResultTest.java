package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

public class ResultTest {

    void 플레이어보다_21에_가깝다면_승리(String card1, String card2, int cardValue1, int cardValue2, Result expected) {
        List<Card> dealerCardsByCardBox = new ArrayList<>();
        dealerCardsByCardBox.add(new Card("9하트", 9));
        dealerCardsByCardBox.add(new Card("10하트", 10));
        Cards dealerCards = new Cards(dealerCardsByCardBox);

        Dealer dealer = new Dealer(dealerCards);

        Name playerName = new Name("wuga");
        List<Card> playerCardsByCardBox = new ArrayList<>();
        playerCardsByCardBox.add(new Card(card1, cardValue1));
        playerCardsByCardBox.add(new Card(card2, cardValue2));
        Cards playerCards = new Cards(playerCardsByCardBox);
        Betting betting = new Betting(10000,0);

        Player player = new Player(playerCards, playerName,betting);

        assertThat(Result.checkWinningResult(dealer.sumOfParticipantCards(),player.sumOfParticipantCards())).isEqualTo(expected);
    }

}
