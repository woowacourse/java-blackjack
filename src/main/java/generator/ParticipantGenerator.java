package generator;

import domain.card.Card;
import domain.card.DrawnCards;
import domain.participants.Account;
import domain.participants.Dealer;
import domain.participants.Names;
import domain.participants.Player;
import domain.participants.Players;
import domain.participants.Status;
import java.util.ArrayList;
import java.util.List;

public class ParticipantGenerator {

    private ParticipantGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static Players createPlayers(final Names playerNames, final List<Account> playerAccounts) {
        List<Card> emptyCards = new ArrayList<>();
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playerNames.getNamesSize(); i++) {
            players.add(new Player(
                    new Status(playerNames.findByIndex(i), playerAccounts.get(i)),
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
