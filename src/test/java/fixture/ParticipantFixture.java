package fixture;

import static fixture.CardFixture.카드;

import domain.card.Denomination;
import domain.participant.BetAmount;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.Arrays;
import java.util.List;

public class ParticipantFixture {
    public static Player 플레이어(String name) {
        return 플레이어(name, 10);
    }

    public static Player 플레이어(String name, int betAmount) {
        return new Player(new Name(name), new BetAmount(betAmount));
    }

    public static Participants 참가자들(String... playerNames) {
        List<Player> players = Arrays.stream(playerNames)
                .map(ParticipantFixture::플레이어)
                .toList();
        return new Participants(players);
    }

    public static Participants 참가자들(Player... players) {
        return new Participants(Arrays.asList(players));
    }

    public static Dealer 블랙잭_딜러() {
        return 딜러(Denomination.ACE, Denomination.KING);
    }

    public static Dealer 버스트_딜러() {
        return 딜러(Denomination.TEN, Denomination.JACK, Denomination.QUEEN);
    }

    public static Dealer 스테이_딜러() {
        return 딜러(Denomination.NINE, Denomination.EIGHT);
    }

    public static Dealer 딜러(Denomination... denominations) {
        Dealer dealer = new Dealer();
        for (Denomination denomination : denominations) {
            dealer.receiveAdditionalCard(카드(denomination));
        }
        return dealer;
    }
}
