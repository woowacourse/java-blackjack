package blackjack.domain.blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackJackGameTest {

    private Deck deck;
    private Participants participants;
    private Dealer dealer;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        deck = Deck.generate();
        players = Arrays.asList(new Player("로키", cards -> 15), new Player("수리", cards -> 19));
        dealer = new Dealer(cards -> 15);
        participants = new Participants(players, dealer);
    }

    @DisplayName("BlackJackGame 객체를 생성한다")
    @Test
    void testInitBlackJackGame() {
        //when
        BlackJackGame blackJackGame = new BlackJackGame(deck, participants);

        //then
        assertThat(blackJackGame).isNotNull();
    }

    @DisplayName("초기패를 돌리는 기능을 테스트한다")
    @Test
    void testHandInitCards() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, participants);

        //when
        blackJackGame.handInitCards();

        //then
        List<Player> players = blackJackGame.getPlayers();
        assertAll(
                () -> assertThat(players.get(0).getName()).isEqualTo("로키"),
                () -> assertThat(players.get(0).showCards()).hasSize(2),
                () -> assertThat(players.get(1).getName()).isEqualTo("수리"),
                () -> assertThat(players.get(1).showCards()).hasSize(2)
        );
    }

    @DisplayName("딜러의 턴을 플레이하는 기능을 테스트한다")
    @ParameterizedTest
    @CsvSource(value = {
            "16:1", "17:0"
    }, delimiter = ':')
    void testPlayDealerTurn(int totalDealerScore, int expected) {
        //given
        List<Player> players = Arrays.asList(new Player("로키", cards -> 15), new Player("수리", cards -> 19));
        Dealer dealer = new Dealer(cards -> totalDealerScore);
        Participants participants = new Participants(players, dealer);
        BlackJackGame blackJackGame = new BlackJackGame(deck, participants);

        //when
        blackJackGame.playDealerTurn();

        //then
        assertThat(blackJackGame.getDealer().showCards()).hasSize(expected);
    }

    @DisplayName("차례를 기다리는 플레이어가 있을 때, 차례를 기다리는 플레이어가 존재하는지 확인하는 기능")
    @Test
    void testExistWaitingPlayerIfExistWaitingPlayer() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, participants);

        //when
        boolean actual = blackJackGame.isExistWaitingPlayer();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("차례를 기다리는 플레이어가 없을 때, 차례를 기다리는 플레이어가 존재하는지 확인하는 기능")
    @Test
    void testExistWaitingPlayerIfNotExistWaitingPlayer() {
        //given
        Player waitingPlayer = new Player("로키", cards -> 15);
        waitingPlayer.endOwnTurn();
        List<Player> players = Arrays.asList(waitingPlayer);
        Participants participants = new Participants(players, dealer);
        BlackJackGame blackJackGame = new BlackJackGame(deck, participants);

        //when
        boolean actual = blackJackGame.isExistWaitingPlayer();

        //then
        assertThat(actual).isFalse();
    }

    @DisplayName("차례가 남은 Player 중 가장 순서가 빠른 Player를 반환하는 기능")
    @Test
    void testFindCurrentTurnPlayerIfExistWaitingPlayer() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, participants);

        //when
        Player currentPlayer = blackJackGame.findCurrentTurnPlayer();

        //then
        assertAll(
                () -> assertThat(currentPlayer.getName()).isEqualTo("로키"),
                () -> assertThat(currentPlayer.isContinue()).isTrue()
        );
    }

    @DisplayName("차례가 남은 Player가 없을 때, 가장 순서가 빠른 Player를 반환하는 기능")
    @Test
    void testFindCurrentTurnPlayerIfNotExistWaitingPlayer() {
        //given
        Player waitingPlayer = new Player("로키", cards -> 15);
        waitingPlayer.endOwnTurn();
        List<Player> players = Arrays.asList(waitingPlayer);
        Participants participants = new Participants(players, dealer);
        BlackJackGame blackJackGame = new BlackJackGame(deck, participants);

        //when //then
        assertThatThrownBy(() -> blackJackGame.findCurrentTurnPlayer())
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드를 더 받을지 묻는 기능을 테스트한다")
    @ParameterizedTest
    @CsvSource(value = {
            "true:1", "false:0"
    }, delimiter = ':')
    void testAskMorkCard(boolean isNeedCard, int expectedCardsNumber) {
        //given
        List<Player> players = Arrays.asList(new Player("로키", cards -> 15));
        Participants participants = new Participants(players, dealer);
        BlackJackGame blackJackGame = new BlackJackGame(deck, participants);

        //when
        blackJackGame.askMoreCard(isNeedCard);

        //then
        assertThat(blackJackGame.getPlayers().get(0).showCards()).hasSize(expectedCardsNumber);
    }
}
