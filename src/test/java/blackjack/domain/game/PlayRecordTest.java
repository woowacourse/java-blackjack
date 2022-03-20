package blackjack.domain.game;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.state.Bet;

public class PlayRecordTest {
    Player player = new Player(Name.of("pobi"), new Bet(1));

    @BeforeEach
    void setUp() {
        player.draw(new Card(DIAMOND, KING));
        player.draw(new Card(DIAMOND, SEVEN));
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE:LOSS", "TWO:PUSH", "FIVE:LOSS"}, delimiter = ':')
    @DisplayName("딜러가 버스트 하지 않은 경우 플레이어의 승패 여부를 반환한다.")
    void getRecord_dealerNotBust(CardNumber cardNumber, PlayRecord expected) {
        // give
        Dealer dealer = new Dealer();
        dealer.init(new Card(DIAMOND, JACK), new Card(DIAMOND, NINE));
        player.draw(new Card(DIAMOND, cardNumber));

        // when
        PlayRecord actual = player.getRecord(dealer);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"THREE:WIN", "FIVE:LOSS"}, delimiter = ':')
    @DisplayName("딜러가 버스트한 경우 플레이어의 승패 여부를 반환한다.")
    void getRecord_dealerBust(CardNumber cardNumber, PlayRecord expected) {
        // give
        Dealer dealer = new Dealer();
        dealer.init(new Card(DIAMOND, JACK), new Card(DIAMOND, NINE));
        dealer.draw(new Card(DIAMOND, THREE));
        player.draw(new Card(DIAMOND, cardNumber));

        // when
        PlayRecord actual = player.getRecord(dealer);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
