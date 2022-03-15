package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.PlayStatus;
import blackjack.domain.TestDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;

class PlayerTest {

    @Test
    @DisplayName("카드를 받아 저장한다.")
    void hit() {
        // give
        Player player = new Player("pobi");
        Card card = new Card(DIAMOND, JACK);

        // when
        player.hit(card);
        int actual = player.getScore();

        // then
        assertThat(actual).isEqualTo(10);
    }

    @ParameterizedTest
    @CsvSource(value = {"TWO:BUST", "ACE:HIT"}, delimiter = ':')
    @DisplayName("카드의 합이 21을 초과하면 BUST를 반환한다.")
    void returnBust(CardNumber cardNumber, PlayStatus expected) {
        // give
        Player player = new Player("pobi");
        player.hit(new Card(DIAMOND, JACK));
        player.hit(new Card(DIAMOND, QUEEN));
        player.hit(new Card(DIAMOND, cardNumber));

        // when
        PlayStatus actual = player.getStatus();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("게임준비를 위해 가진 카드를 초기화한다.")
    void init() {
        // give
        CardDeck cardDeck = new CardDeck(new TestDeck());
        Player player = new Player("pobi");

        // when
        player.init(cardDeck);
        int actual = player.getScore();

        // then
        assertThat(actual).isEqualTo(15);
    }

    @Test
    @DisplayName("상태를 STAY로 변경한다.")
    void stay() {
        // give
        Player player = new Player("pobi");

        // when
        player.stay();
        PlayStatus actual = player.getStatus();

        // then
        assertThat(actual).isEqualTo(PlayStatus.STAY);
    }
}
