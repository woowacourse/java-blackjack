package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Number.JACK;
import static blackjack.domain.Number.KING;
import static blackjack.domain.Number.NINE;
import static blackjack.domain.Number.QUEEN;
import static blackjack.domain.Number.TWO;
import static blackjack.domain.Symbol.CLUB;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantTest {

    @DisplayName("참가자가 가지고 있는 카드의 합을 반환한다.")
    @Test
    void should_ReturnSumOfCards() {
        Participant participant = new Dealer();

        participant.take(new Card(SPADE, JACK));
        participant.take(new Card(CLUB, QUEEN));

        assertThat(participant.getSum()).isEqualTo(20);
    }

    @DisplayName("참가자 카드 중 ACE는 11을 기본값으로 한다.")
    @Test
    void should_defaultValueOfACE_Is_11() {
        Participant participant = new Dealer();

        participant.take(new Card(SPADE, ACE));
        participant.take(new Card(CLUB, QUEEN));

        assertThat(participant.getSum()).isEqualTo(21);
    }

    @DisplayName("참가자 카드 합이 21을 초과하면 ACE의 값을 1로 계산한다.")
    @Test
    void should_valueOfAce_Is_1_WhenSumOfCardsOver21() {
        Participant participant = new Dealer();

        participant.take(new Card(SPADE, ACE));
        participant.take(new Card(SPADE, KING));
        participant.take(new Card(CLUB, QUEEN));

        assertThat(participant.getSum()).isEqualTo(21);
    }

    @Test
    void blackJackTest() {
        Deck deck = new MockDeckGenerator(Lists.newArrayList(
                new Card(SPADE, ACE),
                new Card(SPADE, QUEEN)
        )).generate();
        Player player = new Player("a");
        player.handInitialCards(deck);
        assertThat(player.isBlackJack()).isTrue();
    }

    @Test
    void notBlackJackTest() {
        Deck deck = new MockDeckGenerator(Lists.newArrayList(
                new Card(SPADE, TWO),
                new Card(SPADE, NINE),
                new Card(SPADE, QUEEN)
        )).generate();
        Player player = new Player("a");
        player.handInitialCards(deck);
        player.take(deck.draw());
        assertThat(player.isBlackJack()).isFalse();
    }
}
