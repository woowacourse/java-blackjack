package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("플레이어에게 카드 2장을 나눠준다.")
    void dealCards() {
        //given
        final Players players = Players.from(List.of("레디", "제제"));
        final CardDeck cardDeck = CardDeck.generate();
        final Dealer dealer = new Dealer(players, cardDeck);

        //when
        dealer.startDeal();
        List<Player> names = players.getNames();

        //then
        boolean allMatch = names.stream()
                .allMatch(player -> player.getPacketSize() == 2);

        Assertions.assertThat(allMatch).isTrue();
    }
}
