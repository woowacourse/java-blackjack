package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("참가자 테스트")
class GamblerTest {

    private static final Card ACE = new Card(CardNumber.ACE, CardShape.CLOVER);

    @Test
    @DisplayName("게임 참가자는 모든 카드를 공개한다")
    void revealsAllCards() {
        Gambler gambler = new Gambler("두리", 0);

        Cards cards = new Cards(List.of(ACE, ACE));
        gambler.addCards(cards);

        List<Card> result = gambler.getOpenedCards();

        assertThat(result.size()).isEqualTo(2);
    }
}
