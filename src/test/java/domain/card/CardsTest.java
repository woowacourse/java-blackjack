package domain.card;

import domain.CardShuffler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CardsTest {
    @Test
    @DisplayName("create()는 호출하면 52장의 카드 뭉치를 생성한다.")
    void create_whenCall_thenSuccess() {
        assertThatCode(() -> Cards.create())
                .doesNotThrowAnyException();

        assertThat(Cards.create())
                .isExactlyInstanceOf(Cards.class);
    }

    @Test
    @DisplayName("getShuffledCards()는 호출하면, 정해진 전략에 따라 섞인 카드 뭉치를 반환한다.")
    void getShuffledCards_whenCall_thenReturnShuffledCards() {
        // given
        final Cards cards = Cards.create();
        final CardShuffler cardShuffler = targetCard -> targetCard;

        // when
        List<Card> shuffledCards = cards.getShuffledCards(cardShuffler);

        // then
        assertThat(shuffledCards.size())
                .isEqualTo(52);

        assertThat(shuffledCards)
                .isEqualTo(shuffledCards);
    }
}
