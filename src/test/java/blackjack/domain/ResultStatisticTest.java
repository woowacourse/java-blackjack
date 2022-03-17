package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import blackjack.domain.participant.human.Human;
import blackjack.domain.participant.human.Player;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Result;
import blackjack.domain.result.ResultStatistic;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ResultStatisticTest {
    private final Player player1 = Player.from("pobi");
    private final Player player2 = Player.from("jason");
    private final Player player3 = Player.from("bani");
    private final Player player4 = Player.from("hunch");

    void addCardList(Human human, List<String> cards) {
        for (String card : cards) {
            human.addCard(Card.of(Denomination.fromInitial(card), Suit.CLOVER));
        }
    }

    @Nested
    @DisplayName("딜러 21초과 플레이어승 결과 테스트")
    class DealerOverPlayerUnderTest {
        ResultStatistic resultStatistic;

        @BeforeEach
        void setup() {
            // given
            addCardList(player1, List.of("A", "A", "A", "A", "A"));
            addCardList(player2, List.of("10", "10", "2"));
            Players players = Players.fromRawValue(List.of(player1, player2));

            Participant participant = Participant.from(players);
            addCardList(participant.getDealer(), List.of("8", "10", "10"));

            // when
            resultStatistic = ResultStatistic.from(participant);
        }

        @Test
        void dealerResultTest() {
            // then
            assertThat(resultStatistic.getDealerResults())
                    .isEqualTo(Map.of(
                                    Result.LOSE, 1,
                                    Result.WIN, 1,
                                    Result.DRAW, 0
                            )
                    );
        }

        @Test
        void playerResultTest() {
            // then
            assertThat(resultStatistic.getPlayersResult())
                    .containsEntry(player1.getName(), Result.WIN)
                    .containsEntry(player2.getName(), Result.LOSE);
        }
    }

    @Nested
    @DisplayName("딜러 21초과 딜러 승리 결과 테스트")
    class DealerOverPlayerOverTest {
        ResultStatistic resultStatistic;

        @BeforeEach
        void setup() {
            // given
            addCardList(player1, List.of("10", "10", "10"));
            addCardList(player2, List.of("10", "10", "10"));
            Players players = Players.fromRawValue(List.of(player1, player2));

            Participant participant = Participant.from(players);
            addCardList(participant.getDealer(), List.of("10", "10", "10", "10"));

            //when
            resultStatistic = ResultStatistic.from(participant);
        }

        @Test
        void playerResultTest() {
            // then
            assertThat(resultStatistic.getPlayersResult())
                    .containsEntry(player1.getName(), Result.LOSE)
                    .containsEntry(player2.getName(), Result.LOSE);
        }

        @Test
        void dealerResultTest() {
            // then
            assertThat(resultStatistic.getDealerResults())
                    .isEqualTo(Map.of(
                                    Result.LOSE, 0,
                                    Result.WIN, 2,
                                    Result.DRAW, 0
                            )
                    );
        }
    }

    @Nested
    @DisplayName("딜러 21이하 결과 테스트")
    class DealerUnderTest {
        ResultStatistic resultStatistic;

        @BeforeEach
        void setup() {
            // given
            addCardList(player1, List.of("10", "10", "10"));
            addCardList(player2, List.of("5", "A"));
            addCardList(player3, List.of("10", "10"));
            addCardList(player4, List.of("10", "10", "A"));
            Players players = Players.fromRawValue(List.of(player1, player2, player3, player4));

            Participant participant = Participant.from(players);
            addCardList(participant.getDealer(), List.of("10", "10"));

            // when
            resultStatistic = ResultStatistic.from(participant);
        }

        @Test
        void playerResultTest() {
            // then
            assertThat(resultStatistic.getPlayersResult())
                    .containsEntry(player1.getName(), Result.LOSE)
                    .containsEntry(player2.getName(), Result.LOSE)
                    .containsEntry(player3.getName(), Result.DRAW)
                    .containsEntry(player4.getName(), Result.WIN);
        }

        @Test
        void dealerResultTest() {
            // then
            assertThat(resultStatistic.getDealerResults())
                    .isEqualTo(Map.of(
                                    Result.LOSE, 1,
                                    Result.WIN, 2,
                                    Result.DRAW, 1
                            )
                    );
        }
    }
}
