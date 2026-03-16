package controller;

import domain.Money;
import domain.ProfitCalculator;
import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantDto;
import dto.PlayerProfitDto;
import java.util.ArrayList;
import java.util.List;
import view.InputParser;
import view.InputValidator;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;


    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer(new ArrayList<>(List.of(deck.draw(), deck.draw())));
        List<String> playerNames = getPlayerNames();
        Players players = getPlayers(playerNames, deck);

        printGameStart(playerNames, dealer, players);

        receiveMoreCard(players, dealer, deck);

        printFinalScore(players, dealer);
        printFinalProfit(players, dealer);
    }

    private List<String> getPlayerNames() {
        String input = inputView.readPlayerName();
        return InputParser.parsePlayerNames(input);
    }

    private Players getPlayers(List<String> playerNames, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Money money = getMoney(playerName);
            Player player = new Player(new ArrayList<>(List.of(deck.draw(), deck.draw())), playerName, money);
            players.add(player);
        }
        return new Players(players);
    }

    private void printGameStart(List<String> playerNames, Dealer dealer, Players players) {
        outputView.printStartCardMessage(playerNames);

        Card dealerFirstCard = dealer.getHand().getFirst();
        List<ParticipantDto> participantDtos = players.getPlayers().stream()
                .map(player -> ParticipantDto.of(player.getName(), player))
                .toList();
        outputView.printDealerStartCard(dealerFirstCard);
        outputView.printStartCard(participantDtos);
    }

    private void receiveMoreCard(Players players, Dealer dealer, Deck deck) {
        for (Player player : players.getPlayers()) {
            processRound(player, deck);
        }

        while (dealer.canReceiveCard()) {
            dealer.addCard(deck.draw());
            outputView.printDealerReceiveCard();
        }
    }

    private void printFinalScore(Players players, Participant dealer) {
        List<ParticipantDto> participantDtos = players.getPlayers().stream()
                .map(player -> ParticipantDto.of(player.getName(), player))
                .toList();
        outputView.printFinalScore(
                ParticipantDto.of("딜러", dealer), participantDtos);
    }

    private void printFinalProfit(Players players, Dealer dealer) {
        List<PlayerProfitDto> playerProfitDtos = getPlayerProfitDtos(players, dealer);
        int dealerFinalProfit = -playerProfitDtos.stream()
                .mapToInt(PlayerProfitDto::profit)
                .sum();
        outputView.printDealerFinalProfit(dealerFinalProfit);
        outputView.printPlayerFinalProfit(playerProfitDtos);
    }

    private Money getMoney(String playerName) {
        String input = inputView.readMoney(playerName);
        return new Money(InputParser.parseMoney(input));
    }

    private void processRound(Player player, Deck deck) {
        while (!player.isBust() && !player.isBlackjack()) {
            String hitOption = inputView.readHitOption(player.getName());
            InputValidator.validateHitOption(hitOption);
            if (hitOption.equals("n")) {
                break;
            }
            player.addCard(deck.draw());
            outputView.printCurrentHoldCard(ParticipantDto.of(player.getName(), player));
        }
    }

    private List<PlayerProfitDto> getPlayerProfitDtos(Players players, Dealer dealer) {
        ProfitCalculator profitCalculator = new ProfitCalculator();
        List<PlayerProfitDto> playerProfitDtos = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            int finalProfit = profitCalculator.calculate(player, dealer, player.getMoney());
            playerProfitDtos.add(PlayerProfitDto.of(player.getName(), finalProfit));
        }
        return playerProfitDtos;
    }
}
