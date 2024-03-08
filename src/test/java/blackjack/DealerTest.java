package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.common.Name;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.result.ResultStatus;
import blackjack.fixture.CardFixture;
import blackjack.fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("기본 이름(딜러)을 가진 딜러를 생성 한다.")
    public void Dealer_Instance_create_with_default_name_and_cards() {
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));

        assertThatCode(() -> {
                    var sut = Dealer.createDefaultDealer(cards);
                    assertThat(sut.getNameAsString()).isEqualTo("딜러");
                }
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러는 숫자의 합이 16이하면 카드를 받아야 한다.")
    public void Dealer_Can_receive_additional_card() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        var sut = new Dealer(name, cards);

        assertTrue(sut.isReceivable());
    }

    @Test
    @DisplayName("딜러는 숫자의 합이 17이상이면 카드를 받아야 한다.")
    public void Dealer_Can_not_receive_additional_card() {
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.JACK, CardValue.KING));
        var sut = new Dealer(name, cards);

        assertFalse(sut.isReceivable());
    }


    @Test
    @DisplayName("딜러가 버스트 일때 플레이어가 버스트가 아니면 플레이어가 승리한다.")
    public void Dealer_Player_win_when_dealer_is_burst() {
        GamePlayer gamePlayer = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.THREE));
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        var sut = new Dealer(name, cards);
        sut.drawCard(new Card(CardValue.JACK, CardSymbol.HEART));

        var result = sut.checkPlayer(gamePlayer);

        assertThat(result).isEqualTo(ResultStatus.WIN);
    }

    @Test
    @DisplayName("플레이어는 버스트이면 무조건 플레이어가 패배한다.")
    public void Dealer_Player_lose_when_burst() {
        GamePlayer gamePlayer = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.FIVE));
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        var sut = new Dealer(name, cards);
        gamePlayer.drawCard(new Card(CardValue.JACK, CardSymbol.CLOVER));

        var result = sut.checkPlayer(gamePlayer);

        assertThat(result).isEqualTo(ResultStatus.LOSE);
    }
}
