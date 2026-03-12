package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import common.ErrorMessage;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MultiPlayersTest {
    private static final List<String> TEST_PLAYER_NAMES = List.of("pobi", "terry", "rati", "gump");

    Deck totalDeck;

    @BeforeEach
    void setUpTotalDeck() {
        CardCreationStrategy totalCardCreationStrategy = this::createSampleCards;
        totalDeck = Deck.createDeck(totalCardCreationStrategy);
    }

    @Nested
    class ofTest {
        @Test
        @DisplayName("생성 잘 한다")
        void of_good() {
            //when, then
            assertDoesNotThrow(
                    () -> MultiPlayers.of(TEST_PLAYER_NAMES, totalDeck)
            );
        }

        @Test
        @DisplayName("이름이 중복되면 오류가 발생한다")
        void of_fail_duplication() {
            //given
            List<String> testPlayerNames = List.of("pobi", "pobi");

            //when && then
            assertThatThrownBy(() -> MultiPlayers.of(testPlayerNames, totalDeck))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NAME_UNIQUENESS_ERR.getMessage());
        }

        @Test
        @DisplayName("5명 초과 시 오류가 발생한다")
        void of_fail_too_many_players() {
            //given
            List<String> testPlayerNames = List.of("pobi", "gump", "rati", "terry", "neo", "james");

            //when && then
            assertThatThrownBy(() -> MultiPlayers.of(testPlayerNames, totalDeck))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.MAX_PLAYER_ERROR.getMessage());
        }
    }

    @Nested
    class findNotStayPlayerTest {
        @Test
        @DisplayName("stay가 아닌 사용자가 있으면 Player를 반환한다")
        void findNotStayPlayer_exist() {
            //given
            MultiPlayers testMultiPlayers = MultiPlayers.of(TEST_PLAYER_NAMES, totalDeck);

            //when
            Optional<Player> result = testMultiPlayers.findNotStayPlayer();

            //then
            assertNotNull(result.get());
        }

        @Test
        @DisplayName("stay가 아닌 사용자가 없으면 빈 Optional를 반환한다")
        void findNotStayPlayer_not_exist() {
            //given
            MultiPlayers testMultiPlayers = MultiPlayers.of(TEST_PLAYER_NAMES, totalDeck);
            while (true) {
                Optional<Player> findResult = testMultiPlayers.findNotStayPlayer();
                if (findResult.isEmpty()) {
                    break;
                }
                testMultiPlayers.executeStand(findResult.get());
            }

            //when
            Optional<Player> result = testMultiPlayers.findNotStayPlayer();

            //then
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    class executeToPlayer {
        @Test
        @DisplayName("hit가 정상적으로 다른 플레이이에게 요청이 되면 결과로 Player 객체를 받는다")
        void executeHit_good() {
            //given
            MultiPlayers testMultiPlayers = MultiPlayers.of(TEST_PLAYER_NAMES, totalDeck);
            Player testPlayer = testMultiPlayers.findNotStayPlayer().get();

            //when
            Player result = testMultiPlayers.executeHit(testPlayer, totalDeck::drawCard);

            //then
            assertEquals(Player.class, result.getClass());
        }

        @Test
        @DisplayName("stand가 정상적으로 다른 플레이이에게 요청이 되면 결과로 Player 객체를 받는다")
        void executeStand_good() {
            //given
            MultiPlayers testMultiPlayers = MultiPlayers.of(TEST_PLAYER_NAMES, totalDeck);
            Player testPlayer = testMultiPlayers.findNotStayPlayer().get();

            //when
            Player result = testMultiPlayers.executeStand(testPlayer);

            //then
            assertEquals(Player.class, result.getClass());
        }
    }

    @Test
    @DisplayName("초기 상태를 잘 가져온다")
    void getInitialState_success() {
        //given
        String testerName = "pobi";
        Card heartTen = new Card(CardShape.하트, CardContents.TEN);
        Card heartJ = new Card(CardShape.하트, CardContents.J);
        Queue<Card> onlyTwoTenCards = new LinkedList<>(List.of(heartTen, heartJ));
        List<String> onlyOneNames = List.of(testerName);
        CardCreationStrategy onlyTwoTenCardsCreationStrategy = () -> new ArrayDeque<>(onlyTwoTenCards);
        MultiPlayers multiPlayers = MultiPlayers.of(onlyOneNames, Deck.createDeck(onlyTwoTenCardsCreationStrategy));
        ParticipantDto expect = ParticipantDto.from(
                Player.from(testerName, Hand.of(heartTen, heartJ))
        );

        //when
        List<ParticipantDto> result = multiPlayers.getInitialStates();

        //then
        Assertions.assertEquals(expect, result.getFirst());
    }

    @Test
    @DisplayName("게임 결과를 잘 가져온다")
    void checkPlayersGameResult_success() {
        //given
        String testerName = "pobi";
        List<String> onlyOneNames = List.of(testerName);

        Card heartTwo = new Card(CardShape.하트, CardContents.TWO);
        Card heartThree = new Card(CardShape.하트, CardContents.THREE);
        Card heartTen = new Card(CardShape.하트, CardContents.TEN);
        Card heartJ = new Card(CardShape.하트, CardContents.J);
        Queue<Card> onlyTwoTenCards = new LinkedList<>(List.of(heartTen, heartJ));

        Dealer testDealer = Dealer.from(Hand.of(heartTwo, heartThree));
        Player expectedPlayer = Player.from(testerName, Hand.of(heartTen, heartJ));
        CardCreationStrategy onlyTwoTenCardsCreationStrategy = () -> new ArrayDeque<>(onlyTwoTenCards);
        MultiPlayers multiPlayers = MultiPlayers.of(onlyOneNames, Deck.createDeck(onlyTwoTenCardsCreationStrategy));
        PlayerResultDto expect = PlayerResultDto.from(expectedPlayer, testDealer);

        //when
        List<PlayerResultDto> result = multiPlayers.checkPlayersGameResult(testDealer);

        //then
        Assertions.assertEquals(expect, result.getFirst());
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
