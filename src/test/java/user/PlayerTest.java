package user;

import card.Card;
import card.Deck;
import card.Symbol;
import card.Type;
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
        player = new Player("또링", deck);
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

    @DisplayName("첫 카드로 ace와 10의 점수를 갖는 카드가 주어졌을 때, 블랙잭인지 확인")
    @ParameterizedTest
    @MethodSource("blackJackData")
    void checkBlackJack_AceWithTen_ReturnTrue(List<Card> cards) {
        Hands hands = new Hands(cards);
        assertThat(new Player("또링", hands).checkBlackJack()).isTrue();
    }

    static Stream<Arguments> blackJackData() {
        Card ace = new Card(Symbol.ACE, Type.CLUB);
        Card ten = new Card(Symbol.TEN, Type.SPADE);
        Card jack = new Card(Symbol.JACK, Type.HEART);
        Card queen = new Card(Symbol.QUEEN, Type.HEART);
        Card king = new Card(Symbol.KING, Type.HEART);

        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList(ace, ten))),
                Arguments.of(new ArrayList<>(Arrays.asList(ace, jack))),
                Arguments.of(new ArrayList<>(Arrays.asList(ace, queen))),
                Arguments.of(new ArrayList<>(Arrays.asList(ace, king)))
        );
    }
}
