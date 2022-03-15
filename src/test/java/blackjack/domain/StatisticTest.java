package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.element.Denomination;
import blackjack.domain.card.element.Suit;
import blackjack.domain.human.Human;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StatisticTest {
    private final Player player1 = Player.from("pobi");
    private final Player player2 = Player.from("jason");
    private final Player player3 = Player.from("bani");
    private final Player player4 = Player.from("hunch");
    
    void addCardList(Human human, List<String> cards) {
        for (String card : cards) {
            human.addCard(Card.of(Denomination.valueof(card), Suit.CLOVER));
        }
    }
    
    @Nested
    @DisplayName("딜러_21초과_플레이어승")
    class DealerOverPlayerUnderTest {
        Statistic statistic;
        
        @BeforeEach
        void setup() {
            // given
            addCardList(player1, List.of("A", "A", "A", "A", "A"));
            addCardList(player2, List.of("10", "10", "2"));
            Players players = Players.from(List.of(player1, player2));
            
            BlackjackTable blackjackTable = BlackjackTable.from(players);
            addCardList(blackjackTable.getDealer(), List.of("8", "10", "10"));
            
            // when
            statistic = Statistic.from(blackjackTable);
        }
        
        @Test
        void playerResultTest() {
            // then
            assertThat(statistic.getPlayersResult())
                    .containsEntry(player1, Result.WIN)
                    .containsEntry(player2, Result.LOSE);
        }
        
        @Test
        void dealerResultTest() {
            // then
            assertThat(statistic.getDealerResults()).isEqualTo(Map.of(
                            Result.LOSE, 1,
                            Result.WIN, 1,
                            Result.DRAW, 0
                    )
            );
        }
    }
    
    @Nested
    @DisplayName("딜러_21초과_딜러승")
    class DealerOverPlayerOverTest {
        Statistic statistic;
        
        @BeforeEach
        void setup() {
            // given
            addCardList(player1, List.of("10", "10", "10"));
            addCardList(player2, List.of("10", "10", "10"));
            Players players = Players.from(List.of(player1, player2));
            
            BlackjackTable blackjackTable = BlackjackTable.from(players);
            addCardList(blackjackTable.getDealer(), List.of("10", "10", "10", "10"));
            
            //when
            statistic = Statistic.from(blackjackTable);
        }
        
        @Test
        void playerResultTest() {
            // then
            assertThat(statistic.getPlayersResult())
                    .containsEntry(player1, Result.LOSE)
                    .containsEntry(player2, Result.LOSE);
        }
        
        @Test
        void dealerResultTest() {
            // then
            assertThat(statistic.getDealerResults()).isEqualTo(Map.of(
                            Result.LOSE, 0,
                            Result.WIN, 2,
                            Result.DRAW, 0
                    )
            );
        }
    }
    
    @Nested
    @DisplayName("딜러_21이하_테스트")
    class DealerUnderTest {
        Statistic statistic;
        
        @BeforeEach
        void setup() {
            // given
            addCardList(player1, List.of("10", "10", "10"));
            addCardList(player2, List.of("5", "A"));
            addCardList(player3, List.of("10", "10"));
            addCardList(player4, List.of("10", "10", "A"));
            Players players = Players.from(List.of(player1, player2, player3, player4));
            
            BlackjackTable blackjackTable = BlackjackTable.from(players);
            addCardList(blackjackTable.getDealer(), List.of("10", "10"));
            
            // when
            statistic = Statistic.from(blackjackTable);
        }
        
        @Test
        void playerResultTest() {
            // then
            assertThat(statistic.getPlayersResult())
                    .containsEntry(player1, Result.LOSE)
                    .containsEntry(player2, Result.LOSE)
                    .containsEntry(player3, Result.DRAW)
                    .containsEntry(player4, Result.WIN);
        }
        
        @Test
        void dealerResultTest() {
            // then
            assertThat(statistic.getDealerResults()).isEqualTo(Map.of(
                            Result.LOSE, 1,
                            Result.WIN, 2,
                            Result.DRAW, 1
                    )
            );
        }
    }
}
