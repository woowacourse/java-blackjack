package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Deck;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    //@formatter:off
    /**
     * 카드 저장 순서 Ace카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장 2카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장 ... KING카드 HEART,
     * SPADE, CLOVER, DIAMOND 순서대로 4장
     * <p>
     * 카드 pop 순서는 카드 저장 순서의 역순이다.
     */
    //@formatter:on
    @DisplayName("딜러의 최종 수익을 알려준다.")
    @Test
    void informDealerOutcome() {
        final TestDeckFactory testDeckFactory = new TestDeckFactory();
        final Deck deck = testDeckFactory.create();
        final Map<String, Double> bettings = new LinkedHashMap<>();
        bettings.put("pobi", (double) 10000);
        final Players players = Players.from(bettings);
        final Dealer dealer = Dealer.from(deck);
        final GameBoard gameBoard = new GameBoard(dealer, players);
        gameBoard.drawInitialPlayersHand();
        gameBoard.drawInitialDealerHand();

        final Money dealerProfit = gameBoard.getDealerProfit();

        assertThat(dealerProfit.value()).isEqualTo(0);
    }

    @DisplayName("모든 플레이어의 최종 수익을 알려준다.")
    @Test
    void informPlayersOutcome() {
        final TestDeckFactory testDeckFactory = new TestDeckFactory();
        final Deck deck = testDeckFactory.create();
        final Map<String, Double> bettings = new LinkedHashMap<>();
        bettings.put("pobi", (double) 10000);
        bettings.put("jason", (double) 20000);
        final Players players = Players.from(bettings);
        final Dealer dealer = Dealer.from(deck);
        final GameBoard gameBoard = new GameBoard(dealer, players);
        gameBoard.drawInitialDealerHand();
        gameBoard.drawInitialPlayersHand();

        final Map<Name, Money> playerProfits = gameBoard.getPlayerProfits();

        assertAll(
                () -> assertThat(playerProfits.size()).isEqualTo(players.getPlayers().size()),
                () -> assertThat(playerProfits.get(new Name("pobi"))).isEqualTo(new Money(0)),
                () -> assertThat(playerProfits.get(new Name("jason"))).isEqualTo(new Money(0))
                );
    }
}
