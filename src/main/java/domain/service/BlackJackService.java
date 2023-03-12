package domain.service;

import domain.Card;
import domain.CardDistributor;
import domain.Dealer;
import domain.Name;
import domain.Participants;
import domain.Player;
import util.CardsMaker;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackService {

    private static final int INITIAL_CARD_COUNT = 2;

    private final CardDistributor cardDistributor;
    private final Participants participants;

    public BlackJackService(List<String> playerNames) {
        cardDistributor = new CardDistributor(CardsMaker.generate());
        participants = Participants.of(playerNames, cardDistributor);
    }

    public void betPlayers(List<Integer> bettingAmount) {
        participants.betPlayers(bettingAmount);
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

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public String getDealerName() {
        return participants.getDealerName()
                .getValue();
    }

    public Card distributeCard() {
        return cardDistributor.distribute();
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

    public int getDealerMoreCardCount() {
        return participants.getDealerCardsCount() - INITIAL_CARD_COUNT;
    }

}
