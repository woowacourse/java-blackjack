package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("Player를 생성할 때 오류 발생 안함")
    void player_create_success() {
        CardCreationStrategy fixedCardCreationStrategy = new CardCreationStrategy() {
            @Override
            public List<Card> create() {
                Card spadeJ = new Card(CardShape.SPADE, CardContents.J);
                Card clover5 = new Card(CardShape.CLOVER, CardContents.FIVE);

                return new ArrayList<>(List.of(spadeJ, clover5));
            }
        };
        Deck deck = Deck.createDeck(fixedCardCreationStrategy);
        Deck participantDeck = Deck.createParticipantDeck(deck);
        String name = "pobi";

        assertDoesNotThrow(
                () -> new Player(participantDeck, name)
        );
    }

    @Test
    @DisplayName("플레이어가 카드를 한 장 더 받는다")
    void addCardWhenSumBelowMinimum() {
        //given
        Card expectResultCard = new Card(CardShape.SPADE, CardContents.A);
        CardCreationStrategy playerCardCreationStrategy = () -> {
            Card spadeJ = new Card(CardShape.SPADE, CardContents.J);
            return new ArrayList<>(List.of(spadeJ));
        };
        CardCreationStrategy totalCardCreationStrategy = () -> {
            Card heartA = new Card(CardShape.HEART, CardContents.TWO);
            return new ArrayList<>(List.of(expectResultCard, heartA));
        };
        Deck playerDeck = Deck.createDeck(playerCardCreationStrategy);
        String testPlayerName = "pobi";
        Deck totalDeck = Deck.createDeck(totalCardCreationStrategy);
        Player player = new Player(playerDeck, testPlayerName);

        //when
        Card resultCard = player.addCard(totalDeck).get();

        //then
        Assertions.assertThat(resultCard).isEqualTo(expectResultCard);
    }
}
