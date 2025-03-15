package domain.participant;

import static domain.participant.ParticipantFactory.createDealerCardsOfRanks;
import static domain.participant.ParticipantFactory.createRanks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Rank;
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
}
