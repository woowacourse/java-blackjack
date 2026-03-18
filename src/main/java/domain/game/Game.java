package domain.game;

import domain.betting.BettingAmount;
import domain.card.GameCards;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.GamblersFactory;
import domain.player.Participant;
import domain.player.ParticipantGameInfo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final Dealer dealer;
    private final Gamblers gamblers;
    private final GameCards gameCards;

    public Game(Map<String, BettingAmount> gamblersNameAndBettingInfo) {
        this.dealer = new Dealer();
        List<Gambler> gamblers = GamblersFactory.createGamblers(gamblersNameAndBettingInfo);
        this.gamblers = new Gamblers(gamblers);
        this.gameCards = new GameCards();
    }

    public void initializeGame() {
        dealer.receiveInitialCards(gameCards);
        gamblers.receiveInitialCards(gameCards);
    }

    public Map<String, List<String>> getInitialParticipantsHandInfo() {
        Map<String, List<String>> info = new LinkedHashMap<>();
        info.put(dealer.getName(), List.of(dealer.getFirstHand()));
        info.putAll(gamblers.getHandsInfo());
        return info;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public boolean shouldDealerDraw() {
        return dealer.isBelowDrawThreshold();
    }

    public void drawCardTo(Participant participant) {
        participant.addCard(gameCards.drawCard());
    }

    public List<Gambler> getGamblers() {
        return gamblers.getGamblers();
    }

    public List<ParticipantGameInfo> getParticipantGameInfos() {
        List<ParticipantGameInfo> gameInfo = new ArrayList<>();
        gameInfo.add(dealer.getParticipantGameInfo());
        gameInfo.addAll(gamblers.getParticipantGameInfos());
        return gameInfo;
    }

    public int getDealerHandSize() {
        return dealer.getCardSize();
    }

    public List<Integer> getGamblersHandSize() {
        return gamblers.getHandSize();
    }

    public GamblersGameResult getResult() {
        return new GamblersGameResult(dealer, gamblers);
    }
}
