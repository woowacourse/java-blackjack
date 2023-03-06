import domain.Card;
import domain.Dealer;
import domain.PlayerName;
import domain.TrumpCardNumber;
import domain.Participant;
import domain.Player;
import domain.PlayerNames;
import domain.Participants;
import domain.Result;
import domain.TrumpCardType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private static final Card VALUE_TEN = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.TEN);
    private static final Card VALUE_NINE = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.NINE);
    private static final Card VALUE_EIGHT = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.EIGHT);

    @DisplayName("딜러의 카드 합을 기준으로 플레이어의 승패를 결정한다.")
    @Test
    void resultSuccessTest() {
        Dealer dealer = new Dealer(new PlayerName("딜러"));
        Participants participants = Participants.from(PlayerNames.from(List.of("pobi", "crong")));
        Player pobi = participants.getPlayers().get(0);
        Player crong = participants.getPlayers().get(1);

        giveCardsTo(dealer, List.of(VALUE_TEN, VALUE_NINE)); // 19

        giveCardsTo(pobi, List.of(VALUE_TEN, VALUE_TEN)); // 20
        giveCardsTo(crong, List.of(VALUE_TEN, VALUE_EIGHT)); // 18

        dealer.decideDealerResultsAgainst(participants);

        Assertions.assertThat(dealer.getPlayerResultMap().values())
                .containsExactly(Result.LOSE, Result.WIN);
    }

    public void giveCardsTo(Participant participant, List<Card> cards){
        for (Card card : cards) {
            participant.receive(card);
        }
    }

}
