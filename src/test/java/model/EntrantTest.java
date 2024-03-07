package model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EntrantTest {
    Entrant entrant;

    @BeforeEach
    void setUp() {
        // Given
        Names names = new Names(List.of("프람", "도비"));
        entrant = new Entrant(names);

        // When
        entrant.hitCardToDealer(new Card(Number.ACE, Emblem.SPADE));
        entrant.hitCardToDealer(new Card(Number.TWO, Emblem.SPADE));
        entrant.hitCardToDealer(new Card(Number.THREE, Emblem.SPADE));

        entrant.getPlayers()
                .get(0)
                .hitCard(new Card(Number.ACE, Emblem.SPADE));
        entrant.getPlayers()
                .get(0)
                .hitCard(new Card(Number.TWO, Emblem.SPADE));
        entrant.getPlayers()
                .get(0)
                .hitCard(new Card(Number.THREE, Emblem.SPADE));

        entrant.getPlayers()
                .get(1)
                .hitCard(new Card(Number.ACE, Emblem.SPADE));
        entrant.getPlayers()
                .get(1)
                .hitCard(new Card(Number.TWO, Emblem.SPADE));
        entrant.getPlayers()
                .get(1)
                .hitCard(new Card(Number.THREE, Emblem.SPADE));
    }

    @Test
    @DisplayName("딜러의 정보(이름, 카드 패, 핸드)를 포함하는 데이터 전송 객체를 반환한다. ")
    void getDealerFaceUpResult_ShouldReturnDealerCardInfo_WhenCalled() {

        // Then
        IndividualFaceUpResult result = entrant.getDealerFaceUpResult();
        assertAll(() -> {
            assertEquals("딜러", result.getPartipantNameAsString());
            assertEquals(List.of("A스페이드", "2스페이드", "3스페이드"), result.getCardsAsStrings());
            assertEquals(16, result.hand());
        });
    }
}
