package domain.blackjackgame;

import domain.card.deck.CardDeck;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.math.BigDecimal;

public class BlackjackGame {
    private final CardDeck cardDeck;

    public BlackjackGame(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void initGame(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            participant.receiveInitialCards(cardDeck.draw(), cardDeck.draw());
        }
    }

    public void dealCardTo(Participant participant) {
        participant.receiveAdditionalCard(cardDeck.draw());
    }

    public GameResult createGameResult(Participants participants) {
        ProfitResultManager profitResultManager = new ProfitResultManager(participants.getDealer());

        GameResult gameResult = new GameResult();
        for (Player player : participants.getPlayers()) {
            BigDecimal profit = profitResultManager.calculateProfit(player);
            gameResult.record(player, profit);
        }
        return gameResult;
    }
}
