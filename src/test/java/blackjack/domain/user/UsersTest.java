package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.CardFixtures;
import blackjack.domain.card.Deck;
import blackjack.domain.money.Money;

class UsersTest {

    @DisplayName("문자열 기반 Users 생성 검증")
    @Test
    public void testCreateUsers() {
        //given
        Map<String, String> inputNameAndMoney = Map.ofEntries(
            Map.entry("pobi", "1000"), Map.entry("jason", "1000")
        );
        //when
        Users users = Users.of(inputNameAndMoney);

        //then
        Assertions.assertAll(
                () -> assertThat(users).isNotNull(),
                () -> assertThat(users.getPlayers().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("유저들에게 초기 카드를 나눠준다.")
    public void testInitialDrawForUsers() {
        // given
        Deck deck = new Deck();
        Map<String, String> inputNameAndMoney = Map.ofEntries(
            Map.entry("pobi", "1000"), Map.entry("jason", "1000")
        );
        Users users = Users.of(inputNameAndMoney);
        users.drawInitialCardsPerUser(deck);

        // when
        int dealerSize = users.getDealer()
            .showInitCards()
            .size();

        List<Integer> playerSizes = users.getPlayers().stream()
            .map(player -> player.showInitCards().size())
            .collect(Collectors.toList());

        // then
        Assertions.assertAll(
            () -> assertThat(dealerSize).isEqualTo(1),
            () -> assertThat(playerSizes).containsSequence(2, 2)
        );
    }
}