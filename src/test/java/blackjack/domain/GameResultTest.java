package blackjack.domain;

import static blackjack.fixture.CardFixture.CLUB_ONE;
import static blackjack.fixture.CardFixture.DIAMOND_ACE;
import static blackjack.fixture.CardFixture.DIAMOND_FIVE;
import static blackjack.fixture.CardFixture.DIAMOND_FOUR;
import static blackjack.fixture.CardFixture.DIAMOND_NINE;
import static blackjack.fixture.CardFixture.DIAMOND_ONE;
import static blackjack.fixture.CardFixture.DIAMOND_TEN;
import static blackjack.fixture.CardFixture.DIAMOND_THREE;
import static blackjack.fixture.CardFixture.HEART_ONE;
import static blackjack.fixture.CardFixture.SPADE_ONE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.WinningResult;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class GameResultTest {
    @Test
    void 플레이어의_승패를_저장할_수_있다() {
        //given
        Player pobi = new Player("pobi",
                new Cards(
                        DIAMOND_THREE,
                        DIAMOND_TEN
                ), 10000);
        Player surf = new Player("surf",
                new Cards(
                        DIAMOND_THREE,
                        DIAMOND_TEN,
                        DIAMOND_FIVE,
                        DIAMOND_THREE
                ), 10000);
        Players players = new Players(List.of(pobi, surf));
        Stack<Card> cards = new Stack<>();
        cards.addAll(List.of(
                DIAMOND_FOUR,
                DIAMOND_FIVE,
                DIAMOND_ONE,
                CLUB_ONE,
                HEART_ONE,
                SPADE_ONE
        ));
        Dealer dealer = new Dealer(players, new Deck(cards), new Cards(DIAMOND_ACE, DIAMOND_NINE));

        Map<Player, WinningResult> playerGameResults =
                Map.of(pobi, WinningResult.LOSE,
                        surf, WinningResult.WIN);
        Map<WinningResult, Integer> dealerGameResult = Map.of(WinningResult.LOSE, 1, WinningResult.WIN, 1);

        //when
        GameResult gameResult = GameResult.create(dealer, players);

        //then
        assertThat(gameResult.playerResults()).isEqualTo(playerGameResults);
        assertThat(gameResult.dealerResults()).isEqualTo(dealerGameResult);
    }
}
