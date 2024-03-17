package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import blackjack.domain.player.info.Name;
import blackjack.fixture.PlayerFixture;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("플레이어 목록을 포함한 일급 컬렉션을 생성한다.")
    void create_with_playerList() {
        var player1 = PlayerFixture.게임_플레이어_생성(new Name("초롱"));
        var player2 = PlayerFixture.게임_플레이어_생성(new Name("조이썬"));
        var dealer = PlayerFixture.딜러_생성(new Name("딜러"));

        assertThatCode(() -> {
            new Players(dealer, List.of(player1, player2));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임 플레이어는 최대 7명까지만 플레이 가능하다.")
    void limit_maximum_name_is_7() {
        final List<String> names = List.of("초롱", "도비", "조이썬", "제우스", "폰드", "호티", "배키", "켬미");
        final Cards cards = new Cards(List.of(
                new Card(CardValue.EIGHT, CardSymbol.CLOVER),
                new Card(CardValue.ACE, CardSymbol.DIAMOND)));
        final var gamePlayers = names.stream()
                                     .map(name -> new GamePlayer(new Name(name), cards))
                                     .toList();
        final var dealer = PlayerFixture.딜러_생성(new Name("딜러"));

        assertThrows(IllegalArgumentException.class, () -> {
            new Players(dealer, gamePlayers);
        });
    }
}
