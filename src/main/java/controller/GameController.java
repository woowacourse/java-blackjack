package controller;

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
        Dealer dealer = new Dealer(new ArrayList<>(List.of(deck.draw(), deck.draw())), deck);
        Players players = getPlayers(playerNames, deck);

        printGameStart(playerNames, dealer, players);
        receiveMoreCard(players, dealer);

        List<ParticipantDto> participantDtos = players.getPlayers().stream()
                .map(player -> ParticipantDto.from(player.getName(), player))
                .toList();
        outputView.printFinalScore(
                ParticipantDto.from("딜러", dealer), participantDtos);

        Judgement judgement = new Judgement();
        Map<String, GameResult> playerResults = judgement.judgePlayerResults(players, dealer);
        Map<GameResult, Integer> dealerResults = judgement.judgeDealerResults(playerResults);
        outputView.printDealerFinalCount(dealerResults);
        outputView.printPlayerFinalResults(PlayerResultDto.from(playerResults));
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
        outputView.printDealerStartCard(dealer.getHand().getFirst().toString());
        outputView.printStartCard(
                players.getPlayers().stream().map(p -> ParticipantDto.from(p.getName(), p)).toList());
    }

    private void receiveMoreCard(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            processRound(player, dealer);
        }

        while (dealer.canReceiveCard()) {
            dealer.addCard(dealer.dealCard());
            outputView.printDealerReceiveCard();
        }
    }

    private void processRound(Player player, Dealer dealer) {
        while (!player.isBust()) {
            String hitOption = inputView.readHitOption(player.getName());
            InputValidator.validateHitOption(hitOption);
            if (hitOption.equals("n")) {
                break;
            }
            player.addCard(dealer.dealCard());
            outputView.printCurrentHoldCard(ParticipantDto.from(player.getName(), player));
        }
    }
}
