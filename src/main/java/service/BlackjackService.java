package service;

import java.util.ArrayList;
import java.util.List;

import domain.Deck;
import domain.Game;
import domain.Player;
import domain.Players;
import domain.dto.CardContentDto;
import utils.generator.CardsGenerator;
import utils.generator.ShuffledCardsGenerator;

public class BlackjackService {

    public Deck generateCards() {
        CardsGenerator cardsGenerator = new ShuffledCardsGenerator();
        return new Deck(cardsGenerator);
    }

    public Players createPlayers(List<String> names, Deck deck) {
        List<Player> playerList = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            player.addInitializedCard(deck);
            playerList.add(player);
        }
        return new Players(playerList);
    }

    public Game createGame(Deck deck, Players players) {
        return new Game(deck, players);
    }

    public List<CardContentDto> getCardContentDtos(Game game) {
        List<CardContentDto> firstCardContents = new ArrayList<>();
        firstCardContents.add(new CardContentDto(Game.DEALER_NAME, List.of(game.getDealerFirstCard())));
        for (Player player : game.getPlayers()) {
            firstCardContents.add(new CardContentDto(player.getName(), player.getCards()));
        }
        return firstCardContents;
    }

//    public void giveInitialedCard(Cards cards, Dealer dealer) {
//        dealer.addInitializedCard(cards);
//    }

//    public Dealer createDealer(Cards cards) {
//        Dealer dealer = new Dealer();
//        giveInitialedCard(cards, dealer);
//        return dealer;
//    }
//
//    public void determineAdditionalCardOfDealer(Dealer dealer, Cards cards) {
//        while (dealer.needAdditionalCard()) {
//            dealer.add(cards.pop());
//            OutputView.displayDealerCard();
//        }
//    }
//
//    public void calculateBettingScore(Dealer dealer, Players players) {
//        players.calculateScore(dealer,players);
//    }
//
//    public BettingResultDto toBettingResultDto(Dealer dealer, Players players) {
//        // todo: 실제 값으로 넣기
//        Map<String, Integer> dealerWinningMap = new LinkedHashMap<>();
//        dealerWinningMap.put(Dealer.DEALER_NAME, dealer.getBettingScore());
//        for  (Player player : players) {
//            dealerWinningMap.put(player.getName(), player.getBettingScore());
//        }
//        return new BettingResultDto(dealerWinningMap);
//    }

}
