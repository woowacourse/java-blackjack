package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.card.Cards;
import blackjack.domain.player.BetAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GamblerTest {

    @Test
    @DisplayName("게임 참가자는 모든 카드를 공개한다")
    void test() {
        Players players = new Players(new Dealer(new Cards()),
                List.of(
                        new Gambler(new PlayerName("비타"), new BetAmount(0), new Cards()),
                        new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards())
                ));
        players.dealInitCardsToPlayers(CardPack.createShuffled());
        int openedCardsSize = players.getGamblers().getFirst().getOpenedCards().size();
        int totalCardsSize = players.getGamblers().getFirst().getCards().size();

        Assertions.assertThat(openedCardsSize).isEqualTo(totalCardsSize);
    }
}
