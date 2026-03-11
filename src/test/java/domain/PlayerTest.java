package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("Player를 생성할 때 오류 발생 안함")
    void player_create_success() {
        Hand playerHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.J),
                new Card(CardShape.클로버, CardContents.K)
        );

        String name = "pobi";

        assertDoesNotThrow(
                () -> Player.from(name, playerHand)
        );
    }

    @Nested
    class hitTest {
        //given
        Hand playerHand = Hand.of(
                new Card(CardShape.스페이드, CardContents.J),
                new Card(CardShape.클로버, CardContents.FIVE)
        );
        String testName = "gump";
        Player testPlayer = Player.from(testName, playerHand);

        @Test
        @DisplayName("hit 할 수 있는 상태이면 hit를 진행한다")
        void hit_do() {
            //given
            Queue<Card> testDeck = new LinkedList<>(List.of(
                    new Card(CardShape.클로버, CardContents.SEVEN)
            ));
            Supplier<Card> testCardSupplier = () -> testDeck.poll();

            //when
            testPlayer.hit(testCardSupplier);

            //then
            Assertions.assertThat(testCardSupplier.get()).isNull();
        }

        @Test
        @DisplayName("hit 할 수 없는 상태이면 hit를 진행하지 않는다")
        void hit_do_not() {
            //given
            Queue<Card> testDeck = new LinkedList<>(List.of(
                    new Card(CardShape.하트, CardContents.TEN),
                    new Card(CardShape.클로버, CardContents.TEN)
            ));
            Supplier<Card> testCardSupplier = () -> testDeck.poll();
            testPlayer.hit(testCardSupplier);

            //when
            testPlayer.hit(testCardSupplier);

            //then
            Assertions.assertThat(testCardSupplier.get()).isNotNull();
        }

    }
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