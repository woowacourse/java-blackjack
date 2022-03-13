package blackjack.domain.gamer;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Cards;
import blackjack.domain.deck.Deck;

public class PlayersTest {

    @Test
    @DisplayName("참가자의 이름이 중복인 경우 확인")
    void duplicateTest() {
        Deck deck = new Deck(Deck.initCards());
        Assertions.assertThatThrownBy(() -> new Players(
                List.of(new Player("a", new Cards(deck.drawStartCards())),
                    new Player("a", new Cards(deck.drawStartCards())))))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참가자 이름은 중복될 수 없습니다.");
    }
}
