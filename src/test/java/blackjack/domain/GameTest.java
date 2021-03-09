package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.exception.PlayerNotFoundException;
import blackjack.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        Player dealer = new Dealer();
        List<Card> cards = Arrays.asList(
                new Card(Symbol.CLOVER, CardNumber.KING),
                new Card(Symbol.CLOVER, CardNumber.TWO),
                new Card(Symbol.CLOVER, CardNumber.THREE),
                new Card(Symbol.CLOVER, CardNumber.FOUR),
                new Card(Symbol.CLOVER, CardNumber.FIVE),
                new Card(Symbol.CLOVER, CardNumber.QUEEN),
                new Card(Symbol.HEART, CardNumber.TWO),
                new Card(Symbol.SPADE, CardNumber.THREE),
                new Card(Symbol.HEART, CardNumber.FIVE),
                new Card(Symbol.HEART, CardNumber.QUEEN),
                new Card(Symbol.DIAMOND, CardNumber.FOUR),
                new Card(Symbol.HEART, CardNumber.ACE),
                new Card(Symbol.HEART, CardNumber.KING)
        );
        game = new Game(
                new Cards(cards),
                dealer,
                new Gamers(Stream.of("nabom", "neozal")
                        .map(name -> new Pair<>(name, 1))
                        .collect(toList()))
        );
    }

    @Test
    @DisplayName("Game 초기화 시 각 플레이어에게 두 장의 카드를 배분")
    void initialize_drawTwoCardsToPlayers() {
        assertThat(game.getDealer().getDeckAsList().size()).isEqualTo(2);
        for (Player player : game.getGamersAsList()) {
            assertThat(player.getDeckAsList().size()).isEqualTo(2);
        }
    }

    @Test
    @DisplayName("딜러에게 카드를 한장 준다.")
    void drawCardToDealer() {
        game.drawCardToDealer();
        assertThat(game.getDealer().getDeckAsList().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("게이머들의 이름으로 게이머를 반환한다.")
    void findGamerByName() {
        assertThat(game.findGamerByName("neozal").getName()).isEqualTo("neozal");
    }

    @Test
    @DisplayName("게이머가 존제하지 않으면 예외")
    void findGamerByName_notFoundException() {
        assertThatExceptionOfType(PlayerNotFoundException.class)
                .isThrownBy(() -> game.findGamerByName("pobi"));
    }

    @Test
    @DisplayName("게이머들의 이름을 스트링으로 반환한다.")
    void getGamerNames() {
        assertThat(game.getGamerNames()).containsExactly("nabom", "neozal");
    }
}