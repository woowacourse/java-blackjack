package blackjack.participant;

import blackjack.ConstantFixture;
import blackjack.GameRule;
import blackjack.result.Betting;
import blackjack.result.GameStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameParticipantsTest {

    @Test
    @DisplayName("모든 게임 참여자들에게 초기 카드 딜링을 통해서 카드를 {초기 카드 개수}장씩 나눠줄 수 있다")
    void canDealInitialCard() {
        // given
        int initialDealingCardCount = GameRule.INITIAL_DEALING_CARD_COUNT.getValue();

        List<Player> players = List.of(
                GameParticipantFixture.createPlayer("강산"),
                GameParticipantFixture.createPlayer("재중"),
                GameParticipantFixture.createPlayer("파프"));
        Dealer dealer = GameParticipantFixture.createDealer();

        GameParticipants participants = GameParticipants.of(players, dealer);

        // when
        participants.dealInitialCards();

        // then
        assertAll(
                () -> {
                    players.forEach(player ->
                            assertThat(player.getHand().size()).isEqualTo(initialDealingCardCount));

                    assertThat(dealer.getHand().size()).isEqualTo(initialDealingCardCount);
                }
        );
    }

    @Test
    @DisplayName("모든 게임 참여자들이 히트를 진행할 수 있다")
    void canHit() {
        // given
        Function<Player, Boolean> hitDecisionAlwaysReturnTrue = player -> true;
        Function<Player, Boolean> hitDecisionAlwaysReturnFalse = player -> false;

        List<Player> players = List.of(
                GameParticipantFixture.createPlayer("겁쟁이1", Betting.from(10000), hitDecisionAlwaysReturnFalse),
                GameParticipantFixture.createPlayer("겁쟁이2", Betting.from(10000), hitDecisionAlwaysReturnFalse),
                GameParticipantFixture.createPlayer("강산", Betting.from(10000), hitDecisionAlwaysReturnTrue),
                GameParticipantFixture.createPlayer("재중", Betting.from(10000), hitDecisionAlwaysReturnTrue)
        );

        Dealer dealer = GameParticipantFixture.createDealer(players.size());
        int sumOfDealerCards = dealer.calculateSumOfCards();
        int dealerHitThresholdPoint = ConstantFixture.getDealerHitThresholdPoint("테스트 전용");

        GameParticipants participants = GameParticipants.of(players, dealer);

        participants.dealInitialCards();

        List<Player> chickens = participants.getPlayers().stream()
                .filter(participant -> participant.getNickname().getValue().contains("겁쟁이"))
                .toList();

        List<Player> heroes = participants.getPlayers().stream()
                .filter(participant -> !participant.getNickname().getValue().contains("겁쟁이"))
                .toList();

        // when
        participants.executeHitPhase();

        // then
        assertAll(() -> {
            for (GameParticipant chicken : chickens) {
                assertThat(chicken.isBust()).isFalse();
            }

            for (GameParticipant hero : heroes) {
                assertThat(hero.isBust()).isTrue();
            }

            if (sumOfDealerCards <= dealerHitThresholdPoint) {
                assertThat(dealer.calculateSumOfCards() > sumOfDealerCards).isTrue();
            }
        });
    }

    @Test
    @DisplayName("모든 플레이어의 게임 결과를 계산할 수 있다")
    void canCalculateGameStatistics() {
        // given
        Player p1 = GameParticipantFixture.createPlayer("내일점심", Betting.from(10000), 16);
        Player p2 = GameParticipantFixture.createPlayer("애슐리", Betting.from(10000), 17);
        Player p3 = GameParticipantFixture.createPlayer("강산", Betting.from(10000), 18);
        Player p4 = GameParticipantFixture.createPlayer("재중", Betting.from(10000), 19);
        Player p5 = GameParticipantFixture.createPlayer("고양이", Betting.from(10000), 22);
        List<Player> players = List.of(p1, p2, p3, p4, p5);

        Dealer dealer = GameParticipantFixture.createDealer(players.size(), 18);
        GameParticipants participants = GameParticipants.of(players, dealer);

//        // when
        GameStatistics gameStatistics = participants.calculateGameStatistics();

        // then
        assertThat(gameStatistics.getProfit(p1).getAmount()).isEqualTo(-10000);
        assertThat(gameStatistics.getProfit(p2).getAmount()).isEqualTo(-10000);
        assertThat(gameStatistics.getProfit(p3).getAmount()).isEqualTo(0);
        assertThat(gameStatistics.getProfit(p4).getAmount()).isEqualTo(10000);
        assertThat(gameStatistics.getProfit(p5).getAmount()).isEqualTo(-10000);
        assertThat(gameStatistics.getDealerProfit().getAmount()).isEqualTo((-10000 - 10000 + 0 + 10000 - 10000) * -1); // 모든 플레이어 최종 수익의 역
    }
}
