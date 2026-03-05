package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import common.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {
    Deck deck;
    CardCreationStrategy fixedCardCreationStrategy = new CardCreationStrategy() {
        @Override
        public List<Card> create() {
            Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
            Card clover5 = new Card(CardShape.클로버, CardContents.FIVE);

            return new ArrayList<>(List.of(spadeJ, clover5));
        }
    };

    @BeforeEach
    void init() {
        deck = Deck.createDeck(fixedCardCreationStrategy); //TODO : 전략 넣기
    }

    @Test
    @DisplayName("Deck를 생성할 때 오류 발생 안함")
    void deck_create_success() {
        assertDoesNotThrow(
                () -> Deck.createDeck(fixedCardCreationStrategy)
        );
    }

    @Test
    @DisplayName("참가자의 Deck를 생성할 때 오류 발생 안함")
    void initial_deck_create_success() {
        assertDoesNotThrow(
                () -> Deck.createParticipantDeck(deck)
        );
    }

    @Test
    @DisplayName("카드들의 점수 합을 구함")
    void calculate_card_score_sum() {
        CardCreationStrategy fixedCardCreationStrategy = new CardCreationStrategy() {
            @Override
            public List<Card> create() {
                Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
                Card clover5 = new Card(CardShape.클로버, CardContents.FIVE);
                Card diamondAce = new Card(CardShape.다이아몬드, CardContents.A);

                return List.of(spadeJ, clover5, diamondAce);
            }
        };
        Deck deck = Deck.createDeck(fixedCardCreationStrategy);
        assertThat(deck.calculateCardScoreSum()).isEqualTo(16);
    }

    @Test
    @DisplayName("덱에 카드 한장을 추가함")
    void add_card() {
        // given
        CardCreationStrategy gameStrategy = new CardCreationStrategy() {
            @Override
            public List<Card> create() {
                Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
                Card clover5 = new Card(CardShape.클로버, CardContents.FIVE);
                Card diamond3 = new Card(CardShape.다이아몬드, CardContents.THREE);

                return new ArrayList<>(List.of(spadeJ, clover5, diamond3));
            }
        };
        Deck gameDeck = Deck.createDeck(gameStrategy);

        CardCreationStrategy playerStrategy = new CardCreationStrategy() {
            @Override
            public List<Card> create() {
                Card spadeA = new Card(CardShape.스페이드, CardContents.A);

                return new ArrayList<>(List.of(spadeA));
            }
        };
        Deck playerDeck = Deck.createDeck(playerStrategy);

        // when
        int result = playerDeck.addCard(gameDeck.drawCard());
        int expected = 2;

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Nested
    class drawTest {
        @Test
        @DisplayName("Deck에서 카드를 한장 뽑아줌")
        void draw_card_success() {
            Card result = deck.drawCard();
            Card expected = new Card(CardShape.스페이드, CardContents.J);
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("Deck에서 0이하 혹은 남은 카드 이상의 숫자 선택 시도 시 오류 발생")
        void draw_card_fail() {
            deck.drawCard();
            deck.drawCard();

            assertThatThrownBy(
                    () -> deck.drawCard()
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
        }
    }

    @Nested
    class isBustTest {
        @Test
        @DisplayName("카드의 합이 21이 넘어가면 버스트로 판정")
        void isBust_true() {
            CardCreationStrategy fixedCardCreationStrategy = new CardCreationStrategy() {
                @Override
                public List<Card> create() {
                    Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
                    Card cloverQ = new Card(CardShape.클로버, CardContents.Q);
                    Card diamondK = new Card(CardShape.다이아몬드, CardContents.K);

                    return List.of(spadeJ, cloverQ, diamondK);
                }
            };
            Deck deck = Deck.createDeck(fixedCardCreationStrategy);
            assertTrue(deck.isBust());
        }

        @Test
        @DisplayName("카드의 합이 21이 넘어가면 버스트로 판정하지 않음")
        void isBust_false() {
            CardCreationStrategy fixedCardCreationStrategy = new CardCreationStrategy() {
                @Override
                public List<Card> create() {
                    Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
                    Card clover5 = new Card(CardShape.클로버, CardContents.FIVE);
                    Card diamond3 = new Card(CardShape.다이아몬드, CardContents.THREE);

                    return List.of(spadeJ, clover5, diamond3);
                }
            };
            Deck deck = Deck.createDeck(fixedCardCreationStrategy);
            assertFalse(deck.isBust());
        }
    }
}