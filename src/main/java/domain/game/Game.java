package domain.game;

import static util.Constants.DEFAULT_START_CARD_COUNT;

import domain.betting.Betting;
import domain.betting.BettingAmount;
import domain.card.Card;
import domain.card.GameCards;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.ParticipantGameInfo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final Dealer dealer;
    private final Gamblers gamblers;
    private final GameCards gameCards;

    public Game(String dealerName, Map<String, BettingAmount> gamblersNameAndBettingInfo, int DEFAULT_CARD_SET) {
        this.dealer = new Dealer(dealerName);
        this.gamblers = new Gamblers(gamblersNameAndBettingInfo);
        this.gameCards = new GameCards(DEFAULT_CARD_SET);
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

    public boolean shouldDealerDraw() {
        return dealer.isBelowDrawThreshold();
    }

    public void addDealerCard() {
        dealer.addCard(pickCard());
    }

    public Card pickCard() {
        return gameCards.drawCard();
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

    public GamblersGameResult getResult(){
        return new GamblersGameResult(dealer, gamblers);
    }
}
