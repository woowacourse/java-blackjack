package blackjack.domain.blackjack;

import blackjack.domain.card.Deck;
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
    private List<String> players;

    @BeforeEach
    void setUp() {
        deck = Deck.generate();
        players = Arrays.asList("로키", "수리");
    }

    @DisplayName("BlackJackGame 객체를 생성한다")
    @Test
    void testInitBlackJackGame() {
        //when
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 0);

        //then
        assertThat(blackJackGame).isNotNull();
    }

    @DisplayName("초기패를 돌리는 기능을 테스트한다")
    @Test
    void testHandInitCards() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 0);

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

    @DisplayName("차례를 기다리는 플레이어가 있을 때, 차례를 기다리는 플레이어가 존재하는지 확인하는 기능")
    @Test
    void testExistWaitingPlayerIfExistWaitingPlayer() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 17);

        //when
        boolean actual = blackJackGame.isExistWaitingPlayer();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("차례를 기다리는 플레이어가 없을 때, 차례를 기다리는 플레이어가 존재하는지 확인하는 기능")
    @Test
    void testExistWaitingPlayerIfNotExistWaitingPlayer() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 22);

        //when
        boolean actual = blackJackGame.isExistWaitingPlayer();

        //then
        assertThat(actual).isFalse();
    }

    @DisplayName("차례가 남은 Player 중 가장 순서가 빠른 Player를 반환하는 기능")
    @Test
    void testFindCurrentTurnPlayerIfExistWaitingPlayer() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 17);

        //when
        Player currentPlayer = blackJackGame.findCurrentTurnPlayer();

        //then
        assertAll(
                () -> assertThat(currentPlayer.getName()).isEqualTo("로키"),
                () -> assertThat(currentPlayer.isContinue()).isTrue()
        );
    }

    @DisplayName("차례가 남은 Player가 없을 때, 예외를 발생시킨다")
    @Test
    void testFindCurrentTurnPlayerIfNotExistWaitingPlayer() {
        //given
        Player waitingPlayer = new Player("로키", cards -> 15);
        waitingPlayer.endOwnTurn();
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 22);

        //when //then
        assertThatThrownBy(() -> blackJackGame.findCurrentTurnPlayer())
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드를 더 받을지 묻는 기능을 테스트한다")
    @ParameterizedTest
    @CsvSource(value = {
            "true:1", "false:0"
    }, delimiter = ':')
    void testAskMoreCard(boolean isNeedCard, int expectedCardsNumber) {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 15);

        //when
        blackJackGame.askMoreCard(isNeedCard);

        //then
        assertThat(blackJackGame.getPlayers().get(0).showCards()).hasSize(expectedCardsNumber);
    }

    @DisplayName("배팅을 안한 플레이어가 있는지 확인하는 기능")
    @Test
    void testIsExistWaitedBattingPlayer() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 15);

        //when
        boolean actual = blackJackGame.isExistWaitingBattingPlayer();

        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("배팅을 안한 플레이어가 존재하지 않을 때 배팅을 안한 플레이어가 있는지 확인하는 기능")
    @Test
    void testIsExistWaitingBattingPlayerIfNotExistWaitedBattingPlayer() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 15);
        blackJackGame.findCurrentBattingPlayer().bet(10000);
        blackJackGame.findCurrentBattingPlayer().bet(5000);

        //when
        boolean actual = blackJackGame.isExistWaitingBattingPlayer();

        //then
        assertThat(actual).isFalse();
    }

    @DisplayName("배팅을 안한 Player 중 가장 순서가 빠른 Player를 반환하는 기능")
    @Test
    void testFindCurrentBattingPlayer() {
        //given
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 15);

        //when
        Player currentBattingPlayer = blackJackGame.findCurrentBattingPlayer();

        //then
        assertThat(currentBattingPlayer).extracting("name").isEqualTo("로키");
        assertThat(currentBattingPlayer.isNotBatting()).isTrue();
    }

    @DisplayName("모든 Player가 배팅을 했을 때, 배팅을 해야할 Player를 호출하면 예외를 발생시킨다.")
    @Test
    void testFindCurrentBattingPlayerIfNotExistWaitedBattingPlayer() {
        //given
        players = Arrays.asList("로키", "수리");
        BlackJackGame blackJackGame = new BlackJackGame(deck, players, cards -> 15);
        blackJackGame.findCurrentBattingPlayer().bet(1_000);
        blackJackGame.findCurrentBattingPlayer().bet(2_000);

        //when //then
        assertThatThrownBy(() -> blackJackGame.findCurrentBattingPlayer())
                .isExactlyInstanceOf(IllegalStateException.class);
    }
}
