package blackjack.domain;

import blackjack.domain.betting.BettingAreas;
import blackjack.domain.betting.BettingResult;
import blackjack.domain.betting.BettingYieldCalculator;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.result.GameResult;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Participants participants;
    private final GameTable gameTable;

    public BlackJackGame(Participants participants, BettingAreas bettingAreas) {
        this.participants = participants;
        this.gameTable = new GameTable(bettingAreas);
    }

    public void drawInitialCards() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participants.getAllParticipants().forEach(participant -> participant.receiveCard(gameTable.pickCard()));
        }
    }

    public void drawNewCard(Participant participant) {
        participant.receiveCard(gameTable.pickCard());
    }

    public boolean isDealerBlackJack(){
        return participants.getDealer().isBlackJack();
    }

    public GameResult getGameResult() {
        return new GameResult(participants.getDealer(), participants.getPlayers());
    }

    public BettingResult getBettingResult() {
        BettingYieldCalculator bettingYieldCalculator = new BettingYieldCalculator(participants.getDealer(), participants.getPlayers());
        return new BettingResult(bettingYieldCalculator.getPlayersYields(), gameTable.getBettingAreas());
    }

    public Participants getParticipants() {
        return participants;
    }
}
