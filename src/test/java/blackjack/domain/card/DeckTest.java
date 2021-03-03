package blackjack.domain.card;

import blackjack.domain.gamer.Gamers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeckTest {

    Deck deck;

    @BeforeEach
    void setUp() {
        deck = Deck.create();
    }

    @DisplayName("Deck 객체 생성")
    @Test
    void createCards() {
        assertThatCode(() -> Deck.create()).doesNotThrowAnyException();
    }

    @DisplayName("초기 나눠주는 Hands는 두 장의 카드로 이루어진다.")
    @Test
    void giveFirstHands() {
        assertThat(deck.giveFirstHand().toList().size()).isEqualTo(2);
    }

    @DisplayName("덱에 카드가 존재할 때 isEmpty false 반환")
    @Test
    void isEmpty() {
        assertFalse(deck.isEmpty());
    }

    @DisplayName("카드 한 장 나눠주기")
    @Test
    void giveSingleCard() {
        assertThat(deck.giveSingleCard()).isInstanceOf(Card.class);
    }

    @DisplayName("참여자 생성 성공")
    @Test
    void initiateGamersTest() {
        assertThat(deck.initiateGamers(Arrays.asList("joanne","pk"))).isInstanceOf(Gamers.class);
    }
}
