package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.component.Symbol;
import blackjack.domain.generic.BettingMoney;
import blackjack.domain.report.GameReport;
import blackjack.domain.report.GameReports;
import blackjack.domain.result.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.card.CardBundleHelper.aCardBundle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayersTest {

    @DisplayName("게임 참여자가 존재하지 않으면 Exception")
    @Test
    void noPlayers() {
        assertAll(
                () -> assertThatThrownBy(() -> new Players(null))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("게임참여자가 존재하지 않습니다."),

                () -> assertThatThrownBy(() -> new Players(Collections.emptyList()))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("게임참여자가 존재하지 않습니다.")
        );
    }

    @DisplayName("딜러와 갬블러들을 비교해서 게임 결과 리스트를 받아오는 메서드")
    @Test
    void getReports() {
        //given
        Player dealer = new Dealer(aCardBundle(CardNumber.KING, CardNumber.KING));
        Player gambler1 = new Gambler(aCardBundle(CardNumber.ACE, CardNumber.KING), new PlayerInfo("bebop", BettingMoney.of(0)));
        Player gambler2 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.KING), new PlayerInfo("pobi", BettingMoney.of(0)));
        Player gambler3 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.NINE), new PlayerInfo("allen", BettingMoney.of(0)));
        Players players = new Players(Arrays.asList(dealer, gambler1, gambler2, gambler3));

        //when
        GameReports reports = players.getReports();

        //then
        assertThat(reports).isEqualTo(new GameReports(Arrays.asList(
                new GameReport("bebop", BettingMoney.of(0), GameResult.WIN),
                new GameReport("pobi", BettingMoney.of(0), GameResult.DRAW),
                new GameReport("allen", BettingMoney.of(0), GameResult.LOSE)))
        );
    }

    @DisplayName("찾아온 플레이어가 갬블러이다.")
    @Test
    void findGamblers() {
        //given
        Players players = new Players(Arrays.asList(new Gambler(CardBundle.emptyBundle(), new PlayerInfo("bebop", BettingMoney.of(0))), new Dealer(CardBundle.emptyBundle())));

        //when
        List<Gambler> gamblers = players.findGamblers();
        Player player = gamblers.get(0);

        //then
        assertThat(player.getClass()).isEqualTo(Gambler.class);
    }

    @DisplayName("찾아온 플레이어가 딜러이다.")
    @Test
    void findDealer() {
        //given
        Players players = new Players(Arrays.asList(new Gambler(CardBundle.emptyBundle(), new PlayerInfo("bebop", BettingMoney.of(0))), new Dealer(CardBundle.emptyBundle())));

        //when
        Player dealer = players.findDealer();

        //then
        assertThat(dealer.getClass()).isEqualTo(Dealer.class);
    }

    @DisplayName("2장씩 분배하는 것은 카드를 가진상태(게임의 시작이 아닌상태)에서 호출하면 Exception")
    @Test
    void drawStartingCard() {
        Player player = new Gambler(CardBundle.emptyBundle(), new PlayerInfo("bebop", BettingMoney.of(0)));
        player.drawCard(() -> Card.of(Symbol.DIAMOND, CardNumber.ACE));
        Players players = new Players(Arrays.asList(player));

        assertThatThrownBy(() -> players.drawStartingCard(CardDeck.getInstance(new CardFactory())))
                .isInstanceOf(IllegalStateException.class);
    }
}