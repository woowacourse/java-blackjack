package blackjack.domain.result.prize;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import blackjack.domain.player.*;
import blackjack.domain.player.info.BettingMoney;
import blackjack.domain.player.info.PlayerInfo;
import blackjack.domain.player.info.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PrizeCheckerTest {
    @Test
    @DisplayName("플레이어가 블랙잭 , 딜러가 블랙잭이 아니면 1.5배의 금액을 받는다.")
    void player_blackjack_case() {
        final GamePlayer gamePlayer = 플레이어_생성(1000, List.of(CardValue.ACE, CardValue.JACK));

        final Dealer dealer = 딜러_생성(List.of(CardValue.EIGHT, CardValue.EIGHT));

        final var prize = PrizeChecker.check(dealer, gamePlayer);

        assertPrizeMoney(prize, 1500);
    }

    @Test
    @DisplayName("플레이어가 블랙잭 , 딜러가 블랙잭이면 배팅 금액을 돌려받는다.")
    void player_blackjack_dealer_blackjack_case() {
        final GamePlayer gamePlayer = 플레이어_생성(1000, List.of(CardValue.ACE, CardValue.JACK));

        final Dealer dealer = 딜러_생성(List.of(CardValue.ACE, CardValue.JACK));

        final var prize = PrizeChecker.check(dealer, gamePlayer);

        assertPrizeMoney(prize, 0);
    }

    @Test
    @DisplayName("플레이어가 버스트 , 딜러가 버스트이면 배팅 금액을 잃는다.")
    void player_buse_dealer_bust_case() {
        final GamePlayer gamePlayer = 플레이어_생성(1000, List.of(CardValue.JACK, CardValue.JACK, CardValue.EIGHT));

        final Dealer dealer = 딜러_생성(List.of(CardValue.TWO, CardValue.JACK, CardValue.JACK));

        final var prize = PrizeChecker.check(dealer, gamePlayer);

        assertPrizeMoney(prize, -1000);
    }

    @Test
    @DisplayName("플레이어가 버스트 , 딜러가 버스트가 아니면 배팅 금액을 잃는다.")
    void player_bust_case() {
        final GamePlayer gamePlayer = 플레이어_생성(1000, List.of(CardValue.JACK, CardValue.JACK, CardValue.EIGHT));

        final Dealer dealer = 딜러_생성(List.of(CardValue.TWO, CardValue.JACK));

        final var prize = PrizeChecker.check(dealer, gamePlayer);

        assertPrizeMoney(prize, -1000);
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 , 딜러가 버스트이면 배팅 금액만큼 받는다.")
    void dealer_bust_case() {
        final GamePlayer gamePlayer = 플레이어_생성(1000, List.of(CardValue.JACK, CardValue.JACK));
        final Dealer dealer = 딜러_생성(List.of(CardValue.TWO, CardValue.JACK, CardValue.JACK));

        final var prize = PrizeChecker.check(dealer, gamePlayer);

        assertPrizeMoney(prize, 1000);
    }

    @Test
    @DisplayName("플레이어가 스탠드 , 딜러가 스탠드 일때 플레이어가 숫자가 더 크면 배팅 금액만큼 받는다.")
    void player_score_high_case() {
        final GamePlayer gamePlayer = 플레이어_생성(1000, List.of(CardValue.JACK, CardValue.JACK));
        gamePlayer.stand();
        final Dealer dealer = 딜러_생성(List.of(CardValue.TWO, CardValue.JACK));
        dealer.stand();

        final var prize = PrizeChecker.check(dealer, gamePlayer);

        assertPrizeMoney(prize, 1000);
    }

    @Test
    @DisplayName("플레이어가 스탠드 , 딜러가 스탠드 일때 숫자가 같으면 배팅 금액만큼 돌려받는다.")
    void player_score_dealer_score_equal_case() {
        final GamePlayer gamePlayer = 플레이어_생성(1000, List.of(CardValue.JACK, CardValue.JACK));
        gamePlayer.stand();
        final Dealer dealer = 딜러_생성(List.of(CardValue.JACK, CardValue.JACK));
        dealer.stand();

        final var prize = PrizeChecker.check(dealer, gamePlayer);

        assertPrizeMoney(prize, 0);
    }

    @Test
    @DisplayName("플레이어가 스탠드 , 딜러가 스탠드 일때 플레이어 숫자가 작으면 배팅 금액을 잃는다.")
    void dealer_score_high_case() {
        final GamePlayer gamePlayer = 플레이어_생성(2000, List.of(CardValue.JACK, CardValue.FIVE));
        gamePlayer.stand();
        final Dealer dealer = 딜러_생성(List.of(CardValue.JACK, CardValue.JACK));
        dealer.stand();

        final var prize = PrizeChecker.check(dealer, gamePlayer);

        assertPrizeMoney(prize, -2000);
    }


    private GamePlayer 플레이어_생성(final Integer bettingMoney, final List<CardValue> cardValues) {
        final PlayerInfo playerInfo = new PlayerInfo(new Name("조이썬"), new BettingMoney(bettingMoney));
        final GamePlayer gamePlayer = new GamePlayer(playerInfo);
        cardValues.forEach(cardValue -> gamePlayer.drawCard(new Card(cardValue, CardSymbol.DIAMOND)));
        return gamePlayer;
    }

    private Dealer 딜러_생성(final List<CardValue> cardValues) {
        final Dealer dealer = Dealer.createDefaultNameDealer();
        cardValues.forEach(cardValue -> dealer.drawCard(new Card(cardValue, CardSymbol.DIAMOND)));
        return dealer;
    }

    private void assertPrizeMoney(final PrizeMoney prizeMoney, final int value) {
        assertThat(prizeMoney.value()).isEqualTo(value);
    }
}
