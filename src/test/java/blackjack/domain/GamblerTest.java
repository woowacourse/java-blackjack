package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GamblerTest {

    @Test
    @DisplayName("게임 참가자는 모든 카드를 공개한다")
    void test() {
        Players players = new Players(List.of(new Gambler("비타"), new Gambler("두리")));
        players.dealInitCardsToPlayers(new CardPack());
        int openedCardsSize = players.getGamblers().getFirst().getOpenedCards().size();
        int totalCardsSize = players.getGamblers().getFirst().getCards().size();

        Assertions.assertThat(openedCardsSize).isEqualTo(totalCardsSize);
    }
}
