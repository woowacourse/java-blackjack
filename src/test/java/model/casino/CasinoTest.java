package model.casino;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.dto.BettingInfo;
import controller.dto.BettingInfos;
import controller.dto.DealerFaceUpResult;
import controller.dto.PlayerFaceUpResult;
import java.util.List;
import model.participant.FixedCardShuffleMachine;
import model.participant.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CasinoTest {
    Casino casino;

    @BeforeEach
    void setUp() {
        // Given
        BettingInfos bettingInfos = BettingInfos.from(List.of(new BettingInfo(new Name("프람"), 10000L)));
        // When
        casino = new Casino(bettingInfos, new FixedCardShuffleMachine());
    }

    @Test
    @DisplayName("딜러의 정보(이름, 카드 패, 핸드)를 포함하는 데이터 전송 객체를 반환한다. ")
    void getPlayerFaceUpResult_ShouldReturnPlayerCardInfo_WhenCalled() {

        // Then
        PlayerFaceUpResult result = casino.getNextPlayerFaceUpInfo();
        assertAll(() -> {
            assertEquals("프람", result.getPartipantNameAsString());
            assertEquals(List.of("A스페이드", "2스페이드"), result.getCardsAsStrings());
            assertEquals(13, result.hand());
        });
    }

    @Test
    @DisplayName("딜러의 정보(이름, 카드 패, 핸드)를 포함하는 데이터 전송 객체를 반환한다. ")
    void getDealerFaceUpResult_ShouldReturnDealerCardInfo_WhenCalled() {

        // Then
        DealerFaceUpResult result = casino.getDealerFaceUpResult();
        assertAll(() -> {
            assertEquals(List.of("3스페이드", "4스페이드"), result.getCardsAsStrings());
            assertEquals(7, result.hand());
        });
    }
}
