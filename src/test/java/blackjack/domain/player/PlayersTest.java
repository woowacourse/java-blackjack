package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.Blackjack;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.fixture.CardFixture;
import blackjack.fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("플레이어 목록을 포함한 일급 컬렉션을 생성한다.")
    public void Players_Instance_create_with_playerList() {
        var player1 = PlayerFixture.게임_플레이어_생성(new Name("초롱"), new BettingAmount("10000"));
        var player2 = PlayerFixture.게임_플레이어_생성(new Name("조이썬"), new BettingAmount("10000"));
        var dealer = PlayerFixture.딜러_생성(new Name("딜러"));

        assertThatCode(() -> {
            new Players(dealer, List.of(player1, player2));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임 결과를 종합한다.")
    public void Dealer_Count_result() {
        GamePlayer gamePlayer = PlayerFixture.게임_플레이어_생성(List.of(CardValue.EIGHT, CardValue.THREE));
        Name name = new Name("딜러");
        Cards cards = CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR));
        Dealer dealer = new Dealer(name, cards);
        var sut = new Players(dealer, List.of(gamePlayer));

        var result = sut.compareResults();

        assertThat(result.getResult()
                .get(gamePlayer.getName())).isEqualTo(new Profit(-10000));
        assertThat(result.getResult()
                .get(name)).isEqualTo(new Profit(10000));
    }
}
