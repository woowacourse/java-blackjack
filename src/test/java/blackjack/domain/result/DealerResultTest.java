package blackjack.domain.result;

import blackjack.domain.card.CardGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerResultTest {

    @Test
    @DisplayName("유저와 비교해서 딜러의 전적이 잘 계산되는지 확인한다.")
    public void calculateDealerWinResultTest() {
        Participants participants = new Participants();
        participants.addDealer();
        participants.distributeCard(new Deck(new CardGenerator()));
        participants.addUsers(new String[]{"스폰지밥", "뚱이", "다람이"});
        DealerResult dealerResult = new DealerResult(participants);
        assertThat(dealerResult.getCount().get(Result.WIN)).isEqualTo(3);
    }

    @Test
    @DisplayName("유저와 비교해서 딜러의 전적이 잘 계산되는지 확인한다.")
    public void calculateDealerLoseResultTest() {
        Participants participants = new Participants();
        participants.addUsers(new String[]{"스폰지밥", "뚱이", "다람이"});
        participants.distributeCard(new Deck(new CardGenerator()));
        participants.addDealer();
        DealerResult dealerResult = new DealerResult(participants);
        assertThat(dealerResult.getCount().get(Result.LOSE)).isEqualTo(3);
    }

    @Test
    @DisplayName("유저와 비교해서 딜러의 전적이 잘 계산되는지 확인한다.")
    public void calculateDealerDrawResultTest() {
        Participants participants = new Participants();
        participants.addDealer();
        participants.addUsers(new String[]{"스폰지밥", "뚱이", "다람이"});
        DealerResult dealerResult = new DealerResult(participants);
        assertThat(dealerResult.getCount().get(Result.DRAW)).isEqualTo(3);
    }

}
