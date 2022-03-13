package blackjack.controller;

import blackjack.model.Card;
import blackjack.model.CardDeck;
import blackjack.model.Result;
import blackjack.model.dto.CardDTO;
import blackjack.model.dto.PlayerDTO;
import blackjack.model.dto.PlayersDTO;
import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;
import blackjack.model.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void play() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = createDealer(cardDeck);
        List<Gamer> gamers = createGamers(names(), cardDeck);
        OutputView.printOpenCard(createPlayerDto(dealer, dealer.openCards()), createGamersDto(gamers));
        takeCards(cardDeck, dealer, gamers);
        displayResult(dealer, gamers);
    }

    private Dealer createDealer(CardDeck cardDeck) {
        return new Dealer(cardDeck.selectCard(), cardDeck.selectCard());
    }

    private List<String> names() {
        List<String> names;
        try {
            names = InputView.inputNames();
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return names();
        }
        return names;
    }

    private List<Gamer> createGamers(List<String> names, CardDeck cardDeck) {
        return names.stream()
                .map(name -> createEachGamer(name, cardDeck))
                .collect(Collectors.toList());
    }

    private Gamer createEachGamer(String name, CardDeck cardDeck) {
        return new Gamer(name, cardDeck.selectCard(), cardDeck.selectCard());
    }

    private PlayersDTO createGamersDto(List<Gamer> gamers) {
        List<PlayerDTO> players = new ArrayList<>();
        for (Gamer gamer : gamers) {
            players.add(createPlayerDto(gamer, gamer.getCards().getEachCard()));
        }
        return new PlayersDTO(players);
    }

    private PlayerDTO createPlayerDto(Player player, List<Card> playerCards) {
        List<CardDTO> cards = new ArrayList<>();
        for (Card card : playerCards) {
            cards.add(new CardDTO(card.getRank().getSymbol(), card.getSuit().getSymbol()));
        }
        return new PlayerDTO(player.getName(), player.score().getValue(), cards);
    }

    private void takeCards(CardDeck cardDeck, Dealer dealer, List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
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

    private void takeDealerCard(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isHittable()) {
            dealer.take(cardDeck.selectCard());
            OutputView.printDealerTakeCardMessage();
        }
    }

    private boolean isKeepTakeCard(Player gamer) {
        String option;
        try {
            option = InputView.chooseOptions(gamer.getName());
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return isKeepTakeCard(gamer);
        }
        return option.equals("y");
    }

    private void displayResult(Dealer dealer, List<Gamer> gamers) {
        OutputView.printTotalScore(createPlayerDto(dealer, dealer.getCards().getEachCard()), createGamersDto(gamers));
        printRecord(dealer, gamers);
    }

    private void printRecord(Dealer dealer, List<Gamer> gamers) {
        printDealerRecord(dealer, gamers);
        printGamerRecords(dealer, gamers);
    }

    private void printDealerRecord(Dealer dealer, List<Gamer> gamers) {
        Map<String, Integer> result = new HashMap<>();
        for (Gamer gamer : gamers) {
            result.merge(dealer.match(gamer.getCards()).getSymbol(), 1, Integer::sum);
        }
        OutputView.printDealerRecord(result);
    }

    private void printGamerRecords(Dealer dealer, List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
            Result result = dealer.match(gamer.getCards());
            OutputView.printGamerRecord(gamer.getName(), result.opposite().getSymbol());
        }
    }
}
