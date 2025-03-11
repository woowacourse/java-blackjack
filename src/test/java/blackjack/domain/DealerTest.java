package blackjack.domain;

import blackjack.domain.card.CardPack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DealerTest {

    @Test
    @DisplayName("딜러는 한장의 카드만 공개한다")
    void test() {
        Players players = new Players(List.of());
        players.dealInitCardsToPlayers(new CardPack());
        int openedCardsSize = players.getDealer().getOpenedCards().size();
        Assertions.assertThat(openedCardsSize).isEqualTo(1);
    }
}
