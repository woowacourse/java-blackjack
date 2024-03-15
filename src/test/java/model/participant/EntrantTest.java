package model.participant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.card.Card;
import model.card.Emblem;
import model.card.Number;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.dto.DealerFaceUpResult;
import service.dto.FaceUpResult;

class EntrantTest {
    Entrant entrant;

    @BeforeEach
    void setUp() {
        // Given
        Card card1 = Card.from(Number.ACE, Emblem.SPADE);
        Card card2 = Card.from(Number.TWO, Emblem.SPADE);
        Names names = new Names(List.of("프람"));
        entrant = generateEntrant(names, card1, card2);

        // When

        entrant.hitDealer(Card.from(Number.THREE, Emblem.SPADE));
        entrant.hitPlayer(Card.from(Number.THREE, Emblem.HEART));
    }

    private Entrant generateEntrant(Names names, Card card1, Card card2) {
        List<Player> players = names.getPlayerNames()
                .stream()
                .map(name -> new Player(name, card1, card2))
                .toList();
        Dealer dealer = new Dealer(card1, card2);
        return new Entrant(dealer, players);
    }

    @Test
    @DisplayName("딜러의 정보(이름, 카드 패, 핸드)를 포함하는 데이터 전송 객체를 반환한다. ")
    void getDealerFaceUpResult_ShouldReturnDealerCardInfo_WhenCalled() {

        // Then
        DealerFaceUpResult result = entrant.getDealerFaceUpResult();
        assertAll(() -> {
            assertEquals("딜러", result.getPartipantNameAsString());
            assertEquals(List.of("A스페이드", "2스페이드", "3스페이드"), result.getCardsAsStrings());
            assertEquals(16, result.hand());
        });
    }

    @Test
    @DisplayName("딜러의 정보(이름, 카드 패, 핸드)를 포함하는 데이터 전송 객체를 반환한다. ")
    void getPlayerFaceUpResult_ShouldReturnPlayerCardInfo_WhenCalled() {

        // Then
        List<FaceUpResult> result = entrant.getPlayerFaceUpResults();
        assertAll(() -> {
            assertEquals("프람", result.get(0)
                    .getPartipantNameAsString());
            assertEquals(List.of("A스페이드", "2스페이드", "3하트"), result.get(0)
                    .getCardsAsStrings());
            assertEquals(16, result.get(0)
                    .hand());
        });
    }

}
