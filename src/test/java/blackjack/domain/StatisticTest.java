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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatisticTest {

    @Test
    @DisplayName("딜러가 21을 초과했을 경우 플레이어 결과 경우의 수 테스트")
    void dealerIsBurst() {
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

        assertThat(statistic.getGameResultByPlayer(player1).equals(GameResult.WIN) &&
            statistic.getGameResultByPlayer(player2).equals(GameResult.LOSE))
            .isTrue();
    }

    @Test
    @DisplayName("딜러, 플레이어 모두 버스트")
    void dealerAndAllPlayerBurst() {
        Dealer dealer = Dealer.of();
        Player player1 = Player.of(Name.of("pobi"));
        Player player2 = Player.of(Name.of("jason"));
        Players players = Players.of(List.of(player1, player2));

        // 30
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        // 30
        player1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        // 30
        player2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        Statistic statistic = Statistic.of(dealer, players);

        assertThat(statistic.getGameResultByPlayer(player1).equals(GameResult.LOSE) &&
            statistic.getGameResultByPlayer(player2).equals(GameResult.LOSE))
            .isTrue();
    }

    @Test
    @DisplayName("딜러 21 이하일 경우 플레이어 경우의 수 테스트")
    void dealerUnderMaxPoint() {
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

        //딜러보다 작게
        player2.addCard(Card.of(Denomination.FIVE, Symbol.CLOVER));
        player2.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));

        //딜러와 동일
        player3.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player3.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        //딜러보다 크게
        player4.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        player4.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));

        Statistic statistic = Statistic.of(dealer, players);

        assertThat(statistic.getGameResultByPlayer(player1).equals(GameResult.LOSE) &&
            statistic.getGameResultByPlayer(player2).equals(GameResult.LOSE) &&
            statistic.getGameResultByPlayer(player3).equals(GameResult.DRAW) &&
            statistic.getGameResultByPlayer(player4).equals(GameResult.WIN))
            .isTrue();
    }
}
