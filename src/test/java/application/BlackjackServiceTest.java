package application;

import domain.card.Card;
import domain.card.FixedDeck;

import java.util.List;
import java.util.Map;

import dto.GameResult;
import dto.MemberStatus;
import dto.RoundResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackServiceTest {

    private static final String pobiName = "pobi";

    private BlackjackService blackjackService;

    @BeforeEach
    void setUpTest() {
        List<Card> cards = List.of(
                Card.from("10", "클로버"),
                Card.from("9", "클로버"),
                Card.from("8", "클로버"),
                Card.from("7", "클로버"),
                Card.from("A", "클로버"),
                Card.from("6", "클로버")
        );
        Map<String, Integer> playerBets = Map.of(pobiName, 10_000);
        this.blackjackService = new BlackjackService(playerBets, new FixedDeck(cards));
    }

    @DisplayName("게임 초기화 시 플레이어와 딜러에게 각각 2장의 카드가 배분되어야 한다.")
    @Test
    void initializeGame_Always_DistributesTwoCardsToEach() {
        List<MemberStatus> statuses = blackjackService.getMemberStatuses();

        for (MemberStatus status : statuses) {
            Assertions.assertEquals(2, status.cards().size());
        }
    }

    @DisplayName("카드의 총합이 21보다 크면 포비는 Bust이다.")
    @Test
    void startOneRound_ScoreOver21_IsBust() {
        RoundResult roundResult = new RoundResult(List.of(), false);

        for (int i = 0; i < 2; i++) {
            roundResult = blackjackService.startOneRound(pobiName);
        }

        Assertions.assertTrue(roundResult.isBust());
    }

    @DisplayName("카드의 총합이 21보다 작으면 포비는 Bust가 아니다.")
    @Test
    void startOneRound_ScoreUnder21_IsNotBust() {
        Assertions.assertFalse(blackjackService.startOneRound(pobiName).isBust());
    }

    @DisplayName("플레이어가 차례를 마치면(Stay), 해당 플레이어의 상태는 종료(Finished)되어야 한다.")
    @Test
    void endPlayerRound_Always_ChangesStateToFinished() {
        blackjackService.endPlayerRound(pobiName);

        Assertions.assertTrue(blackjackService.isFinishedByName(pobiName));
    }

    @DisplayName("딜러가 17점 이상이면 카드를 더 뽑지 않고 상태를 Stay로 변경한다.")
    @Test
    void checkDealerDrawable_DealerScoreOver17_NoDrawAndStay() {
        boolean drawn = blackjackService.checkDealerDrawable();

        Assertions.assertFalse(drawn);
        MemberStatus dealerStatus = blackjackService.getMemberStatuses().getFirst();
        Assertions.assertEquals(2, dealerStatus.cards().size());
    }

    @DisplayName("딜러가 16점 이하이면 카드를 한 장 더 뽑고 상태를 Stay로 변경한다.")
    @Test
    void checkDealerDrawable_DealerScoreUnder16_DrawOnceAndStay() {
        List<Card> cards = List.of(
                Card.from("8", "클로버"), Card.from("7", "클로버"),
                Card.from("10", "클로버"), Card.from("10", "하트"),
                Card.from("A", "스페이드")
        );
        BlackjackService lowScoreService = new BlackjackService(Map.of(pobiName, 10_000), new FixedDeck(cards));

        boolean drawn = lowScoreService.checkDealerDrawable();

        Assertions.assertTrue(drawn);
        MemberStatus dealerStatus = lowScoreService.getMemberStatuses().getFirst();
        Assertions.assertEquals(3, dealerStatus.cards().size());
    }

    @DisplayName("최종 게임 결과(수익)가 올바르게 계산되어 DTO로 반환되어야 한다.")
    @Test
    void getGameResults_Always_ReturnCorrectProfits() {
        blackjackService.endPlayerRound(pobiName);
        blackjackService.checkDealerDrawable();

        List<GameResult> results = blackjackService.getGameResults();

        GameResult pobiResult = results.stream()
                .filter(result -> result.name().equals(pobiName))
                .findFirst()
                .orElseThrow();

        Assertions.assertEquals(-10_000, pobiResult.result());
    }
}
