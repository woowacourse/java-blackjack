package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.human.Human;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class StatisticTest {
    private final Player player1 = Player.of("pobi");
    private final Player player2 = Player.of("jason");
    private final Player player3 = Player.of("bani");
    private final Player player4 = Player.of("hunch");

    void addCardList(Human human, List<String> cards) {
        for (String card : cards) {
            human.addCard(Card.of(Denomination.of(card), Suit.CLOVER));
        }
    }

    @Nested
    @SuppressWarnings("NonAsciiCharacters")
    class 딜러_21초과_플레이어승 {
        Statistic statistic;

        @BeforeEach
        void setup() {
            addCardList(player1, List.of("A", "A", "A", "A", "A"));
            addCardList(player2, List.of("10", "10", "2"));
            Players players = Players.of(List.of(player1, player2));

            Table table = Table.of(players);
            addCardList(table.getDealer(), List.of("8", "10", "10"));

            statistic = Statistic.of();
            statistic.calculate(table);
        }

        @Test
        void playerResult() {
            assertThat(statistic.getPlayersResult())
                    .containsEntry(player1, Result.WIN)
                    .containsEntry(player2, Result.LOSE);
        }

        @Test
        void dealerResult() {
            assertThat(statistic.getDealerResult())
                    .isEqualTo(Map.of(
                                    Result.LOSE, 1,
                                    Result.WIN, 1,
                                    Result.DRAW, 0
                            )
                    );
        }
    }

    @Nested
    @SuppressWarnings("NonAsciiCharacters")
    class 딜러_21초과_딜러승 {
        Statistic statistic;

        @BeforeEach
        void setup() {
            addCardList(player1, List.of("10", "10", "10"));
            addCardList(player2, List.of("10", "10", "10"));
            Players players = Players.of(List.of(player1, player2));

            Table table = Table.of(players);
            addCardList(table.getDealer(), List.of("10", "10", "10", "10"));

            statistic = Statistic.of();
            statistic.calculate(table);
        }

        @Test
        void playerResult() {
            assertThat(statistic.getPlayersResult())
                    .containsEntry(player1, Result.LOSE)
                    .containsEntry(player2, Result.LOSE);
        }

        @Test
        void dealerResult() {
            assertThat(statistic.getDealerResult())
                    .isEqualTo(Map.of(
                                    Result.LOSE, 0,
                                    Result.WIN, 2,
                                    Result.DRAW, 0
                            )
                    );
        }
    }

    @Nested
    class 딜러_21이하_딜러승 {
        Statistic statistic;

        @BeforeEach
        void setup() {
            addCardList(player1, List.of("10", "10", "10"));
            addCardList(player2, List.of("5", "A"));
            addCardList(player3, List.of("10", "10"));
            addCardList(player4, List.of("10", "10", "A"));
            Players players = Players.of(List.of(player1, player2, player3, player4));

            Table table = Table.of(players);
            addCardList(table.getDealer(), List.of("10", "10"));
            statistic = Statistic.of();
            statistic.calculate(table);
        }

        @Test
        void playerResult() {
            assertThat(statistic.getPlayersResult())
                    .containsEntry(player1, Result.LOSE)
                    .containsEntry(player2, Result.LOSE)
                    .containsEntry(player3, Result.DRAW)
                    .containsEntry(player4, Result.WIN);
        }

        @Test
        void dealerResult() {
            assertThat(statistic.getDealerResult())
                    .isEqualTo(Map.of(
                                    Result.LOSE, 1,
                                    Result.WIN, 2,
                                    Result.DRAW, 1
                            )
                    );
        }
    }
}