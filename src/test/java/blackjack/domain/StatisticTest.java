package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Name;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class StatisticTest {

    @Test
    void 딜러_21초과_플레이어승() {
        Dealer dealer = Dealer.of();
        Player player1 = Player.of(Name.of("pobi"));
        Player player2 = Player.of(Name.of("jason"));
        Players players = Players.of(List.of(player1, player2));

        dealer.addCard(Card.of(Denomination.EIGHT, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        player1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));

        player2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player2.addCard(Card.of(Denomination.TWO, Symbol.CLOVER));

        Statistic statistic = Statistic.of(dealer, players);
        statistic.calculate();
        assertThat(player1.getResult().equals(GameResult.WIN) &&
                player2.getResult().equals(GameResult.LOSE))
                .isTrue();
    }

    @Test
    void 딜러_21초과_딜러승() {
        Dealer dealer = Dealer.of();
        Player player1 = Player.of(Name.of("pobi"));
        Player player2 = Player.of(Name.of("jason"));
        Players players = Players.of(List.of(player1, player2));

        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        player1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        player2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        Statistic statistic = Statistic.of(dealer, players);
        statistic.calculate();
        assertThat(player1.getResult().equals(GameResult.LOSE) &&
                player2.getResult().equals(GameResult.LOSE))
                .isTrue();
    }

    @Test
    void 딜러_21이하_딜러승() {
        Dealer dealer = Dealer.of();
        Player player1 = Player.of(Name.of("pobi"));
        Player player2 = Player.of(Name.of("jason"));
        Player player3 = Player.of(Name.of("bani"));
        Player player4 = Player.of(Name.of("hunch"));
        Players players = Players.of(List.of(player1, player2, player3, player4));

        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        //초과
        player1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        //딜러보다 크게
        player4.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player4.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));

        //딜러보다 작게
        player2.addCard(Card.of(Denomination.FIVE, Symbol.CLOVER));
        player2.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));

        //딜러와 동일
        player3.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player3.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        Statistic statistic = Statistic.of(dealer, players);
        statistic.calculate();
        assertThat(player1.getResult().equals(GameResult.LOSE) &&
                player2.getResult().equals(GameResult.LOSE) &&
                player4.getResult().equals(GameResult.WIN) &&
                player3.getResult().equals(GameResult.DRAW))
                .isTrue();
    }
}