package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dto.GameResultDto;
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
        //given
        Deck sampleDeck = Deck.createDeck(this::createSampleCards);

        //when, then
        assertDoesNotThrow(
                () -> BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards)
        );
    }

    @Test
    @DisplayName("다음 턴의 사용자가 있으면 제공한다")
    void whoseTure_success() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        Optional<Player> result = testGame.whoseTurn();

        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("hit 프로세스를 적합한 턴의 플레이어에게 요청하고 결과로 ParticipantDto를 반환받는다")
    void doHitProcess_success() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        ParticipantDto result = testGame.doHitProcess();

        assertEquals(ParticipantDto.class, result.getClass());
    }

    @Test
    @DisplayName("stand 프로세스를 적합한 턴의 플레이어에게 요청하고 결과로 ParticipantDto를 반환받는다")
    void doStandProcess_success() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        ParticipantDto result = testGame.doStandProcess();

        assertEquals(ParticipantDto.class, result.getClass());
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
    @DisplayName("플레이어들의 게임 초기 상태를 인원 수 대로 잘 가져온다")
    void getPlayersGameSettingStates_good() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        int expectSize = TEST_PLAYER_NAMES.size();
        int resultSize = testGame.getPlayersGameSettingStates().size();
        assertEquals(expectSize, resultSize);
    }

    @Test
    @DisplayName("딜러의 게임 초기 상태를 ParticipantDto 형태로 잘 받는다")
    void getDealerGameSettingState_good() {
        BlackJackGame testGame = BlackJackGame.ready(TEST_PLAYER_NAMES, this::createSampleCards);

        assertEquals(ParticipantDto.class, testGame.getDealerGameSettingState().getClass());
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