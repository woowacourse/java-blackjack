package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DealerTest {

    @ParameterizedTest
    @CsvSource(value = {"TWO:true", "JACK,SIX:true", "JACK,QUEEN:false"}, delimiterString = ":")
    @DisplayName("딜러 카드 추가 가능 여부 판단 기능 테스트")
    void dealerAbleToAddCardTest(String rankNames, boolean expected) {
        // given
        List<Rank> dealerRanks = createRanks(rankNames);
        Dealer dealer = createDealerCardsOfRanks(dealerRanks);
        // when & then
        assertEquals(expected, dealer.ableToAddCard());
    }

    private static Dealer createDealerCardsOfRanks(List<Rank> ranks) {
        Dealer dealer = new Dealer();
        ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .forEach(dealer::addCard);
        return dealer;
    }

    private static List<Rank> createRanks(String rankNames) {
        return Arrays.stream(rankNames.split(","))
                .map(Rank::valueOf)
                .toList();
    }
}
