package blackjack.controller;

import blackjack.model.Betting;
import blackjack.model.Card;
import blackjack.model.CardDeck;
import blackjack.model.GameResult;
import blackjack.model.Result;
import blackjack.model.dto.CardDTO;
import blackjack.model.dto.PlayerDTO;
import blackjack.model.dto.PlayersDTO;
import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;
import blackjack.model.player.Gamers;
import blackjack.model.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void play() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(List.of(cardDeck.selectCard(), cardDeck.selectCard()));
        Gamers gamers = new Gamers(createGamerInfo(), cardDeck);
        OutputView.printOpenCard(createPlayerDto(dealer, dealer.openCards()), createPlayersDto(gamers));
        takeCards(cardDeck, dealer, gamers);

        GameResult gameResult = new GameResult();
        matchAndUpdateResult(dealer, gamers, gameResult);
        displayResult(dealer, gamers, gameResult);
    }

    private Map<String, Betting> createGamerInfo() {
        List<String> GamerNames = InputView.inputNames();
        Map<String, Betting> gamerInfo = new LinkedHashMap<>();
        for (String name : GamerNames) {
            gamerInfo.put(name, getGamerBettingMoney(name));
        }
        return gamerInfo;
    }

    private Betting getGamerBettingMoney(String name) {
        Betting money;
        try {
            money = new Betting(InputView.inputBettingMoney(name));
        } catch (IllegalStateException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return getGamerBettingMoney(name);
        }
        return money;
    }

    private PlayerDTO createPlayerDto(Player player, List<Card> playerCards) {
        List<CardDTO> cards = playerCards.stream()
                .map(card -> new CardDTO(card.getRank().name(), card.getSuit().name()))
                .collect(Collectors.toList());
        return new PlayerDTO(player.getName(), player.score().getValue(), cards);
    }

    private PlayersDTO createPlayersDto(Gamers gamers) {
        List<PlayerDTO> players = gamers.getGamers().stream()
                .map(gamer -> createPlayerDto(gamer, gamer.getCards().getEachCard()))
                .collect(Collectors.toList());
        return new PlayersDTO(players);
    }

    private void takeCards(CardDeck cardDeck, Dealer dealer, Gamers gamers) {
        for (Gamer gamer : gamers.getGamers()) {
            takeGamerCard(gamer, cardDeck);
        }
        takeDealerCard(dealer, cardDeck);
    }

    private void takeGamerCard(Gamer gamer, CardDeck cardDeck) {
        while (gamer.isHittable() && isKeepTakeCard(gamer)) {
            gamer.take(cardDeck.selectCard());
            OutputView.printCard(createPlayerDto(gamer, gamer.getCards().getEachCard()));
        }
    }

    private boolean isKeepTakeCard(Player gamer) {
        return InputView.chooseOptions(gamer.getName()).equals("y");
    }

    private void takeDealerCard(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isHittable()) {
            dealer.take(cardDeck.selectCard());
            OutputView.printDealerTakeCardMessage();
        }
    }

    private void matchAndUpdateResult(Dealer dealer, Gamers gamers, GameResult gameResult) {
        for (Gamer gamer : gamers.getGamers()) {
            Result result = dealer.match(gamer);
            gameResult.updatePlayerBettingResult(gamer, result.opposite());
        }
        gameResult.calculateDealerResult();
    }

    private void displayResult(Dealer dealer, Gamers gamers, GameResult gameResult) {
        OutputView.printTotalScore(createPlayerDto(dealer, dealer.getCards().getEachCard()), createPlayersDto(gamers));
        OutputView.printResults(gameResult.getDealerResult(), createGamerBettingInfo(gameResult.getPlayersResult()));
    }

    private Map<String, Integer> createGamerBettingInfo(Map<Gamer, Integer> GamerBettingResult) {
        Map<String, Integer> playerBettingResult = new LinkedHashMap<>();
        for (Gamer gamer : GamerBettingResult.keySet()) {
            playerBettingResult.put(gamer.getName(), GamerBettingResult.get(gamer));
        }
        return playerBettingResult;
    }
}
