package blackjack.domain.result;

import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Name;
import blackjack.fixture.CardFixture;
import blackjack.fixture.PlayerFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCheckerTest {
    @Test
    @DisplayName("플레이어가 버스트이면 패배한다.")
    public void player_lose_when_bust() {
        final GamePlayer gamePlayer = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.FIVE, CardValue.JACK));
        final Name name = new Name("딜러");
        final Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        final var dealer = new Dealer(name, cards);

        final var result = ResultChecker.calculate(dealer, gamePlayer);

        assertThat(result).isEqualTo(ResultStatus.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트 , 딜러가 버스트이면 플레이어가 패배한다.")
    public void player_lose_when_bust_with_dealer_bust() {
        final GamePlayer gamePlayer = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.FIVE, CardValue.JACK));
        final Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR, CardValue.JACK));
        final var dealer = Dealer.createDefaultDealer(cards);

        final var result = ResultChecker.calculate(dealer, gamePlayer);

        assertThat(result).isEqualTo(ResultStatus.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트이면 플레이어가 승리한다.")
    public void player_win_when_dealer_bust() {
        final GamePlayer gamePlayer = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.FIVE));

        final Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR, CardValue.JACK));
        final var dealer = Dealer.createDefaultDealer(cards);

        final var result = ResultChecker.calculate(dealer, gamePlayer);

        assertThat(result).isEqualTo(ResultStatus.WIN);
    }

    @Test
    @DisplayName("둘다 버스트가 아니면 숫자의 합을 비교한다.")
    public void check_sum_of_cards() {
        final GamePlayer gamePlayer = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.FIVE));

        final Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        final var dealer = Dealer.createDefaultDealer(cards);

        final var result = ResultChecker.calculate(dealer, gamePlayer);

        assertThat(result).isEqualTo(ResultStatus.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어의 숫자가 같으면 무승부이다.")
    public void player_draw_when_sum_of_cards_equal() {
        final GamePlayer gamePlayer = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.ACE));

        final Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.JACK, CardValue.ACE));
        final var dealer = Dealer.createDefaultDealer(cards);

        final var result = ResultChecker.calculate(dealer, gamePlayer);

        assertThat(result).isEqualTo(ResultStatus.DRAW);
    }

}
