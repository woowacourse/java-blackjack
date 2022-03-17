package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.PlayStatus;
import blackjack.domain.TestDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;

public class ParticipantTest {

    @Test
    @DisplayName("게임준비를 위해 가진 카드를 초기화한다.")
    void init() {
        // give
        CardDeck cardDeck = new CardDeck(new TestDeck());
        Participant player = new Player(Name.of("pobi"));

        // when
        player.init(cardDeck);
        int actual = player.getScore();

        // then
        assertThat(actual).isEqualTo(15);
    }

    @Test
    @DisplayName("카드를 받아 저장한다.")
    void hit() {
        // give
        Participant player = new Player(Name.of("pobi"));
        Card card = new Card(DIAMOND, JACK);

        // when
        player.hit(card);
        int actual = player.getScore();

        // then
        assertThat(actual).isEqualTo(10);
    }

    @Test
    @DisplayName("블랙잭을 검증한다.")
    void checkBlackjack() {
        //given
        Participant player = new Player(Name.of("pobi"));
        player.hit(new Card(DIAMOND, JACK));
        player.hit(new Card(DIAMOND, ACE));

        //when
        boolean actual = player.isBlackjack();

        //then
        assertThat(actual).isEqualTo(true);

    }

    @ParameterizedTest
    @CsvSource(value = {"TWO:BUST", "ACE:STAY"}, delimiter = ':')
    @DisplayName("카드의 합이 21을 초과하면 BUST를 반환한다.")
    void returnBust(CardNumber cardNumber, PlayStatus expected) {
        // give
        Participant player = new Player(Name.of("pobi"));
        player.hit(new Card(DIAMOND, JACK));
        player.hit(new Card(DIAMOND, QUEEN));
        player.hit(new Card(DIAMOND, cardNumber));

        // when
        PlayStatus actual = player.getStatus();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 목록 반환을 검증한다.")
    void getCards() {
        // give
        Participant player = new Player(Name.of("pobi"));
        CardDeck cardDeck = new CardDeck(new TestDeck());

        // when
        player.init(cardDeck);
        Set<Card> actual = player.getCards();

        // then
        assertThat(actual).isEqualTo(Set.of(new Card(DIAMOND, QUEEN), new Card(CLUB, FIVE)));
    }
}
