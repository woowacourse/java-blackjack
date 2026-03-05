package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("딜러는 카드의 합이 16 이하면 카드를 한 장 더 받는다")
    void addCardWhenSumBelowMinimum() {
        //given
        CardCreationStrategy dealerCardCreationStrategy = () -> {
            Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
            return new ArrayList<>(List.of(spadeJ));
        };
        CardCreationStrategy totalCardCreationStrategy = () -> {
            Card spadeA = new Card(CardShape.스페이드, CardContents.A);
            Card heartA = new Card(CardShape.하트, CardContents.TWO);
            return new ArrayList<>(List.of(spadeA, heartA));
        };
        Deck dealerDeck = Deck.createDeck(dealerCardCreationStrategy);
        Deck totalDeck = Deck.createDeck(totalCardCreationStrategy);
        Dealer dealer = new Dealer(dealerDeck);

        //when
        Optional<Card> result = dealer.addCardWhenSumBelowMinimum(totalDeck);

        //then
        Assertions.assertThat(result.isPresent()).isTrue();
    }
}
