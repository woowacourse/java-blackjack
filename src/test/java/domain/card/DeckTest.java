package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DeckTest {
    @Test
    @DisplayName("52장의 덱을 생성한다")
    void test_create() {
        var deck = new Deck();
        var cards = deck.getCards();

        assertThat(cards).hasSize(52);
    }

    @Test
    @DisplayName("4종의 그림으로 이뤄져있다")
    void test_has_four_faces() {
        var deck = new Deck();
        var cards = deck.getCards();
        var distinctFaces = cards.stream()
                .map(Card::getFace)
                .collect(Collectors.toSet());

        assertThat(distinctFaces).hasSize(4);
    }

    @ParameterizedTest(name = "각 그림마다 13장으로 이뤄져있다")
    @CsvSource({"SPADE", "CLOVER", "HEART", "DIAMOND"})
    void test_cards_every_face_13(Face face) {
        var deck = new Deck();
        var cards = deck.getCards();
        var cardsByFace = cards.stream()
                .filter(card -> card.getFace().equals(face))
                .collect(Collectors.toSet());

        assertThat(cardsByFace).hasSize(13);
    }

    @Test
    @DisplayName("카드 한 장을 뽑는다")
    void test_draw_a_card() {
        var deck = new Deck();
        deck.draw();

        assertThat(deck.getCards()).hasSize(51);
    }

    @Test
    @DisplayName("뽑힌 카드는 덱에서 제거된다")
    void test_drawn_card_is_removed() {
        var deck = new Deck();
        var card = deck.draw();

        assertThat(card).isNotIn(deck.getCards());
    }

    @Test
    @DisplayName("카드가 바닥났을 때 뽑으려고 하면 IllegalStateException을 던진다")
    void test_draw_from_empty_deck_throws() {
        var deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 모두 소진됐습니다.");
    }
}