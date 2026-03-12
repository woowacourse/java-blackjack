package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.state.GameState;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("Dealer를 생성할 때 오류 발생 안함")
    void dealer_create_success() {
        GameState dealerGameState = GameState.createDealerInitialGameState(
                Hand.of(
                        new Card(CardShape.스페이드, CardContents.J),
                        new Card(CardShape.클로버, CardContents.FIVE)

                )
        );
        assertDoesNotThrow(() -> Dealer.from(dealerGameState));
    }

    @Test
    @DisplayName("딜러는 카드의 합이 16 이하면 카드를 한 장 더 받을 수 있다")
    void addCard_success() {
        //given
        CardCreationStrategy onlyJCardCreation = () -> {
            Card spadeA = new Card(CardShape.스페이드, CardContents.J);
            return new ArrayDeque<>(List.of(spadeA));
        };
        Deck totalDeck = Deck.createDeck(onlyJCardCreation);

        GameState dealerGameState = GameState.createDealerInitialGameState(
                Hand.of(
                        new Card(CardShape.하트, CardContents.SIX),
                        new Card(CardShape.스페이드, CardContents.TEN)
                )
        );
        Dealer dealer = Dealer.from(dealerGameState);

        //when, then
        Assertions.assertDoesNotThrow(
                () -> dealer.addCard(totalDeck::drawCard)
        );
    }
}
