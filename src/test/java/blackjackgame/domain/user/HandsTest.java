package blackjackgame.domain.user;

import static blackjackgame.domain.Fixtures.ACE_ACE_ACE_ACE_CARDS;
import static blackjackgame.domain.Fixtures.ACE_KING_CARDS;
import static blackjackgame.domain.Fixtures.EIGHT_NINE_CARDS;
import static blackjackgame.domain.Fixtures.JACK_KING_ACE_CARDS;
import static blackjackgame.domain.Fixtures.SIX_FOUR_CARDS;
import static org.assertj.core.api.Assertions.*;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.SpadeCard;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HandsTest {
    private Hands hands;

    @BeforeEach
    void setUp() {
        hands = new Hands();
    }

    @DisplayName("생성 직후에는 패가 비어있다.")
    @Test
    void generateHands_emptyHands() {
        assertThat(hands.size()).isZero();
    }

    @DisplayName("카드패에 카드를 한 장 넣는다.")
    @Test
    void add_oneCard() {
        hands.add(SpadeCard.SPADE_KING);
        Card card = hands.getCards().get(0);

        assertThat(hands.size()).isEqualTo(1);
        assertThat(card).isSameAs(SpadeCard.SPADE_KING);
    }

    @DisplayName("카드패에 여러장의 카드를 넣는다.")
    @ParameterizedTest
    @MethodSource("twoCardsThreeCardsFourCards")
    void add_multipleCards(List<Card> cards) {
        hands.add(cards);

        assertThat(hands.getCards()).isEqualTo(cards);
    }

    static Stream<List<Card>> twoCardsThreeCardsFourCards() {
        return Stream.of(SIX_FOUR_CARDS, EIGHT_NINE_CARDS, JACK_KING_ACE_CARDS);
    }

    @DisplayName("패에 있는 에이스 카드의 개수를 반환한다.")
    @Test
    void countOfAce_aceAceAceAce() {
        hands.add(ACE_ACE_ACE_ACE_CARDS);

        assertThat(hands.countOfAce()).isEqualTo(4);
    }

    @DisplayName("에이스를 11로 계산하며 패에 있는 카드의 숫자 합을 구한다.")
    @Test
    void sum_calculateAceAsEleven() {
        hands.add(ACE_KING_CARDS);

        assertThat(hands.sum()).isEqualTo(21);
    }
}
