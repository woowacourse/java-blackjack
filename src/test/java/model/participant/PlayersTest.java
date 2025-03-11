package model.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import model.bettingamount.BettingAmount;
import model.bettingamount.BettingAmounts;
import model.deck.Deck;
import model.deck.DeckFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("중복된 이름이 포함된 경우 예외가 발생한다.")
    @Test
    void createPlayers_WithDuplicateNames_ShouldThrowException() {
        Deck deck = new Deck(DeckFactory.initializeDeck());

        assertThatThrownBy(() -> Players.createByNames(List.of("pobi", "jason", "pobi"), deck,
                new BettingAmounts(Map.of("pobi", new BettingAmount(1000), "jason", new BettingAmount(2000)))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복되지 않아야 합니다.");
    }
}