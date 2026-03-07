package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @Test
    @DisplayName("생성 잘 한다")
    void ready_good() {
        //given
        List<String> testPlayerNames = List.of("pobi", "terry", "rati", "gump");
        CardCreationStrategy totalCardCreationStrategy = this::createSampleCards;

        //when, then
        assertDoesNotThrow(
                () -> Game.ready(testPlayerNames, totalCardCreationStrategy)
        );
    }

    @Test
    @DisplayName("초기 카드 배분 시 딜러는 1장, 플레이어는 2장씩 출력되어야 한다")
    void showInitialCardShareResult_success() {
        // given
        List<String> testPlayerNames = List.of("pobi", "terry", "rati", "gump");
        Game game = Game.ready(testPlayerNames, this::createSampleCards);

        // when
        Map<String, List<Card>> result = game.showInitialCardShareResult();

        // then
        assertThat(result.get("딜러").size()).isEqualTo(1);

        testPlayerNames.forEach(name ->
                assertThat(result.get(name).size()).isEqualTo(2)
        );

        System.out.println("========= 초기 배분 결과 =========");
        result.forEach((name, cards) -> {
            String cardInfo = cards.stream()
                    .map(Card::toString)
                    .collect(Collectors.joining(", "));
            System.out.printf("%s : [ %s ]\n", name, cardInfo);
        });
    }

    private List<Card> createSampleCards() {
        CardShape[] shapes = CardShape.values();
        CardContents[] contents = CardContents.values();

        List<Card> sampleCards = new ArrayList<>();
        for (CardShape cardShape : shapes) {
            for (CardContents content : contents) {
                sampleCards.add(new Card(cardShape, content));
            }
        }

        return sampleCards;
    }
}