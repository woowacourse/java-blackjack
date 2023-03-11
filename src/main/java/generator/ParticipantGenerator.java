package generator;

import domain.Account;
import domain.Card;
import domain.Dealer;
import domain.DrawnCards;
import domain.Name;
import domain.Player;
import domain.Players;
import domain.Status;
import java.util.ArrayList;
import java.util.List;

public class ParticipantGenerator {

    private ParticipantGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static Players createPlayers(final List<Name> playerNames, final List<Account> playerAccounts) {
        List<Card> emptyCards = new ArrayList<>();
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(
                    new Status(playerNames.get(i), playerAccounts.get(i)),
                    new DrawnCards(emptyCards))
            );
        }

        return new Players(players);
    }

    public static Dealer createDealer() {
        List<Card> emptyCards = new ArrayList<>();
        return new Dealer(new DrawnCards(emptyCards));
    }
}
