package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackJackGameTest {

    private Deck blackJackDeck;
    private Dealer dealer;
    private Rule rule;

    @BeforeEach
    void setUp() {
        blackJackDeck = new Deck(Arrays.stream(TrumpCard.values()).toList());
        dealer = new Dealer(new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.TWO_OF_HEARTS)));
        rule = new Rule();
    }

    @Nested
    class ValidCases {

        @Test
        @DisplayName("플레이어가 정상적으로 생성되어야 한다.")
        void createPlayers() {
            // given
            BlackJackGame blackJackGame = new BlackJackGame(blackJackDeck, dealer, rule);
            List<String> playerNames = List.of("Alice", "Bob");
            Deck originalDeck = new Deck(Arrays.stream(TrumpCard.values()).toList());

            // when
            List<Player> players = blackJackGame.createPlayers(playerNames);

            // then
            assertSoftly(softly -> {
                softly.assertThat(players.getFirst().getName()).isEqualTo("Alice");
                softly.assertThat(players.getFirst().getHand()).isEqualTo(
                        new Hand(List.of(originalDeck.draw(), originalDeck.draw())));
                softly.assertThat(players.getLast().getName()).isEqualTo("Bob");
                softly.assertThat(players.getLast().getHand()).isEqualTo(
                        new Hand(List.of(originalDeck.draw(), originalDeck.draw())));
            });
        }


        @Test
        @DisplayName("블랙잭게임은 덱과 딜러와 룰을 가져야한다.")
        void validateNotNull() {
            // given
            Deck nullDeck = null;
            Dealer nullDealer = null;
            Rule nullRule = null;

            // when & then
            assertThatThrownBy(() -> new BlackJackGame(nullDeck, nullDealer, nullRule))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("블랙잭게임은 덱과 딜러와 룰을 가지고 있어야합니다.");
        }
    }
}
