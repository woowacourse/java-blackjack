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
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        Player testPlayer = testGame.whoseBettingTurn().orElseThrow(
                () -> new IllegalStateException(ErrorMessage.TEST_STATE_EROR.getMessage())
        );
        assertDoesNotThrow(
                () -> testGame.doBetProcess(testPlayer, betAmountValue)
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
        @DisplayName("카드를 추가안하면 false 반환")
        void doDealerHitOrStandProcess_return_false() {
            //given
            List<Card> testCards = new ArrayList<>();
            List<Card> dealersCardsThatOverThan16 = List.of(
                    new Card(CardShape.하트, CardContents.TEN),
                    new Card(CardShape.하트, CardContents.Q)
            );
            testCards.addAll(dealersCardsThatOverThan16);
            testCards.addAll(playersCardsCandidates);

            Deque<Card> testDeckCards = new ArrayDeque<>(testCards);
            BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, () -> testDeckCards);

            //when
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
    @DisplayName("플레이어들과 사용자의 정보를 잘 받아와서 GameResultDto를 반환한다")
    void getGameResults_good() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        GameResultDto result = testGame.getGameResults();

        int expectPlayerResultDtoSize = TEST_PLAYER_NAMES.size();
        int resultSize = result.playerResultDto().size();

        assertEquals(expectPlayerResultDtoSize, resultSize);
        assertNotNull(result.dealerResultDto());
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