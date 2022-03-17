package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatisticTest {

    @Test
    @DisplayName("딜러가 21을 초과했을 경우 플레이어 결과 경우의 수 테스트")
    void dealerIsBurst() {
        Dealer dealer = Dealer.of();
        Gambler gambler1 = Gambler.of(Name.of("pobi"));
        Gambler gambler2 = Gambler.of(Name.of("jason"));
        Gamblers gamblers = Gamblers.of(List.of(gambler1, gambler2));

        dealer.addCard(Card.of(Denomination.EIGHT, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        gambler1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));
        gambler1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));
        gambler1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));
        gambler1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));
        gambler1.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));

        gambler2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler2.addCard(Card.of(Denomination.TWO, Symbol.CLOVER));

        Statistic statistic = Statistic.of(dealer, gamblers);

        assertThat(statistic.getGameResultByGambler(gambler1).equals(GameResult.WIN) &&
            statistic.getGameResultByGambler(gambler2).equals(GameResult.LOSE))
            .isTrue();
    }

    @Test
    @DisplayName("딜러, 플레이어 모두 버스트")
    void dealerAndAllPlayerBurst() {
        Dealer dealer = Dealer.of();
        Gambler gambler1 = Gambler.of(Name.of("pobi"));
        Gambler gambler2 = Gambler.of(Name.of("jason"));
        Gamblers gamblers = Gamblers.of(List.of(gambler1, gambler2));

        // 30
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        // 30
        gambler1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        // 30
        gambler2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler2.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        Statistic statistic = Statistic.of(dealer, gamblers);

        assertThat(statistic.getGameResultByGambler(gambler1).equals(GameResult.LOSE) &&
            statistic.getGameResultByGambler(gambler2).equals(GameResult.LOSE))
            .isTrue();
    }

    @Test
    @DisplayName("딜러 21 이하일 경우 플레이어 경우의 수 테스트")
    void dealerUnderMaxPoint() {
        Dealer dealer = Dealer.of();
        Gambler gambler1 = Gambler.of(Name.of("pobi"));
        Gambler gambler2 = Gambler.of(Name.of("jason"));
        Gambler gambler3 = Gambler.of(Name.of("bani"));
        Gambler gambler4 = Gambler.of(Name.of("hunch"));
        Gamblers gamblers = Gamblers.of(List.of(gambler1, gambler2, gambler3, gambler4));

        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        dealer.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        //초과
        gambler1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler1.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        //딜러보다 작게
        gambler2.addCard(Card.of(Denomination.FIVE, Symbol.CLOVER));
        gambler2.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));

        //딜러와 동일
        gambler3.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler3.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));

        //딜러보다 크게
        gambler4.addCard(Card.of(Denomination.JACK, Symbol.CLOVER));
        gambler4.addCard(Card.of(Denomination.ACE, Symbol.CLOVER));

        Statistic statistic = Statistic.of(dealer, gamblers);

        assertThat(statistic.getGameResultByGambler(gambler1).equals(GameResult.LOSE) &&
            statistic.getGameResultByGambler(gambler2).equals(GameResult.LOSE) &&
            statistic.getGameResultByGambler(gambler3).equals(GameResult.DRAW) &&
            statistic.getGameResultByGambler(gambler4).equals(GameResult.WIN))
            .isTrue();
    }
}
