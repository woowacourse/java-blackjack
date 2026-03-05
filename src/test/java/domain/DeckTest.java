package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import common.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DeckTest {
    Deck deck;
    CardCreationStrategy fixedCardCreationStrategy = new CardCreationStrategy() {
        @Override
        public List<Card> create() {
            Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
            Card clover5 = new Card(CardShape.클로버, CardContents.FIVE);

            return List.of(spadeJ, clover5);
        }
    };

    @BeforeEach
    void init() {
        deck = Deck.createDeck(fixedCardCreationStrategy); //TODO : 전략 넣기
    }

    @Test
    @DisplayName("Deck를 생성할 때 오류 발생 안함")
    void deck_create_success() {
        Assertions.assertDoesNotThrow(
                () -> Deck.createDeck(fixedCardCreationStrategy)
        );
    }

    @Test
    @DisplayName("참가자의 Deck를 생성할 때 오류 발생 안함")
    void initial_deck_create_success() {
        Assertions.assertDoesNotThrow(
                () -> Deck.createParticipantDeck(deck)
        );
    }

    /*
    @Test
    @DisplayName("여러 장의 카드의 합을 구함")
    void calculate_card_score_sum() {
        List<Card> cards
        assertThat(deck.calculateCardScoreSum()).isEqualTo(340);
    }

     */
    @Nested
    class drawTest {
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
}