package domain.result.dto;

import domain.card.*;
import domain.gamer.Dealer;
import domain.gamer.Players;
import domain.result.GameResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DealerResultDtoTest {
    private Deck deck;
    private Dealer dealer;
    private Players players;
    private GameResults gameResults;
    private DealerResultDto dealerResultDto;

    @BeforeEach
    void setUp() {
        Stack<Card> cards = new Stack<>();
        cards.push(new Card(Symbol.TWO, Type.CLOVER));
        cards.push(new Card(Symbol.THREE, Type.CLOVER));
        cards.push(new Card(Symbol.FOUR, Type.CLOVER));
        cards.push(new Card(Symbol.FIVE, Type.CLOVER));
        cards.push(new Card(Symbol.SIX, Type.CLOVER));
        cards.push(new Card(Symbol.SEVEN, Type.CLOVER));
        cards.push(new Card(Symbol.EIGHT, Type.CLOVER));
        cards.push(new Card(Symbol.NINE, Type.CLOVER));
        deck = new Deck(cards);
        dealer = new Dealer(deck.dealInitCards());
        players = Players.valueOf(deck, Arrays.asList("a","b","c"));
        gameResults = new GameResults(dealer, players);
        dealerResultDto = DealerResultDto.of(gameResults);
    }

    @Test
    @DisplayName("생성 테스트")
    void of() {
        assertThat(dealerResultDto).isNotNull();
    }

    @Test
    @DisplayName("올바른 값을 가지는 지 테스트")
    void checkValue() {
        assertThat(dealerResultDto).hasFieldOrPropertyWithValue("winCount", 3)
                .hasFieldOrPropertyWithValue("drawCount", 0)
                .hasFieldOrPropertyWithValue("loseCount", 0);
    }
}
