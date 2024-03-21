package model.casino;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import controller.dto.BettingInfo;
import controller.dto.BettingInfos;
import controller.dto.DealerFaceUpResult;
import controller.dto.PlayerFaceUpResult;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import model.card.Card;
import model.card.Emblem;
import model.card.Number;
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
    @DisplayName("Player의 선택이 true라면 카지노가 해당 Player에게 추가 카드 한 장을 분배한다.")
    void distinctPlayerChoice_ShouldCallHitCardToPlayer_WhenChoiceIsTrue() {
        PlayerFaceUpResult initialPlayerFaceUpResult = casino.getNextPlayerFaceUpInfo();

        assertAll(() -> {
            assertEquals(2, initialPlayerFaceUpResult.cards()
                    .size());

            casino.distinctPlayerChoice(true);
            PlayerFaceUpResult finalPlayerFaceUpResult = casino.getNextPlayerFaceUpInfo();

            assertEquals(3, finalPlayerFaceUpResult.cards()
                    .size());
        });
    }

    @Test
    @DisplayName("Player의 선택이 false라면 카지노가 해당 Player의 turn을 종료한다.")
    void distinctPlayerChoice_ShouldCallTurnOverPlayer_WhenChoiceIsFalse() {
        PlayerFaceUpResult initialPlayerFaceUpResult = casino.getNextPlayerFaceUpInfo();
        casino.distinctPlayerChoice(false);
        List<PlayerFaceUpResult> finalPlayerFaceUpResults = casino.getPlayerFaceUpResult();

        assertAll(() -> {
            assertEquals(initialPlayerFaceUpResult.cards()
                    .size(), finalPlayerFaceUpResults.get(0)
                    .cards()
                    .size());
            assertFalse(casino.hasAvailablePlayer());
        });
    }

    @Test
    @DisplayName("딜러는 카드를 받아야 합니다.")
    void hitCardToDealer_ShouldHitCardToDealer_WhenCalled() {
        DealerFaceUpResult dealerFaceUpResult = casino.getDealerFaceUpResult();

        assertAll(() -> {
            assertEquals(2, dealerFaceUpResult.cards()
                    .size());

            casino.hitCardToDealer();

            assertEquals(3, dealerFaceUpResult.cards()
                    .size());
        });
    }

    @Test
    @DisplayName("플레이 가능한 플레이어가 있으면 True를 반환한다.")
    void hasAvailablePlayer_ShouldReturnTrue_WhenHasAvailablePlayer() {
        boolean hasAvailablePlayer = casino.hasAvailablePlayer();
        assertTrue(hasAvailablePlayer);
    }

    @Test
    @DisplayName("플레이 가능한 플레이어가 없으면 False를 반환한다.")
    void hasAvailablePlayer_ShouldReturnBoolean_WhenNotAvailablePlayerExist() {
        casino.distinctPlayerChoice(false);
        boolean hasAvailablePlayer = casino.hasAvailablePlayer();
        assertFalse(hasAvailablePlayer);
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 있을 때 true를 반환한다.")
    void isDealerHitAllowed_ShouldReturnTrue_WhenDealerHandSmallerThan17() {
        boolean isDealerHitAllowed = casino.isDealerHitAllowed();

        // Then
        assertTrue(isDealerHitAllowed);
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 없을 때 false를 반환한다.")
    void isDealerHitAllowed_ShouldReturnFalse_WhenDealerHandBiggerThan17() {
        // Given
        BettingInfos bettingInfos = BettingInfos.from(List.of(new BettingInfo(new Name("프람"), 10000L)));
        Casino casinoDispenseTenCards = new Casino(bettingInfos, new TenCardShuffleMachine());

        // When
        boolean isDealerHitAllowed = casinoDispenseTenCards.isDealerHitAllowed();

        // Then
        assertFalse(isDealerHitAllowed);
    }

    @Test
    @DisplayName("플레이어의 배팅 결과를 계산합니다.")
    void calculatePlayerBettingResults_ShouldReturnBettingInfos_WhenCalled() {
        casino.distinctPlayerChoice(false);
        casino.aggregateBettingResult();

        BettingInfos result = casino.calculatePlayerBettingResults();

        assertEquals(10000L, result.getBettingInfos().get(0).bettingAmount());
    }

    @Test
    @DisplayName("모든 플레이어의 턴이 종료 된 후에 딜러의 수익을 계산한다")
    void calculateDealerEarningResults_ShouldReturnLongValue_WhenCalled() {
        casino.distinctPlayerChoice(false);
        casino.aggregateBettingResult();

        long result = casino.calculateDealerEarningResults();

        assertEquals(-10000L, result);
    }

    @Test
    @DisplayName("Player의 정보(이름, 카드 패, 핸드)를 포함하는 데이터 전송 객체를 반환한다. ")
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

    static class TenCardShuffleMachine implements CardShuffleMachine {
        @Override
        public ArrayList<Card> generateAllCards() {
            return new ArrayList<>(List.of(Card.from(Number.TEN, Emblem.SPADE), Card.from(Number.TEN, Emblem.SPADE),
                    Card.from(Number.TEN, Emblem.SPADE), Card.from(Number.TEN, Emblem.SPADE),
                    Card.from(Number.TEN, Emblem.SPADE), Card.from(Number.TEN, Emblem.SPADE),
                    Card.from(Number.TEN, Emblem.SPADE), Card.from(Number.TEN, Emblem.SPADE),
                    Card.from(Number.TEN, Emblem.SPADE), Card.from(Number.TEN, Emblem.SPADE),
                    Card.from(Number.TEN, Emblem.SPADE), Card.from(Number.TEN, Emblem.SPADE),
                    Card.from(Number.TEN, Emblem.SPADE)));
        }

        @Override
        public Queue<Card> shuffleCardDeck() {
            return new ArrayDeque<>(generateAllCards());
        }
    }
}
