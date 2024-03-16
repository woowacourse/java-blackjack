package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gamers.Dealer;
import blackjack.domain.gamers.Name;
import blackjack.domain.gamers.Player;
import blackjack.domain.result.Bettings;
import blackjack.domain.result.Judge;
import blackjack.domain.result.Money;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingsTest {

    //@formatter:off
    /**
     * 카드 저장 순서
     * Ace카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * 2카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * ...
     * KING카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * <p>
     * 카드 pop 순서는 카드 저장 순서의 역순이다.
     */
    //@formatter:on
    @DisplayName("플레이어가 블랙잭으로 승리했다면, 배팅금액의 1.5배의 수익을 얻는다.")
    @Test
    void calculateBlackjackWinProfits() {
        //given
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        for (int i = 0; i < 48; i++) {
            dealer.drawPlayerCard();
        }
        player.draw(dealer.drawPlayerCard());

        final Judge judge = new Judge(dealer);
        final Map<Player, Money> rawBettings = new HashMap<>();
        rawBettings.put(player, new Money(10000));
        final Bettings bettings = new Bettings(rawBettings);

        //when
        final Map<Name, Money> playerProfits = bettings.calculatePlayerProfits(judge);

        //then
        assertThat(playerProfits.get(new Name("pobi")).value()).isEqualTo(15000);
    }

    @DisplayName("플레이어가 일반승리했다면, 배팅금액만큼 수익을 얻는다.")
    @Test
    void calculateNormalWinProfits() {
        //given
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();

        final Judge judge = new Judge(dealer);
        final Map<Player, Money> rawBettings = new HashMap<>();
        rawBettings.put(player, new Money(10000));
        final Bettings bettings = new Bettings(rawBettings);

        //when
        final Map<Name, Money> playerProfits = bettings.calculatePlayerProfits(judge);

        //then
        assertThat(playerProfits.get(new Name("pobi")).value()).isEqualTo(10000);
    }

    @DisplayName("플레이어가 패배했다면, 수익은 (-배팅금액)이다.")
    @Test
    void calculateLoseProfits() {
        //given
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();

        final Judge judge = new Judge(dealer);
        final Map<Player, Money> rawBettings = new HashMap<>();
        rawBettings.put(player, new Money(10000));
        final Bettings bettings = new Bettings(rawBettings);

        //when
        final Map<Name, Money> playerProfits = bettings.calculatePlayerProfits(judge);

        //then
        assertThat(playerProfits.get(new Name("pobi")).value()).isEqualTo(-10000);
    }

    @DisplayName("무승부라면, 수익은 0이다.")
    @Test
    void calculatePushProfits() {
        //given
        final Player player = Player.from("pobi");
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();

        final Judge judge = new Judge(dealer);
        final Map<Player, Money> rawBettings = new HashMap<>();
        rawBettings.put(player, new Money(10000));
        final Bettings bettings = new Bettings(rawBettings);

        //when
        final Map<Name, Money> playerProfits = bettings.calculatePlayerProfits(judge);

        //then
        assertThat(playerProfits.get(new Name("pobi")).value()).isEqualTo(0);
    }
}
