package service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.dto.BettingResultDto;
import utils.generator.CardsGenerator;
import view.OutputView;

public class BlackjackService {

    public Cards generateCards(CardsGenerator cardsGenerator) {
        return cardsGenerator.generateShuffledCards();
    }

    public void giveInitialedCard(Cards cards, Dealer dealer) {
        dealer.addInitializedCard(cards);
    }

    public Players createPlayers(List<String> names, Cards cards) {
        List<Player> playerList = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            player.addInitializedCard(cards);
            playerList.add(player);
        }
        return new Players(playerList);
    }

    public Dealer createDealer(Cards cards) {
        Dealer dealer = new Dealer();
        giveInitialedCard(cards, dealer);
        return dealer;
    }

    public void determineAdditionalCardOfDealer(Dealer dealer, Cards cards) {
        while (dealer.needAdditionalCard()) {
            dealer.add(cards.pop());
            OutputView.displayDealerCard();
        }
    }

    public void calculateBettingScore(Dealer dealer, Players players) {
        players.calculateScore(dealer,players);
    }

    public BettingResultDto toBettingResultDto(Dealer dealer, Players players) {
        // todo: 실제 값으로 넣기
        Map<String, Integer> dealerWinningMap = new LinkedHashMap<>();
        dealerWinningMap.put(Dealer.DEALER_NAME, dealer.getBettingScore());
        for  (Player player : players) {
            dealerWinningMap.put(player.getName(), player.getBettingScore());
        }
        return new BettingResultDto(dealerWinningMap);
    }

}
