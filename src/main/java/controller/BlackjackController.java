package controller;

import domain.card.Card;
import domain.game.BlackjackGame;
import domain.game.Command;
import domain.game.Deck;
import domain.strategy.NumberGenerator;
import domain.strategy.RandomNumberGenerator;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;
import view.OutputView2;
import view.mapper.CardDenominationMapper;
import view.mapper.CardSuitMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView2 outputView;
    private BlackjackGame blackjackGame;

    public BlackjackController(InputView inputView, OutputView2 outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        init();
        start();
        hitPlayers();
        hitDealer();
        printResult();
        printProfit();
    }

    public void init() {
        List<String> playersName = inputView.inputParticipantsName();
        List<Player> players = new ArrayList<>();
        for (String playerName : playersName) {
            players.add(new Player(playerName, inputView.inputBetting(playerName)));
        }

        Dealer dealer = new Dealer("딜러");

        NumberGenerator numberGenerator = new RandomNumberGenerator();
        Deck deck = new Deck(numberGenerator);

        blackjackGame = new BlackjackGame(dealer, players, deck);
    }

    public void start() {
        blackjackGame.hitAll();
        blackjackGame.hitAll();

        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();

        Map<String, List<String>> participants = new LinkedHashMap<>();

        participants.put(dealer.name(), cardString(dealer));
        for (Player player : players) {
            participants.put(player.name(), cardString(player));
        }

        outputView.printGameStarted(participants);
    }

    public void hitPlayers() {
        List<Player> players = blackjackGame.getPlayers();

        for (Player player : players) {
            playTurn(player);
        }
    }

    private void playTurn(Player player) {
        while(blackjackGame.isRunning(player.name())) {
            hitOrStay(player);
            outputView.printNameAndCards(player.name(), cardString(player));
        }
    }

    private void hitOrStay(Player player) {
        Command command = inputView.inputCardCommand(player.name());
        if (command == Command.Y) {
            blackjackGame.hitPlayer(player.name());
        } else if (command == Command.N) {
            blackjackGame.stayPlayer(player.name());
        }
    }

    public void hitDealer() {
        if (blackjackGame.hitDealer()) {
            outputView.printHitDealer();
        }
    }

    private void printResult() {
        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();

        outputView.printResult(dealer.name(), cardString(dealer), dealer.getScore().value());
        for (Player player : players) {
            outputView.printResult(player.name(), cardString(player), player.getScore().value());
        }
    }

    private void printProfit() {
        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();

        Map<String, Integer> profitMap = new LinkedHashMap<>();

        profitMap.put(dealer.name(), blackjackGame.dealerProfit());
        for (Player player : players) {
            profitMap.put(player.name(), blackjackGame.profit(player));
        }
        outputView.printProfit(profitMap);
    }

    private List<String> cardString(Dealer dealer) {
        List<String> cardString = new ArrayList<>();
        for (Card card : dealer.cards()) {
            cardString.add(CardDenominationMapper.getCardNumber(card.getDenomination()) +
                    CardSuitMapper.getCardName(card.getSuit()));
        }
        return cardString;
    }

    private List<String> cardString(Player player) {
        List<String> cardString = new ArrayList<>();
        for (Card card : player.cards()) {
            cardString.add(CardDenominationMapper.getCardNumber(card.getDenomination()) +
                    CardSuitMapper.getCardName(card.getSuit()));
        }
        return cardString;
    }
}
