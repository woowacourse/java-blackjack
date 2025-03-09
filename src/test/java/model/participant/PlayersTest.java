package model.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import model.deck.Deck;
import model.deck.DeckFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("중복된 이름이 포함된 경우 예외가 발생한다.")
    @Test
    void createPlayers_WithDuplicateNames_ShouldThrowException() {
        Deck deck = new Deck(DeckFactory.initializeDeck());

        assertThatThrownBy(() -> Players.createByNames(List.of("pobi", "jason", "pobi"), deck))
                .isInstanceOf(IllegalArgumentException.class);
    }
}