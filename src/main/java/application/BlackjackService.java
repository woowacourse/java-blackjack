package application;

import static constant.Word.*;

import domain.Card;
import domain.Deck;
import domain.GameTable;
import application.dto.RoundResult;
import domain.Member;
import domain.Role;
import domain.dto.GameResult;
import domain.dto.MemberStatus;
import java.util.List;
import java.util.stream.Stream;

public class BlackjackService {

    private final GameTable gameTable;
    private final Deck deck;

    public BlackjackService(GameTable gameTable, Deck deck) {
        this.gameTable = gameTable;
        this.deck = deck;
        this.deck.init();
    }

    public void joinPlayerToGame(List<String> players) {
        gameTable.joinMember(new Member(DEALER.format(), Role.DEALER));

        players.forEach(name -> gameTable.joinMember(new Member(name, Role.PLAYER)));

        List<String> allParticipants = Stream.concat(Stream.of(DEALER.format()), players.stream()).toList();

        allParticipants.forEach(name -> {
            for (int i = 0; i < 2; i++) {
                gameTable.draw(name, deck.draw());
            }
        });
    }

    public RoundResult startOneRound(String memberName) {
        List<Card> playerCards = gameTable.draw(memberName, deck.draw());

        boolean isBust = gameTable.checkBust(memberName);

        return new RoundResult(playerCards, isBust);
    }

    public boolean checkDealerDrawable() {
        return gameTable.draw(deck.draw());
    }

    public List<MemberStatus> getMemberStatuses() {
        return gameTable.checkMemberStatuses();
    }

    public List<GameResult> getGameResults() {
        return gameTable.checkGameResult();
    }
}
