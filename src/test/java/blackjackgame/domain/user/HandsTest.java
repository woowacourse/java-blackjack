package blackjackgame.domain.user;

import static org.assertj.core.api.Assertions.*;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CloverCard;
import blackjackgame.domain.card.SpadeCard;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HandsTest {

    @DisplayName("생성 직후에는 패가 비어있다.")
    @Test
    void generateHands_emptyHands() {
        Hands hands = new Hands();

        assertThat(hands.size()).isZero();
    }

    @DisplayName("카드패에 카드를 한 장 넣는다.")
    @Test
    void add_oneCard() {
        Hands hands = new Hands();

        hands.add(SpadeCard.SPADE_KING);
        Card card = hands.getCards().get(0);

        assertThat(hands.size()).isEqualTo(1);
        assertThat(card).isSameAs(SpadeCard.SPADE_KING);
    }

    @DisplayName("카드패에 여러장의 카드를 넣는다.")
    @ParameterizedTest
    @MethodSource("twoCardsThreeCardsFourCards")
    void add_multipleCards(List<Card> cards) {
        Hands hands = new Hands();

        hands.add(cards);

        assertThat(hands.getCards()).isEqualTo(cards);
    }

    static Stream<List<Card>> twoCardsThreeCardsFourCards() {
        return Stream.of(
                new ArrayList<>(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_SEVEN)),
                new ArrayList<>(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_ACE, CloverCard.CLOVER_EIGHT)),
                new ArrayList<>(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_ACE, CloverCard.CLOVER_TEN, CloverCard.CLOVER_NINE))
        );
    }

}
