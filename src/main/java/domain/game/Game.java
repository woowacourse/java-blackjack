package domain.game;

import domain.betting.BettingAmount;
import domain.card.Card;
import domain.card.GameCards;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Participant;
import domain.player.ParticipantGameInfo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public static final int DEFAULT_START_CARD_COUNT = 2;
    private final Dealer dealer;
    private final Gamblers gamblers;
    private final GameCards gameCards;

    public Game(Map<String, BettingAmount> gamblersNameAndBettingInfo,
            int defaultCardSet) {
        this.dealer = new Dealer();
        this.gamblers = new Gamblers(gamblersNameAndBettingInfo);
        this.gameCards = new GameCards();
    }

    public void initializeGame() {
        for (int i = 0; i < DEFAULT_START_CARD_COUNT; i++) {
            dealer.addCard(gameCards.drawCard());
            gamblers.receiveCards(gameCards);
        }
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

    public Card pickCard() {
        return gameCards.drawCard();
    }

    public void drawCardTo(Participant participant) {
        participant.addCard(pickCard());
    }

    public List<Gambler> getGamblersList() {
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
