package blackjack.player.domain;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.GameResult;
import blackjack.card.domain.component.CardNumber;
import blackjack.player.domain.report.GameReport;
import blackjack.player.domain.report.GameReports;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static blackjack.card.domain.CardBundleHelper.aCardBundle;
import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {
    @DisplayName("딜러와 갬블러들을 비교해서 게임 결과 리스트를 받아오는 메서드")
    @Test
    void getReports() {
        //given
        Player dealer = new Dealer(aCardBundle(CardNumber.KING, CardNumber.KING));
        Player gambler1 = new Gambler(aCardBundle(CardNumber.ACE, CardNumber.KING), "bebop");
        Player gambler2 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.KING), "pobi");
        Player gambler3 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.NINE), "allen");
        Players players = new Players(Arrays.asList(dealer, gambler1, gambler2, gambler3));

        //when
        GameReports reports = players.getReports();

        //then
        assertThat(reports).isEqualTo(new GameReports(Arrays.asList(
                new GameReport("bebop", GameResult.WIN),
                new GameReport("pobi", GameResult.DRAW),
                new GameReport("allen", GameResult.LOSE)))
        );
    }

    @DisplayName("찾아온 플레이어가 갬블러이다.")
    @Test
    void findGamblers() {
        //given
        Players players = new Players(Arrays.asList(new Gambler(new CardBundle(), "bebop"), new Dealer(new CardBundle())));

        //when
        List<Player> gamblers = players.findGamblers();
        Player player = gamblers.get(0);

        //then
        assertThat(player.getClass()).isEqualTo(Gambler.class);
    }

    @DisplayName("찾아온 플레이어가 딜러이다.")
    @Test
    void findDealer() {
        //given
        Players players = new Players(Arrays.asList(new Gambler(new CardBundle(), "bebop"), new Dealer(new CardBundle())));

        //when
        Player dealer = players.findDealer();

        //then
        assertThat(dealer.getClass()).isEqualTo(Dealer.class);
    }
}