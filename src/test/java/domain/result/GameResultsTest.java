package domain.result;

import domain.card.Deck;
import domain.card.DeckFactory;
import domain.gamer.Dealer;
import domain.gamer.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultsTest {
    private Deck deck;
    private Dealer dealer;
    private Players players;

    @BeforeEach
    void setUp() {
        deck = DeckFactory.create();
        dealer = new Dealer(deck.dealInitCards());
        players = Players.valueOf(deck, Arrays.asList("a","b","c"));
    }

    @Test
    @DisplayName("게임 결과 생성 테스트")
    void construct() {
        assertThat(new GameResults(dealer, players)).isNotNull();
    }
}
