package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.ManualDeckGenerator;

class DealerTest {

    private final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();

    private Deck generateDeck(final List<Card> initializedCards) {
        manualDeckGenerator.initCards(initializedCards);
        return Deck.generate(manualDeckGenerator);
    }


    @DisplayName("첫번째 카드 한장을 반환할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForGetFirstCardTest")
    void getFirstCardTest(final List<Card> initializedCard) {
        final Deck deck = generateDeck(initializedCard);
        final Dealer dealer = Dealer.readyToPlay(deck);

        final Card actualCard = dealer.getFirstCard();
        final Card expectedCard = initializedCard.get(0);
        assertThat(actualCard).isEqualTo(expectedCard);
    }

}
