package blackJack.domain;

import blackJack.domain.card.Card;
import blackJack.domain.card.Deck;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.WinDrawLose;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameTest {

    private final Player player1 = new Player("kei");
    private final Player player2 = new Player("rookie");
    private final Player player3 = new Player("parang");
    private final Dealer dealer = new Dealer();

    @Test
    @DisplayName("BlackJackGame 생성 테스트")
    void createBlackJackGame() {
        assertThat(initializeBlackJackGame()).isNotNull();
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 있는 경우 계속 카드를 받는지 테스트")
    void doDealerGame() {
        BlackJackGame blackJackGame = initializeBlackJackGame();
        Dealer dealerWhoFinishedGame = blackJackGame.doDealerGame();
        assertThat(dealerWhoFinishedGame.getScore()).isGreaterThan(16);
    }

    @Test
    @DisplayName("게임 시작시 최초 카드 분배 기능 테스트")
    void firstCardDispensing() {
        BlackJackGame blackJackGame = initializeBlackJackGame();

        blackJackGame.firstCardDispensing();

        assertThat(player1.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 승패 결과 테스트")
    void calculateDealerResult() {
        BlackJackGame blackJackGame = initializeBlackJackGame();
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<WinDrawLose, Integer> dealerResult = blackJackGame.calculateDealerResult();

        assertThat(dealerResult).contains(
                Map.entry(WinDrawLose.WIN, 1),
                Map.entry(WinDrawLose.DRAW, 0),
                Map.entry(WinDrawLose.LOSE, 2)
        );
    }

    @Test
    @DisplayName("플레이어들의 승패 결과 테스트")
    void calculatePlayersResult() {
        BlackJackGame blackJackGame = initializeBlackJackGame();
        dealer.receiveCard(Card.from(Suit.SPADE, Denomination.NINE));
        player1.receiveCard(Card.from(Suit.SPADE, Denomination.EIGHT));
        player2.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player3.receiveCard(Card.from(Suit.SPADE, Denomination.ACE));

        final Map<Player, WinDrawLose> playersResult = blackJackGame.calculatePlayersResult();

        assertThat(playersResult).contains(
                Map.entry(player1, WinDrawLose.LOSE),
                Map.entry(player2, WinDrawLose.WIN),
                Map.entry(player3, WinDrawLose.WIN)
        );
    }

    private BlackJackGame initializeBlackJackGame() {
        Participants participants = new Participants(dealer, List.of(player1, player2, player3));
        return new BlackJackGame(participants, Deck.createDeck());
    }
}
