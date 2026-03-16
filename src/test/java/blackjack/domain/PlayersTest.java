package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import blackjack.domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    private Player player(String name){
        return Player.of(Name.of(name));
    }

    @Test
    void 플레이어_목록이_비어있으면_예외_발생한다(){
        Assertions.assertThatThrownBy(() -> Players.of(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 1명 이상이어야 합니다.");
    }

    @Test
    void 플레이어_목록이_null이면_예외_발생한다(){
        Assertions.assertThatThrownBy(() -> Players.of(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("플레이어 목록은 null일 수 없습니다.");
    }
    @Test
    void 게임에_참가한_플레이어의_명수를_반환한다() {
        Players players = Players.of(List.of(
                player("handa"),
                player("dalsu")
        ));
        assertThat(players.count()).isEqualTo(2);
    }

    @Test
    void 모든_플레이어에_대해_작업을_수행한다() {
        Players players = Players.of(List.of(
                player("handa"),
                player("dalsu")
        ));
        List<String> names = new ArrayList<>();
        players.forEach(p -> names.add(p.getName()));

        assertThat(names).containsExactly("handa", "dalsu");
    }

    @Test
    void 모든_플레이어는_배팅한다(){
        Players players = Players.of(List.of(
                player("handa"),
                player("dalsu")
        ));

        players.betPlayers(name -> "5000");

        List<BigDecimal> playerBetAmounts = new ArrayList<>();
        players.forEach(player -> {playerBetAmounts.add(player.getBet().getAmount());});
        assertThat(playerBetAmounts).containsOnly(new BigDecimal("5000"));
    }

    @Test
    void 모든_플레이어는_덱에서_카드를_2장씩_받는다(){
        Players players = Players.of(List.of(
                player("handa"),
                player("dalsu")
        ));

        players.deal(() -> TrumpCard.of(Suit.SPADE, Rank.ACE));

        List<Integer> cardCounts = new ArrayList<>();
        players.forEach(player -> {cardCounts.add(player.countCards());});
        assertThat(cardCounts).containsOnly(2);
    }
}
