package blackjack.domain;

import static blackjack.domain.Letter.ACE;
import static blackjack.domain.Letter.JACK;
import static blackjack.domain.Letter.KING;
import static blackjack.domain.Letter.QUEEN;
import static blackjack.domain.Symbol.CLUB;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantTest {

    private Participant player;

    @BeforeEach
    void setParticipant() {
        player = new Player("test");
    }

    @DisplayName("참가자는 카드를 받으면 카드의 수가 1 증가한다.")
    @Test
    void should_HasSize_1Increased() {
        int previousSize = player.getCards().size();

        Card card = new Card(SPADE, JACK);
        player.take(card);
        int currentSize = player.getCards().size();

        assertThat(currentSize).isEqualTo(previousSize + 1);
    }

    @DisplayName("참가자는 카드를 받으면 마지막 위치에 저장한다.")
    @Test
    void should_addCard_At_LastIndex() {
        Card card = new Card(SPADE, JACK);
        player.take(card);

        List<Card> cards = player.getCards();
        Card lastCard = cards.get(cards.size() - 1);
        assertThat(lastCard).isEqualTo(card);
    }

    @DisplayName("참가자가 가지고 있는 카드의 합을 반환한다.")
    @Test
    void should_ReturnSumOfCards() {
        player.take(new Card(SPADE, JACK));
        player.take(new Card(CLUB, QUEEN));

        assertThat(player.computeSumOfCards()).isEqualTo(20);
    }

    @DisplayName("참가자 카드 중 ACE는 11을 기본값으로 한다.")
    @Test
    void should_defaultValueOfACE_Is_11() {
        player.take(new Card(SPADE, ACE));
        player.take(new Card(CLUB, QUEEN));

        assertThat(player.computeSumOfCards()).isEqualTo(21);
    }

    @DisplayName("참가자 카드 합이 21을 초과하면 ACE의 값을 1로 계산한다.")
    @Test
    void should_valueOfAce_Is_1_WhenSumOfCardsOver21() {
        player.take(new Card(SPADE, ACE));
        player.take(new Card(SPADE, KING));
        player.take(new Card(CLUB, QUEEN));

        assertThat(player.computeSumOfCards()).isEqualTo(21);
    }
}
