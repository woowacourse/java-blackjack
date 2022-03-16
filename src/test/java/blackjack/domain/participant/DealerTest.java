package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러가 보유한 숫자의 합이 16초과인 경우 false를 반환한다.")
    void checkUpperSumStandardTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.EIGHT, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        Dealer dealer = new Dealer();
        dealer.receiveCard(deck);
        dealer.receiveCard(deck);

        assertThat(dealer.checkUnderScoreStandard()).isFalse();
    }

    @Test
    @DisplayName("딜러가 보유한 숫자의 합이 16이하인 경우 true를 반환한다.")
    void checkUnderSumStandard() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.SIX, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        Dealer dealer = new Dealer();
        dealer.receiveCard(deck);
        dealer.receiveCard(deck);

        assertThat(dealer.checkUnderScoreStandard()).isTrue();
    }
}
