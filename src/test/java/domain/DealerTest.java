package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("Dealer를 생성할 때 오류 발생 안함")
    void dealer_create_success() {
        CardCreationStrategy fixedCardCreationStrategy = new CardCreationStrategy() {
            @Override
            public List<Card> create() {
                Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
                Card clover5 = new Card(CardShape.클로버, CardContents.FIVE);

                return new ArrayList<>(List.of(spadeJ, clover5));
            }
        };
        Deck deck = Deck.createDeck(fixedCardCreationStrategy);
        Deck participantDeck = Deck.createParticipantDeck(deck);
        assertDoesNotThrow(
                () -> new Dealer(participantDeck)
        );
    }

    @Test
    @DisplayName("딜러는 카드의 합이 16 이하면 카드를 한 장 더 받는다")
    void addCard_Dealer_success() {
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
        Optional<Card> result = dealer.addCard(totalDeck);

        //then
        Assertions.assertThat(result.isPresent()).isTrue();
    }


    @Test
    @DisplayName("딜러는 카드의 합이 16 이상 -> Optional<Empty>를 반환")
    void addCard_Dealer_Optional_empty() {
        //given
        CardCreationStrategy dealerCardCreationStrategy = () -> {
            Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
            Card heartJ = new Card(CardShape.하트, CardContents.J);
            return new ArrayList<>(List.of(spadeJ, heartJ));
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
        Optional<Card> result = dealer.addCard(totalDeck);

        //then
        Assertions.assertThat(result.isPresent()).isFalse();
    }
}
