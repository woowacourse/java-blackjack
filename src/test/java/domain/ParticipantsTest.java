package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    Deck totalDeck;

    @BeforeEach
    void setUpTotalDeck() {
        CardCreationStrategy totalCardCreationStrategy = this::createSampleCards;
        totalDeck = Deck.createDeck(totalCardCreationStrategy);
    }

    @Test
    @DisplayName("생성 잘 한다")
    void of_good() {
        //given
        List<String> testPlayerNames = List.of("pobi", "terry", "rati", "gump");

        //when, then
        assertDoesNotThrow(
                () -> Participants.of(testPlayerNames, totalDeck)
        );
    }

    @Test
    @DisplayName("getDecksPerUser에서 잘 가져온다")
    void getDecksPerUser_success() {
        //given
        List<String> testPlayerNames = List.of("pobi");

        List<Card> expectDealerCards = List.of(
                new Card(CardShape.스페이드, CardContents.A),
                new Card(CardShape.스페이드, CardContents.TWO)
        );

        List<Card> expectPobiCards = List.of(
                new Card(CardShape.스페이드, CardContents.THREE),
                new Card(CardShape.스페이드, CardContents.FOUR)
        );

        //when
        Participants participants = Participants.of(testPlayerNames, totalDeck);
        Map<String, List<Card>> result = participants.getDecksPerUser();

        //then
        assertEquals(expectDealerCards, result.get("딜러"));
        assertEquals(expectPobiCards, result.get("pobi"));
    }

    private List<Card> createSampleCards() {
        CardShape[] shapes = CardShape.values();
        CardContents[] contents = CardContents.values();

        List<Card> sampleCards = new ArrayList<>();
        for (CardShape cardShape : shapes) {
            for (CardContents content : contents) {
                sampleCards.add(new Card(cardShape, content));
            }
        }

        return sampleCards;
    }

}