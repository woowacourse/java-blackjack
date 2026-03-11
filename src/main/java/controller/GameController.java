package controller;

import domain.card.Card;
import domain.participant.Dealer;
import domain.card.Deck;
import domain.GameResult;
import domain.Judgement;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        List<String> playerNames = getPlayerNames();
        Deck deck = new Deck();
        Dealer dealer = new Dealer(new ArrayList<>(List.of(deck.draw(), deck.draw())));
        Players players = getPlayers(playerNames, deck);

        printGameStart(playerNames, dealer, players);
        receiveMoreCard(players, dealer, deck);

        printFinalScore(players, dealer);
        printFinalResults(players, dealer);
    }

    private List<String> getPlayerNames() {
        String rawPlayerNames = inputView.readPlayerName();
        return InputParser.parsePlayerNames(rawPlayerNames);
    }

    private Players getPlayers(List<String> playerNames, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = new Player(playerName, new ArrayList<>(List.of(deck.draw(), deck.draw())));
            players.add(player);
        }
        return new Players(players);
    }

    private void printGameStart(List<String> playerNames, Dealer dealer, Players players) {
        outputView.printStartCardMessage(playerNames);

        Card dealerFirstCard = dealer.getHand().getFirst();
        outputView.printDealerStartCard(dealerFirstCard.getCardNumber(), dealerFirstCard.getCardSuit());
        outputView.printStartCard(
                players.getPlayers().stream().map(player -> ParticipantDto.of(player.getName(), player)).toList());
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

    private void printFinalScore(Players players, Dealer dealer) {
        List<ParticipantDto> participantDtos = players.getPlayers().stream()
                .map(player -> ParticipantDto.of(player.getName(), player))
                .toList();
        outputView.printFinalScore(
                ParticipantDto.of("딜러", dealer), participantDtos);
    }

    private void printFinalResults(Players players, Dealer dealer) {
        Judgement judgement = new Judgement();
        Map<String, GameResult> playerResults = judgement.judgePlayerResults(players, dealer);
        Map<GameResult, Integer> dealerResults = judgement.judgeDealerResults(playerResults);
        outputView.printDealerFinalCount(dealerResults);
        outputView.printPlayerFinalResults(PlayerResultDto.from(playerResults));
    }

    private void processRound(Player player, Deck deck) {
        while (!player.isBust()) {
            String hitOption = inputView.readHitOption(player.getName());
            InputValidator.validateHitOption(hitOption);
            if (hitOption.equals("n")) {
                break;
            }
            player.addCard(deck.draw());
            outputView.printCurrentHoldCard(ParticipantDto.of(player.getName(), player));
        }
    }
}
