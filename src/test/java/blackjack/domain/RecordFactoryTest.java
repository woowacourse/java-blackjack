package blackjack.domain;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

class RecordFactoryTest {

    Player player = new Player(Name.of("pobi"));

    @BeforeEach
    void setUp() {
        player.hit(new Card(DIAMOND, KING));
        player.hit(new Card(DIAMOND, SEVEN));
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE:LOSS", "TWO:PUSH", "FIVE:LOSS"}, delimiter = ':')
    @DisplayName("딜러가 버스트 하지 않은 경우 플레이어의 승패 여부를 반환한다.")
    void getRecord_dealerNotBust(CardNumber cardNumber, PlayRecord expected) {
        // give
        int dealerScore = 19;
        RecordFactory factory = new RecordFactory(dealerScore);
        player.hit(new Card(DIAMOND, cardNumber));

        // when
        PlayRecord actual = factory.getPlayerRecords(List.of(player)).get(player.getName());

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"THREE:WIN", "FIVE:LOSS"}, delimiter = ':')
    @DisplayName("딜러가 버스트한 경우 플레이어의 승패 여부를 반환한다.")
    void getRecord_dealerBust(CardNumber cardNumber, PlayRecord expected) {
        // give
        int dealerScore = 22;
        RecordFactory factory = new RecordFactory(dealerScore);
        player.hit(new Card(DIAMOND, cardNumber));

        // when
        PlayRecord actual = factory.getPlayerRecords(List.of(player)).get(player.getName());

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("2명의 플레이어의 전적을 반환한다.")
    void getRecords() {
        //given
        int dealerScore = 16;
        RecordFactory factory = new RecordFactory(dealerScore);

        //when
        Map<Name, PlayRecord> actual = factory.getPlayerRecords(List.of(player,
            new Player(Name.of("jason"))));

        //then
        assertThat(actual).isEqualTo(Map.of(Name.of("pobi"), PlayRecord.WIN,
            Name.of("jason"), PlayRecord.LOSS));
    }
}
