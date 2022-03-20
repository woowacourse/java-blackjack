package blackjack.domain.game;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.Name;
import blackjack.domain.TestDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.state.Bet;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;

public class GameTest {

    @Test
    @DisplayName("게임을 초기화 한다.")
    void initGame() {
        // given
        Game game = new Game(new CardDeck(new TestDeck()),
            Map.of(Name.of("pobi"), new Bet(1),
                Name.of("rick"), new Bet(1)));

        // when
        int actual = game.getPlayers().size();

        // then
        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("모든 참가자에게 카드를 2장씩 분배한다.")
    void initParticipants() {
        // given
        Game game = new Game(new CardDeck(new TestDeck()),
            Map.of(Name.of("pobi"), new Bet(1),
                Name.of("rick"), new Bet(1)));

        // when
        List<Integer> actual = new ArrayList<>();
        actual.add(game.getParticipants().get(0).getScore());
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            actual.add(player.getScore());
        }

        // then
        assertThat(actual).isEqualTo(List.of(15, 20, 20));
    }

    @ParameterizedTest
    @CsvSource(value = {"y:30", "n:20"}, delimiter = ':')
    @DisplayName("상태가 HIT이면 플레이어가 카드를 1장 뽑는다.")
    void drawCard_HIT(String hitOrStay, int expected) {
        // given
        Game game = new Game(new CardDeck(new TestDeck()), Map.of(Name.of("pobi"), new Bet(1)));
        Player player = game.getPlayers().get(0);

        // when
        game.drawPlayerCard(player, hitOrStay);
        int actual = player.getScore();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 중지 조건에 만족할 때 까지 카드를 뽑는다. (BUST X)")
    void drawCards_Not_BUST() {
        // given
        CardDeck cardDeck = new CardDeck(() -> new ArrayDeque<>(List.of(
            new Card(SPADE, QUEEN), new Card(HEART, SEVEN),
            new Card(DIAMOND, QUEEN), new Card(CLUB, SEVEN))));
        Game game = new Game(cardDeck, Map.of(Name.of("pobi"), new Bet(1)));

        // when
        game.drawDealerCards();
        State actual = game.getParticipants().get(0).getState();

        // then
        assertThat(actual).isInstanceOf(Hit.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"SEVEN:false", "SIX:true"}, delimiter = ':')
    @DisplayName("딜러가 카드를 추가로 뽑았는지 아닌지 검증한다.")
    void isDraw(CardNumber cardNumber, boolean expected) {
        // given
        CardDeck cardDeck = new CardDeck(() -> new ArrayDeque<>(List.of(
            new Card(DIAMOND, ACE),
            new Card(HEART, QUEEN), new Card(SPADE, SEVEN),
            new Card(DIAMOND, QUEEN), new Card(CLUB, cardNumber))));
        Game game = new Game(cardDeck, Map.of(Name.of("pobi"), new Bet(1)));

        // when
        boolean actual = game.drawDealerCards().isDraw();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 턴을 진행한다, 16 초과할 때까지 카드를 뽑는다. (Bust)")
    void drawDealerCard() {
        // given
        Game game = new Game(new CardDeck(new TestDeck()),
            Map.of(Name.of("pobi"), new Bet(1)));

        // when
        game.drawDealerCards();
        State actual = game.getParticipants().get(0).getState();

        // then
        assertThat(actual).isInstanceOf(Bust.class);
    }
}
