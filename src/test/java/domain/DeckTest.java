package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import common.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DeckTest {

    Deck deck;

    @BeforeEach
    void init() {
        deck = Deck.createDeck();
    }


    @Test
    @DisplayName("Deck를 생성할 때 오류 발생 안함")
    void deck_create_success() {
        Assertions.assertDoesNotThrow(
                Deck::createDeck
        );
    }

    @Test
    @DisplayName("Deck에서 카드를 뽑아서 새로운 덱을 만들어줌")
    void give_initial_deck_success() {
        assertThat(deck.giveInitialDeck()).isInstanceOf(Deck.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 52})
    @DisplayName("Deck에서 카드를 원하는 장수만큼 뽑아줌")
    void draw_card_success(int count) {
        assertThat(deck.drawCard(count).size()).isEqualTo(count);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 53})
    @DisplayName("Deck에서 0이하 혹은 남은 카드 이상의 숫자 선택 시도 시 오류 발생")
    void draw_card_fail(int count) {
        assertThatThrownBy(
                () -> deck.drawCard(count)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
    }
}