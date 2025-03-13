package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;
import blackjack.domain.player.Gambler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GamblerTest {

    @Test
    @DisplayName("게임 참가자는 모든 카드를 공개한다")
    void test() {
        CardPack cardPack = new CardPack(new SortShuffle());
        Gambler gambler = new Gambler("비타");

        gambler.pushDealCard(cardPack, 2);
        List<Card> openCards = gambler.getOpenedCards();
        List<Card> allCards = gambler.getCards();

        assertThat(openCards.size())
                .isEqualTo(allCards.size());
    }
}
