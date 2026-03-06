package application;

import domain.Card;
import domain.Deck;
import domain.GameTable;
import application.dto.RoundResult;
import domain.dto.PlayerStatus;
import java.util.List;

public class BlackjackService {

    private final GameTable gameTable;
    private final Deck deck;

    public BlackjackService(GameTable gameTable, Deck deck) {
        this.gameTable = gameTable;
        this.deck = deck;
    }

    public RoundResult startOneRound(String memberName) {
        List<Card> playerCards = gameTable.draw(memberName, deck.draw());

        boolean isBust = gameTable.checkBust(memberName);

        return new RoundResult(playerCards, isBust);
    }

    public List<PlayerStatus> getPlayerStatuses() {
        return gameTable.checkPlayerStatuses();
    }

}
