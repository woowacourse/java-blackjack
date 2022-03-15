package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.MatchRecord;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

class UsersTest {

    @DisplayName("문자열 기반 Users 생성 검증")
    @Test
    public void testCreateUsers() {
        //given
        List<String> names = List.of("pobi", "jason");

        //when
        Users users = Users.from(names);

        //then
        Assertions.assertAll(
                () -> assertThat(users).isNotNull(),
                () -> assertThat(users.getPlayers().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("플레이어의 승패를 구할 수 있다.")
    public void testCreatePlayerMatchRecords() {
        // given
        Deck deck = new Deck();
        List<String> names = List.of("pobi", "jason");
        Users users = Users.from(names);

        // when
        users.getDealer().receiveCard(new Card(Suit.CLOVER, Denomination.ACE));

        // then
        Map<Player, MatchRecord> playerMatchRecords = users.createPlayerMatchRecords();
        assertThat(playerMatchRecords.values()).allMatch(matchRecord -> matchRecord == MatchRecord.LOSS);
    }

}