package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Deck;

public class PlayersTest {

    @Test
    @DisplayName("참가자 인원이 2 ~ 8명이 아닌 경우 예외 처리 확인")
    void numberOfPlayersTest() {
        Deck deck = Deck.create();
        List<Player> players = IntStream.range(0, 10)
            .mapToObj(String::valueOf)
            .map(i -> new Player(i, deck.drawStartingCards()))
            .collect(Collectors.toList());

        assertThatThrownBy(() -> new Players(players))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("2명에서 8명 까지만 참가 가능합니다.");
    }

    @Test
    @DisplayName("참가자의 이름이 중복인 경우 확인")
    void duplicateTest() {
        Deck deck = Deck.create();
        List<Player> players = List.of(new Player("a", deck.drawStartingCards()),
            new Player("a", deck.drawStartingCards()));

        assertThatThrownBy(() -> new Players(players))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참가자 이름은 중복될 수 없습니다.");
    }
}
