package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Symbol;
import blackjack.exception.GamerDuplicateException;
import blackjack.exception.PlayerNotFoundException;
import blackjack.util.Pair;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class GamersTest {

    Gamers gamers;
    @BeforeEach
    void init() {
        gamers = new Gamers(
                Arrays.asList(
                new Gamer("pobi", 1000),
                new Gamer("neozal", 1000)
        ));
    }

    @Test
    @DisplayName("이름이 중복 시 예외")
    void createGamers_GamerDuplicateException() {
        Assertions.assertThatThrownBy(
                () -> new Gamers( Arrays.asList(
                        new Gamer("nabom", 1000),
                        new Gamer("nabom", 1000)
                ))
        ).isInstanceOf(GamerDuplicateException.class);
    }

    @Test
    void createGamers() {
        Gamers gamers = new Gamers( Arrays.asList(
                new Gamer("nabom", 1000),
                new Gamer("meozal", 1000)
        ));
        assertThat(gamers.getGamers().size()).isEqualTo(2);
    }

    @Test
    void drawToGamers() {
        List<Card> cards = Arrays.asList(
                new Card(Symbol.CLOVER, CardNumber.QUEEN),
                new Card(Symbol.CLOVER, CardNumber.TWO)
        );

        gamers.drawToGamers(new Cards(cards));

        List<Player> gamers = this.gamers.getGamers();

        for (Player gamer : gamers) {
            assertThat(gamer.getDeckAsList().stream().map(Card::getName).collect(toList()))
                    .containsAnyOf("Q클로버", "2클로버");
        }
    }

    @Test
    void findGamer() {
        assertThat(gamers.findGamer("pobi").getName()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("게이머가 존제하지 않으면 예외")
    void findGamer_notFoundException(){
        assertThatExceptionOfType(PlayerNotFoundException.class)
                .isThrownBy(() -> gamers.findGamer("ss"));
    }

}