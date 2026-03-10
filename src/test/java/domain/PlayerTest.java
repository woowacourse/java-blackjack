package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("Player를 생성할 때 오류 발생 안함")
    void player_create_success() {
        Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
        Card clover5 = new Card(CardShape.클로버, CardContents.FIVE);
        String name = "pobi";

        assertDoesNotThrow(
                () -> new Player(name, spadeJ, clover5)
        );
    }
//
//    @Test
//    @DisplayName("플레이어가 카드를 한 장 더 받는다")
//    void addCardWhenSumBelowMinimum() {
//        //given
//        Card expectResultCard = new Card(CardShape.스페이드, CardContents.A);
//        CardCreationStrategy playerCardCreationStrategy = () -> {
//            Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
//            return new ArrayList<>(List.of(spadeJ));
//        };
//        CardCreationStrategy totalCardCreationStrategy = () -> {
//            Card heartA = new Card(CardShape.하트, CardContents.TWO);
//            return new ArrayList<>(List.of(expectResultCard, heartA));
//        };
//        Deck playerDeck = Deck.createDeck(playerCardCreationStrategy);
//        String testPlayerName = "pobi";
//        Deck totalDeck = Deck.createDeck(totalCardCreationStrategy);
//        Player player = new Player(playerDeck, testPlayerName);
//
//        //when
//        Card resultCard = player.addCard(totalDeck).get();
//
//        //then
//        Assertions.assertThat(resultCard).isEqualTo(expectResultCard);
//    }
}