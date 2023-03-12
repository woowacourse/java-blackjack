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
        List<Player> players = inputView.inputParticipantsName().stream()
                .map(Player::new)
                .collect(Collectors.toList());
        Dealer dealer = new Dealer("딜러");

        for (Player player : players) {
            player.setBetting(inputView.inputBetting(player.getName()));
        }

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

        participants.put(dealer.getName(), cardString(dealer));
        for (Player player : players) {
            participants.put(player.getName(), cardString(player));
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
        while(blackjackGame.isRunning(player.getName())) {
            hitOrStay(player);
            outputView.printNameAndCards(player.getName(), cardString(player));
        }
    }

    private void hitOrStay(Player player) {
        Command command = inputView.inputCardCommand(player.getName());
        if (command == Command.Y) {
            blackjackGame.hitPlayer(player.getName());
        } else if (command == Command.N) {
            blackjackGame.stayPlayer(player.getName());
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

        outputView.printResult(dealer.getName(), cardString(dealer), dealer.getScore().value());
        for (Player player : players) {
            outputView.printResult(player.getName(), cardString(player), player.getScore().value());
        }
    }

    private void printProfit() {
        Dealer dealer = blackjackGame.getDealer();
        List<Player> players = blackjackGame.getPlayers();

        Map<String, Integer> profitMap = new LinkedHashMap<>();

        profitMap.put(dealer.getName(), blackjackGame.dealerProfit());
        for (Player player : players) {
            profitMap.put(player.getName(), blackjackGame.profit(player));
        }
        outputView.printProfit(profitMap);
    }

    private List<String> cardString(Dealer dealer) {
        List<String> res = new ArrayList<>();
        for (Card card : dealer.cards()) {
            res.add(CardDenominationMapper.getCardNumber(card.getDenomination()) +
                    CardSuitMapper.getCardName(card.getSuit()));
        }
        return res;
    }

    private List<String> cardString(Player player) {
        List<String> res = new ArrayList<>();
        for (Card card : player.cards()) {
            res.add(CardDenominationMapper.getCardNumber(card.getDenomination()) +
                    CardSuitMapper.getCardName(card.getSuit()));
        }
        return res;
    }
}
