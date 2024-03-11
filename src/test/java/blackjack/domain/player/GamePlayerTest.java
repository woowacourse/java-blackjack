package blackjack.domain.player;



import static org.assertj.core.api.Assertions.assertThat;

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
        var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.JACK, CardValue.KING));

        var result = sut.isReceivable();

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("딜러가 버스트 일때 플레이어가 버스트가 아니면 플레이어가 승리한다.")
    public void Dealer_Player_win_when_dealer_is_burst() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        Dealer dealer = new Dealer(name, cards);
        dealer.drawCard(new Card(CardValue.JACK, CardSymbol.HEART));
        var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.THREE));

        var result = sut.confirmResult(dealer);

        assertThat(result).isEqualTo(ResultStatus.WIN);
    }

    @Test
    @DisplayName("플레이어는 버스트이면 무조건 플레이어가 패배한다.")
    public void Dealer_Player_lose_when_burst() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        Dealer dealer = new Dealer(name, cards);
        var sut = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.FIVE));
        sut.drawCard(new Card(CardValue.JACK, CardSymbol.CLOVER));

        var result = sut.confirmResult(dealer);

        assertThat(result).isEqualTo(ResultStatus.LOSE);
    }
}
