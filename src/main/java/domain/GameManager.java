package domain;

import util.CardsMaker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {

    private static final int INITIAL_CARD_COUNT = 2;

    private final CardDistributor cardDistributor;
    private final Participants participants;

    public GameManager(List<String> playerNames) {
        cardDistributor = new CardDistributor(CardsMaker.generate());
        participants = Participants.of(playerNames, cardDistributor);
    }

    public List<Player> getPlayers() {
        return participants.getPlayers()
                .getPlayers();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames()
                .stream()
                .map(Name::getValue)
                .collect(Collectors.toList());
    }

    public void distributeCardToPlayer(Player player) {
        player.pick(cardDistributor.distribute());
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public String getDealerName() {
        return participants.getDealerName()
                .getValue();
    }

    public List<Card> showOneCardOfDealerCards() {
        return List.of(participants.showOneCardOfDealerCards());
    }

    public void drawUntilDealerNoMoreCard() {
        Dealer dealer = participants.getDealer();

        while (dealer.isMoreCardAble()) {
            dealer.pick(cardDistributor.distribute());
        }
    }

    public Map<Name, ResultAmount> getCameResultAmount(BettingAmount bettingAmount) {
        return new GameResultAmount(participants.getResult(), bettingAmount.getBettingAmount())
                .getResultOfBetting();
    }

    public int getDealerMoreCardCount() {
        return participants.getDealerCardsCount() - INITIAL_CARD_COUNT;
    }

}
