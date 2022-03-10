package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Human;
import blackjack.domain.human.Name;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class StatisticTest {
    private final Dealer dealer = Dealer.of();
    private final Player player1 = Player.of(Name.of("pobi"));
    private final Player player2 = Player.of(Name.of("jason"));
    private final Player player3 = Player.of(Name.of("bani"));
    private final Player player4 = Player.of(Name.of("hunch"));

    void addCardList(Human human, List<String> cards) {
        for (String card : cards) {
            human.addCard(Card.of(Denomination.of(card), Symbol.CLOVER));
        }
    }

    @Test
    void 딜러_21초과_플레이어승() {
        addCardList(dealer, List.of("8","10","10"));
        addCardList(player1, List.of("A","A","A","A","A"));
        addCardList(player2, List.of("10","10","2"));
        Players players = Players.of(List.of(player1, player2));

        Statistic statistic = Statistic.of(dealer);
        statistic.calculate(players);
        assertThat(player1.getResult().equals(GameResult.WIN) &&
                player2.getResult().equals(GameResult.LOSE))
                .isTrue();

        assertThat(statistic.getDealerWinState())
                .isEqualTo(Map.of(
                                GameResult.LOSE, 1,
                                GameResult.WIN, 1,
                                GameResult.DRAW, 0
                        )
                );
    }

    @Test
    void 딜러_21초과_딜러승() {
        addCardList(dealer, List.of("10","10","10","10"));
        addCardList(player1, List.of("10","10","10"));
        addCardList(player2, List.of("10","10","10"));
        Players players = Players.of(List.of(player1, player2));

        Statistic statistic = Statistic.of(dealer);
        statistic.calculate(players);
        assertThat(player1.getResult().equals(GameResult.LOSE) &&
                player2.getResult().equals(GameResult.LOSE))
                .isTrue();

        assertThat(statistic.getDealerWinState())
                .isEqualTo(Map.of(
                                GameResult.LOSE, 0,
                                GameResult.WIN, 2,
                                GameResult.DRAW, 0
                        )
                );
    }

    @Test
    void 딜러_21이하_딜러승() {
        addCardList(dealer, List.of("10","10"));
        addCardList(player1, List.of("10","10","10"));
        addCardList(player2, List.of("5","A"));
        addCardList(player3, List.of("10","10"));
        addCardList(player4, List.of("10","10","A"));
        Players players = Players.of(List.of(player1, player2, player3, player4));

        Statistic statistic = Statistic.of(dealer);
        statistic.calculate(players);
        assertThat(player1.getResult().equals(GameResult.LOSE) &&
                player2.getResult().equals(GameResult.LOSE) &&
                player3.getResult().equals(GameResult.DRAW) &&
                player4.getResult().equals(GameResult.WIN))
                .isTrue();

        assertThat(statistic.getDealerWinState())
                .isEqualTo(Map.of(
                                GameResult.LOSE, 1,
                                GameResult.WIN, 2,
                                GameResult.DRAW, 1
                        )
                );
    }
}