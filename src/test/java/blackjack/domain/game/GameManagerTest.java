package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardGenerator;
import blackjack.domain.user.GameUserStorage;
import blackjack.domain.user.Nickname;
import blackjack.dto.FinalHands;
import blackjack.dto.HandState;
import blackjack.dto.InitialHands;
import blackjack.dto.PlayerWinningResult;
import blackjack.dto.WinningState;
import blackjack.mock.GameInputOutputMock;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameManagerTest {

    CardGenerator cardGenerator;
    CardDeck cardDeck;
    GameInputOutputMock gameInputOutput;
    GameUserStorage gameUserStorage;
    GameManager gameManager;

    @BeforeEach
    void beforeEach() {
        cardGenerator = new CardGenerator();
        cardDeck = new CardDeck(cardGenerator);
        gameUserStorage = new GameUserStorage();
        gameManager = new GameManager(gameUserStorage, cardDeck);
        gameInputOutput = new GameInputOutputMock();
        gameManager.setUpInputOutput(gameInputOutput);
    }

    @Test
    @DisplayName("입력된 닉네임만으로 플레이어를 등록할 수 있다.")
    void canRegisterPlayer() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        gameManager.runGame(nicknames);

        InitialHands initialHands = gameInputOutput.getInitialHands();
        assertThat(initialHands.playerHand())
                .extracting(HandState::nickname)
                .containsExactlyInAnyOrder("쿠키", "빙봉");
    }

    @Test
    @DisplayName("플레이어에게 초기카드를 분배할 수 있다.")
    void canDistributeInitialCard() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        gameManager.runGame(nicknames);

        InitialHands initialHands = gameInputOutput.getInitialHands();
        assertThat(initialHands.playerHand())
                .hasSize(GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("플레이어 턴을 진행할 수 있다.")
    void canProcessPlayerTurn() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        gameManager.runGame(nicknames);

        FinalHands finalHands = gameInputOutput.getFinalHands();
        assertThat(finalHands.playerHand())
                .extracting(HandState::cards)
                .hasSizeGreaterThanOrEqualTo(GameRule.INITIAL_CARD_COUNT.getValue());
        assertThat(finalHands.playerHand())
                .extracting(HandState::point)
                .filteredOn(point -> point >= GameRule.LIMIT_POINT_BEFORE_BUST.getValue())
                .hasSize(nicknames.size());
    }

    @Test
    @DisplayName("딜러의 턴을 진행할 수 있다.")
    void canProcessDealerTurn() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        gameManager.runGame(nicknames);

        FinalHands finalHands = gameInputOutput.getFinalHands();
        assertThat(finalHands.dealerHand().cards())
                .hasSizeGreaterThanOrEqualTo(GameRule.INITIAL_CARD_COUNT.getValue());
        assertThat(finalHands.dealerHand().point())
                .isGreaterThanOrEqualTo(GameRule.DEALER_LIMIT_POINT.getValue());
    }

    @Test
    @DisplayName("승패를 계산할 수 있다.")
    void canWinningState() {
        List<Nickname> nicknames = List.of(new Nickname("쿠키"));
        gameManager.runGame(nicknames);

        FinalHands finalHands = gameInputOutput.getFinalHands();
        WinningState winningState = gameInputOutput.getWinningState();
        if (finalHands.dealerHand().point() <= GameRule.LIMIT_POINT_BEFORE_BUST.getValue()) {
            checkLose(winningState);
            return;
        }
        checkWinOrDraw(winningState);
    }

    private static void checkLose(WinningState winningState) {
        PlayerWinningResult winningResult = winningState.playerWinningResults().getFirst();
        WinningType winningType = winningResult.winningType();
        assertThat(winningType == WinningType.LOSE)
                .isTrue();
    }

    private static void checkWinOrDraw(WinningState winningState) {
        PlayerWinningResult winningResult = winningState.playerWinningResults().getFirst();
        WinningType winningType = winningResult.winningType();
        assertThat(winningType == WinningType.DRAW || winningType == WinningType.WIN)
                .isTrue();
    }
}