package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.Suit.SPADE;
import static blackjack.fixtures.BlackjackFixtures.SPADE_ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("스페이드 에이스를 생성한다.")
    void createSpadeAce() {
        assertThat(SPADE_ACE).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않는 모양으로 생성시 예외를 발생한다.")
    void throwExceptionNotExistedSuit() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Card.valueOf(null, ACE))
            .withMessage("존재하지 않는 카드입니다.");
    }

    @Test
    @DisplayName("존재하지 않는 카드 숫자로 생성시 예외를 발생한다.")
    void throwExceptionNotExistedCardNumber() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Card.valueOf(SPADE, null))
            .withMessage("존재하지 않는 카드입니다.");
    }

    @Test
    @DisplayName("같은 모양과 숫자의 카드는 동일하다.")
    void equalsCardsHashcode() {
        assertThat(SPADE_ACE.hashCode() == SPADE_ACE.hashCode()).isTrue();
    }

    @Test
    @DisplayName("같은 모양과 숫자의 카드는 동등하다.")
    void equalsCard() {
        assertThat(SPADE_ACE).isSameAs(SPADE_ACE);
    }

    @Test
    @DisplayName("52장의 서로 다른 카드를 생성한다.")
    void createCards() {
        List<Card> cards = Card.createDeck();

        assertThat(Set.copyOf(cards)).hasSize(52);
    }
}
