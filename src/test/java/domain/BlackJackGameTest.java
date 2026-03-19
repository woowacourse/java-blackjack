package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import common.ErrorMessage;
import dto.GameResultDto;
import dto.GameStateDto;
import dto.ParticipantDto;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {
    private static final List<String> TEST_PLAYER_NAMES = List.of("pobi", "terry", "rati", "gump");

    @Test
    @DisplayName("생성 잘 한다")
    void ready_good() {
        //when, then
        assertDoesNotThrow(
                () -> BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards)
        );
    }

    @Test
    @DisplayName("베팅을 안한 사용자가 있으면 제공한다")
    void whoseBettingTurn_exist() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        Optional<Player> result = testGame.whoseBettingTurn();

        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("다음 턴의 사용자가 있으면 제공한다")
    void whosePlayTurn_exist() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        Optional<Player> result = testGame.whosePlayTurn();

        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("bet을 시켜도 오류가 안난다")
    void doBetProcess_success() {
        int betAmountValue = 1_000;
        BetAmount betAmount = BetAmount.of(betAmountValue);
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        Player testPlayer = testGame.whoseBettingTurn().orElseThrow(
                () -> new IllegalStateException(ErrorMessage.TEST_STATE_EROR.getMessage())
        );
        assertDoesNotThrow(
                () -> testGame.doBetProcess(testPlayer, betAmount)
        );
    }

    @Test
    @DisplayName("hit 프로세스를 적합한 턴의 플레이어에게 요청하고 결과로 ParticipantDto를 반환받는다")
    void doHitProcess_success() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        Player testPlayer = testGame.whoseBettingTurn().orElseThrow(
                () -> new IllegalStateException(ErrorMessage.TEST_STATE_EROR.getMessage())
        );
        ParticipantDto firstPlayerParticipantDto = testGame.doHitProcess(testPlayer);

        assertEquals(TEST_PLAYER_NAMES.getFirst(), firstPlayerParticipantDto.name());
    }

    @Test
    @DisplayName("stand 프로세스를 적합한 턴의 플레이어에게 요청하고 결과로 ParticipantDto를 반환받는다")
    void doStandProcess_success() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        Player testPlayer = testGame.whoseBettingTurn().orElseThrow(
                () -> new IllegalStateException(ErrorMessage.TEST_STATE_EROR.getMessage())
        );
        ParticipantDto firstPlayerParticipantDto = testGame.doStandProcess(testPlayer);

        assertEquals(TEST_PLAYER_NAMES.getFirst(), firstPlayerParticipantDto.name());
        assertEquals(TEST_PLAYER_NAMES.getFirst(), firstPlayerParticipantDto.name());
    }

    @Nested
    class doDealerHitOrStandProcessTest {

        List<Card> playersCardsCandidates = List.of(
                new Card(CardShape.하트, CardContents.FOUR),
                new Card(CardShape.하트, CardContents.FIVE),
                new Card(CardShape.하트, CardContents.SIX),
                new Card(CardShape.하트, CardContents.SEVEN),
                new Card(CardShape.하트, CardContents.EIGHT),
                new Card(CardShape.하트, CardContents.NINE),
                new Card(CardShape.하트, CardContents.TEN),
                new Card(CardShape.하트, CardContents.J),
                new Card(CardShape.하트, CardContents.Q),
                new Card(CardShape.하트, CardContents.K)
        );

        @Test
        @DisplayName("카드를 추가하면 true 반환")
        void doDealerHitOrStandProcess_return_true() {
            //given
            List<Card> testCards = new ArrayList<>();
            List<Card> dealersCardsThatUnderThan16 = List.of(
                    new Card(CardShape.하트, CardContents.TWO),
                    new Card(CardShape.하트, CardContents.THREE)
            );

            testCards.addAll(dealersCardsThatUnderThan16);
            testCards.addAll(playersCardsCandidates);

            Deque<Card> testDeckCards = new ArrayDeque<>(testCards);
            BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, () -> testDeckCards);

            //when
            boolean result = testGame.doDealerHitOrStandProcess();

            //then
            assertTrue(result);
        }

        @Test
        @DisplayName("카드 추가할 수 없는 상황에서는 false 를 반환")
        void doDealerHitOrStandProcess_return_false_when_over_seventeen() {
            //given
            List<Card> testCards = new ArrayList<>();
            List<Card> dealersCardsThatOverThan16 = List.of(
                    new Card(CardShape.하트, CardContents.TEN),
                    new Card(CardShape.하트, CardContents.J)
            );
            testCards.addAll(dealersCardsThatOverThan16);
            testCards.addAll(playersCardsCandidates);

            Deque<Card> testDeckCards = new ArrayDeque<>(testCards);
            BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, () -> testDeckCards);

            //when, then
            boolean result = testGame.doDealerHitOrStandProcess();

            //then
            assertFalse(result);
        }

        @Test
        @DisplayName("카드를 추가안하면 false 반환")
        void doDealerHitOrStandProcess_return_false_when_dealer_blackjack() {
            //given
            List<Card> testCards = new ArrayList<>();
            List<Card> dealersCardsThatOverThan16 = List.of(
                    new Card(CardShape.하트, CardContents.TEN),
                    new Card(CardShape.하트, CardContents.A)
            );
            testCards.addAll(dealersCardsThatOverThan16);
            testCards.addAll(playersCardsCandidates);

            Deque<Card> testDeckCards = new ArrayDeque<>(testCards);
            BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, () -> testDeckCards);

            //when, then
            boolean result = testGame.doDealerHitOrStandProcess();

            //then
            assertFalse(result);
        }
    }

    @Test
    @DisplayName("게임 초기 상태를 GameStateDto 형태로 잘 받고, 플레이어 정보도 인원 수 만큼 존재한다")
    void getDealerGameSettingState_good() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);
        int expectMultiPlayersCount = TEST_PLAYER_NAMES.size();

        GameStateDto gameSettingState = testGame.getGameSettingState();

        int resultMultiPlayerCount = gameSettingState.multiPlayersDtos().size();
        assertEquals(GameStateDto.class, gameSettingState.getClass());
        assertEquals(expectMultiPlayersCount, resultMultiPlayerCount);
    }

    @Test
    @DisplayName("플레이어들과 사용자의 정보를 잘 받아와서 잘 계산된 베팅 결과를 포함하는 GameResultDto를 반환한다")
    void getGameResults_good() {
        //given
        List<Card> playersCardsCandidates = List.of(
                new Card(CardShape.다이아몬드, CardContents.TWO),
                new Card(CardShape.다이아몬드, CardContents.THREE),
                new Card(CardShape.다이아몬드, CardContents.FOUR),
                new Card(CardShape.다이아몬드, CardContents.FIVE),
                new Card(CardShape.다이아몬드, CardContents.SIX),
                new Card(CardShape.다이아몬드, CardContents.SEVEN),
                new Card(CardShape.다이아몬드, CardContents.EIGHT),
                new Card(CardShape.다이아몬드, CardContents.NINE),
                new Card(CardShape.다이아몬드, CardContents.TEN),
                new Card(CardShape.다이아몬드, CardContents.J),
                new Card(CardShape.다이아몬드, CardContents.Q),
                new Card(CardShape.다이아몬드, CardContents.K)
        );

        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, () -> new ArrayDeque<>(playersCardsCandidates));
        int commonBettingAmountValue = 10_000;
        BetAmount commonBettingAmount = BetAmount.of(commonBettingAmountValue);
        while (true) {
            Optional<Player> player = testGame.whoseBettingTurn();
            if (player.isEmpty()) {
                break;
            }
            testGame.doBetProcess(player.get(), commonBettingAmount);
        }

        double expectDealerEarnMoney = -40_000;
        double expectPlayer1EarnMoney = 10_000;
        double expectPlayer2EarnMoney = 10_000;
        double expectPlayer3EarnMoney = 10_000;
        double expectPlayer4EarnMoney = 10_000;

        //when
        GameResultDto result = testGame.getGameResults();

        //결과 검증
        int expectPlayerResultDtoSize = TEST_PLAYER_NAMES.size();
        int resultSize = result.playerResultDto().size();
        assertEquals(expectPlayerResultDtoSize, resultSize);
        assertNotNull(result.dealerResultDto());
        assertEquals(expectDealerEarnMoney, result.dealerResultDto().dealerEarnMoney());
        assertEquals(expectPlayer1EarnMoney, result.playerResultDto().get(0).playerEarnMoney());
        assertEquals(expectPlayer2EarnMoney, result.playerResultDto().get(1).playerEarnMoney());
        assertEquals(expectPlayer3EarnMoney, result.playerResultDto().get(2).playerEarnMoney());
        assertEquals(expectPlayer4EarnMoney, result.playerResultDto().get(3).playerEarnMoney());
    }

    private Deque<Card> createSampleCards() {
        CardShape[] shapes = CardShape.values();
        CardContents[] contents = CardContents.values();

        List<Card> sampleCards = new ArrayList<>();
        for (CardShape cardShape : shapes) {
            for (CardContents content : contents) {
                sampleCards.add(new Card(cardShape, content));
            }
        }

        return new ArrayDeque<>(sampleCards);
    }
}