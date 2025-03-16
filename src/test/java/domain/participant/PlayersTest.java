package domain.participant;

import domain.card.Card;
import domain.card.Number;
import domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어 리스트 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayersTest {

    @Test
    void 플레이어들의_수익을_모두_구한다() {
        Player player = new Player("drago",
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.ACE),
                new Card(Symbol.DIAMOND, domain.card.Number.JACK)),
            1000);
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer(
            List.of(
                new Card(Symbol.HEART, domain.card.Number.FOUR),
                new Card(Symbol.HEART, Number.FOUR)
            )
        );

        Map<Player, Integer> expected = Map.of(player, 1500);
        assertThat(players.judgeAllPlayersIncomes(dealer)).isEqualTo(expected);
    }
}
