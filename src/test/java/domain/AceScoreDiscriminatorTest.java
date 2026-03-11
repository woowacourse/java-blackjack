package domain;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AceScoreDiscriminatorTest {

    private final AceScoreDiscriminator discriminator = new AceScoreDiscriminator();

    @Test
    @DisplayName("Ace 1개 있을 때 값 판별 잘함")
    void calculateAceCardSum_success() {
        //given
        List<Card> testCards = List.of(
                new Card(CardShape.SPADE, CardContents.A),
                new Card(CardShape.CLOVER, CardContents.TEN)
        );
        int testSumExceptAce = 10;
        int expectedAceCardsSum = 11;

        //when
        int result = discriminator.calculateAceCardsSum(testCards, testSumExceptAce);

        //then
        Assertions.assertEquals(expectedAceCardsSum, result);
    }

    @Test
    @DisplayName("Ace 2개 이상 있을 때 값 판별 잘함")
    void calculateAceCardsSum_success() {
        //given
        List<Card> testCards = List.of(
                new Card(CardShape.SPADE, CardContents.A),
                new Card(CardShape.CLOVER, CardContents.A),
                new Card(CardShape.CLOVER, CardContents.TEN)
        );
        int testSumExceptAce = 10;
        int expectedAceCardsSum = 2;

        //when
        int result = discriminator.calculateAceCardsSum(testCards, testSumExceptAce);

        //then
        Assertions.assertEquals(expectedAceCardsSum, result);
    }

}
