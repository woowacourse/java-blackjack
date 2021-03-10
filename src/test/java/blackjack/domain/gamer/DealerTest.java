package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Cards;
import blackjack.domain.gametable.GameTable;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participant;
import blackjack.domain.utils.FixedCardDeck;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("dealer 초기에 카드 두장을 갖고 있는지 확인")
    void create3() {
        Participant dealer = new Dealer();
        final GameTable gameTable = new GameTable(dealer, new FixedCardDeck());

        List<Card> playerCards = dealer.getUnmodifiableCards();

        assertThat(playerCards).hasSize(2);
    }

    @Test
    @DisplayName("dealer 카드 추가 지급")
    void add_card() {
        Participant dealer = new Dealer();
        final GameTable gameTable = new GameTable(dealer, new FixedCardDeck());
        gameTable.giveCard(dealer);

        assertThat(dealer.getUnmodifiableCards()).hasSize(3);
    }

    @Test
    @DisplayName("Dealer 16 초과한 경우")
    void sum_cards_for_result1() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.KING),
            Card.from(Suits.CLOVER, Denominations.KING));
        Participant dealer = new Dealer(new Cards(cards));

        assertThat(dealer.isNotAbleToTake()).isTrue();
    }

}
