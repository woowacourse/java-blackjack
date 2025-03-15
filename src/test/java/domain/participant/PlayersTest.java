package domain.participant;

import domain.card.Card;
import domain.card.Number;
import domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어 리스트 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayersTest {

    @Test
    void 플레이어별_이름과_카드리스트의_총합을_반환한다() {
        Player drago = new Player("drago",
                List.of(new Card(Symbol.DIAMOND, domain.card.Number.EIGHT),
                        new Card(Symbol.DIAMOND, domain.card.Number.JACK),
                        new Card(Symbol.HEART, domain.card.Number.FOUR)),
            1000);

        Player duei = new Player("duei",
                List.of(new Card(Symbol.CLOVER, domain.card.Number.EIGHT),
                        new Card(Symbol.SPADE, domain.card.Number.JACK),
                        new Card(Symbol.HEART, domain.card.Number.TWO)),
            1000);

        Players players = new Players(List.of(drago, duei));
        Map<Player, Integer> expected = Map.of(drago, 22, duei, 20);

        assertThat(players.getTotalNumberSumByPlayer()).isEqualTo(expected);
    }

    @Test
    void 플레이어를_새로운_플레이어로_교체한다() {
        List<Card> cards = List.of(new Card(Symbol.DIAMOND, domain.card.Number.EIGHT), new Card(Symbol.CLOVER, domain.card.Number.FOUR));
        Player player = new Player("drago", cards, 1000);
        Players players = new Players(new ArrayList<>(List.of(player)));

        List<Card> newCards = List.of(
            new Card(Symbol.DIAMOND, domain.card.Number.EIGHT),
            new Card(Symbol.CLOVER, domain.card.Number.FOUR),
            new Card(Symbol.HEART, domain.card.Number.JACK));
        Player newPlayer = new Player("drago", newCards, 2000);
        Players expected = new Players(List.of(newPlayer));

        assertThat(players.editPlayer(newPlayer)).isEqualTo(expected);
    }

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
