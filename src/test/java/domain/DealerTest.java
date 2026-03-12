package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private static Hand dealerHand = Hand.of(
            new Card(CardShape.스페이드, CardContents.J),
            new Card(CardShape.클로버, CardContents.FIVE)
    );

    @Test
    @DisplayName("Dealer를 생성할 때 오류 발생 안함")
    void dealer_create_success() {
        assertDoesNotThrow(() -> Dealer.from(dealerHand));
    }

    @Test
    @DisplayName("딜러는 카드의 합이 16 이하면 카드를 한 장 더 받고 새로운 Dealer를 반환한다")
    void addCard_success() {
        //given
        CardCreationStrategy onlyJCardCreation = () -> {
            Card spadeA = new Card(CardShape.스페이드, CardContents.J);
            return new ArrayDeque<>(List.of(spadeA));
        };
        Deck totalDeck = Deck.createDeck(onlyJCardCreation);

        Hand dealerHandSixteen = Hand.of(
                new Card(CardShape.하트, CardContents.SIX),
                new Card(CardShape.스페이드, CardContents.TEN)
        );
        Dealer dealer = Dealer.from(dealerHandSixteen);

        //when
        Optional<Dealer> result = dealer.addCard(totalDeck::drawCard);

        //then
        assertTrue(result.isPresent());
    }
}
