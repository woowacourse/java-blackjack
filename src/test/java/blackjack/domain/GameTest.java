package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

public class GameTest {

    @Test
    @DisplayName("게임을 초기화 한다.")
    void initGame() {
        // give
        Game game = new Game(new CardDeck(new TestDeck()), List.of(Name.of("pobi"), Name.of("rick")));

        // when
        int actual = game.getPlayers().size();

        // then
        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("모든 참가자에게 카드를 2장씩 분배한다.")
    void initParticipants() {
        // give
        Game game = new Game(new CardDeck(new TestDeck()), List.of(Name.of("pobi"), Name.of("rick")));

        // when
        List<Integer> actual = new ArrayList<>();
        actual.add(game.getDealerScore());
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            actual.add(player.getScore());
        }

        // then
        assertThat(actual).isEqualTo(List.of(15, 20, 20));
    }

    @Test
    @DisplayName("상태가 HIT인 플레이어를 Optional로 반환한다.")
    void findHitPlayer() {
        // give
        Game game = new Game(new CardDeck(new TestDeck()), List.of(Name.of("pobi")));

        // when
        Optional<Player> hitPlayer = game.findHitPlayer();
        if (hitPlayer.isPresent()) {
            boolean actual = hitPlayer.get().isHit();
            // then
            assertThat(actual).isTrue();
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"HIT:30", "STAY:20"}, delimiter = ':')
    @DisplayName("상태가 HIT이면 플레이어가 카드를 1장 뽑는다.")
    void drawCard_HIT(PlayStatus playStatus, int expected) {
        // give
        Game game = new Game(new CardDeck(new TestDeck()), List.of(Name.of("pobi")));
        Player player = game.getPlayers().get(0);

        // when
        game.drawPlayerCard(player, playStatus);
        int actual = player.getScore();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 첫 번째 카드를 반환한다.")
    void dealerFirstCard() {
        // give
        Game game = new Game(new CardDeck(new TestDeck()), List.of(Name.of("pobi")));

        // when
        Card actual = game.dealerFirstCard();

        // then
        assertThat(actual).isEqualTo(new Card(CardSymbol.CLUB, CardNumber.FIVE));
    }

    @Test
    @DisplayName("딜러의 턴을 진행한다, 16 초과할 때까지 카드를 뽑는다.")
    void drawDealerCard() {
        // give
        Game game = new Game(new CardDeck(new TestDeck()), List.of(Name.of("pobi")));

        // when
        game.drawDealerCards();
        int actual = game.getDealerScore();

        // then
        assertThat(actual).isEqualTo(25);
    }
}
