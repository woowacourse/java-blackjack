package domains.user;

import domains.card.Card;
import domains.card.Deck;
import domains.card.Symbol;
import domains.card.Type;
import domains.user.name.PlayerName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    private static Player player;
    private static Deck deck;

    @BeforeAll
    static void setUp() {
        deck = new Deck();
        player = new Player(new PlayerName("또링"), "4000", deck);
    }

    @DisplayName("생성했을 때, 두 장의 카드를 보유하고 있는지 확인")
    @Test
    void constructor_InitializeParticipant_HandsSizeIsTwo() {
        assertThat(player.handSize()).isEqualTo(2);
    }

    @DisplayName("카드를 더 받을지 입력받을 때 y혹은 n이 입력되지 않았을 경우 예외처리")
    @ParameterizedTest
    @ValueSource(strings = {"Y", "1", "q"})
    void needMoreCard_InvalidAnswer_ExceptionThrown(String answer) {
        assertThatThrownBy(() -> player.needMoreCard(answer, deck))
                .isInstanceOf(InvalidPlayerException.class)
                .hasMessage(InvalidPlayerException.INVALID_INPUT);
    }

    @DisplayName("카드를 한 장 더 받았을 때, 버스트가 됐는지 확인")
    @ParameterizedTest
    @MethodSource("burstData")
    void hit_ScoreOver21_BurstIsTrue(List<Card> hands) {
        Hands hand = new Hands(hands);
        Player dealer = new Player(new PlayerName("작은곰"), "100000", hand);

        dealer.hit(deck);

        assertThat(dealer.isBurst()).isTrue();
    }

    static Stream<Arguments> burstData() {
        Card five = new Card(Symbol.FIVE, Type.SPADE);
        Card seven = new Card(Symbol.SEVEN, Type.HEART);
        Card ten = new Card(Symbol.TEN, Type.CLUB);

        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList(five, seven, ten)))
        );
    }


}
