package blackjack.domain.player;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Profit;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.result.ResultStatus;
import blackjack.fixture.CardFixture;
import blackjack.fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GamePlayerTest {
    @Test
    @DisplayName("플레이어는 숫자의 합이 21이 넘지 않을 때까지 추가 카드를 받을 수 있다.")
    public void GamePlayer_Can_receive_additional_card() {
        var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.ACE));

        var result = sut.isReceivable();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어는 숫자의 합이 21이 넘어 가면 추가 카드를 받을 수 없다.")
    public void GamePlayer_Can_not_receive_additional_card() {
        var sut = PlayerFixture.게임_플레이어_생성(
                List.of(CardValue.EIGHT, CardValue.JACK, CardValue.KING));

        var result = sut.isReceivable();

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("딜러가 버스트 일때 플레이어가 버스트가 아니면 플레이어가 승리한다.")
    public void GamePlayer_Player_win_when_dealer_is_burst() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        Dealer dealer = new Dealer(name, cards);
        dealer.drawCard(new Card(CardValue.JACK, CardSymbol.HEART));
        var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.THREE));

        var result = sut.confirmProfit(dealer);

        assertThat(result).isEqualTo(new Profit(10000));
    }

    @Test
    @DisplayName("플레이어는 버스트이면 무조건 플레이어가 패배한다.")
    public void GamePlayer_Player_lose_when_burst() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        Dealer dealer = new Dealer(name, cards);
        var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.FIVE));
        sut.drawCard(new Card(CardValue.JACK, CardSymbol.CLOVER));

        var result = sut.confirmProfit(dealer);

        assertThat(result).isEqualTo(new Profit(-10000));
    }

    @ParameterizedTest
    @CsvSource({
            "EIGHT, TEN, NINE, TEN, 10000",
            "EIGHT, TEN, SEVEN, TEN, -10000",
            "EIGHT, TEN, EIGHT, TEN, 0",
            "EIGHT, TEN, ACE, TEN, 15000"
    })
    @DisplayName("플레이어의 점수가 딜러의 점수보다 높으면 배팅금액의 1배만큼 수익을 얻는다.(블랙잭X)")
    public void GamePlayer_Player_get_once_profit_when_score_is_bigger(CardValue dealerCard1,
                                                                       CardValue dealerCard2,
                                                                       CardValue playerCard1,
                                                                       CardValue playerCard2,
                                                                       int profit) {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(dealerCard1, dealerCard2));
        Dealer dealer = new Dealer(name, cards);
        var sut = PlayerFixture.게임_플레이어_생성(List.of(playerCard1, playerCard2));

        var result = sut.confirmProfit(dealer);

        assertThat(result).isEqualTo(new Profit(profit));
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아닌 21점이고 딜러는 블랙잭인 경우 배팅금액의 -1배만큼 수익을 얻는다.")
    public void GamePlayer_Player_get_minus_once_profit_when_not_blackjack_dealer_blackjack() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.ACE, CardValue.TEN));
        Dealer dealer = new Dealer(name, cards);
        var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.TWO, CardValue.TEN, CardValue.NINE));

        var result = sut.confirmProfit(dealer);

        assertThat(result).isEqualTo(new Profit(-10000));
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭이면 배팅금액의 0배만큼 수익을 얻는다.")
    public void GamePlayer_Player_get_zero_profit_when_blackjack_both() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.ACE, CardValue.TEN));
        Dealer dealer = new Dealer(name, cards);
        var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.ACE, CardValue.QUEEN));

        var result = sut.confirmProfit(dealer);

        assertThat(result).isEqualTo(new Profit(0));
    }
}
