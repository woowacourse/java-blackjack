package blackjack.domain;

import blackjack.domain.card.CardPack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러는 한장의 카드만 공개한다")
    void test() {
        Players players = new Players();
        players.dealInitCardsToPlayers(new CardPack(new HighCardFirstShuffle()));
        int openedCardsSize = players.getDealer().getOpenedCards().size();
        Assertions.assertThat(openedCardsSize).isEqualTo(1);
    }
}
