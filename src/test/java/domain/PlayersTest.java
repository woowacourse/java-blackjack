package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import common.ErrorMessage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayersTest {
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
                    () -> Players.of(TEST_PLAYER_NAMES, totalDeck)
            );
        }

        @Test
        @DisplayName("이름이 중복되면 오류가 발생한다")
        void of_fail_duplication() {
            //given
            List<String> testPlayerNames = List.of("pobi", "pobi");

            //when && then
            assertThatThrownBy(() -> Players.of(testPlayerNames, totalDeck))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NAME_UNIQUENESS_ERR.getMessage());
        }
    }

    @Test
    @DisplayName("사용자의 상태에 따라 Turn이 변경된다")
    void next_change_targetUser() {
        //given
        Players testPlayers = Players.of(TEST_PLAYER_NAMES, totalDeck);
        Player prevPlayer = testPlayers.findCurrentUser().get();
        prevPlayer = prevPlayer.stay();

        //when, then
        Assertions.assertNotEquals(prevPlayer, testPlayers.findCurrentUser());
    }

    //    @Test
//    @DisplayName("getDecksPerUser에서 잘 가져온다")
//    void getDecksPerUser_success() {
//        //given
//        List<String> testPlayerNames = List.of("pobi");
//        List<Card> expectPobiCards = List.of(
//                new Card(CardShape.스페이드, CardContents.A),
//                new Card(CardShape.스페이드, CardContents.TWO)
//        );
//
//        //when
//        Players players = Players.of(testPlayerNames, totalDeck);
//        Map<String, List<Card>> result = players.getDecksPerPlayer();
//
//        //then
//        assertEquals(expectPobiCards, result.get("pobi"));
//    }
//
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
