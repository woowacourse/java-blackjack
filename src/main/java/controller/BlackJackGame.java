package controller;

import domain.card.Deck;
import domain.card.DeckFactory;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.Outcome;
import dto.FinalScoreDto;
import dto.InitialDto;
import dto.MoneyDTO;
import dto.ParticipantDto;
import dto.ParticipantScoreDto;
import dto.PlayerOutcomeDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import util.NameParser;
import view.InputView;
import view.ResultView;

public class BlackJackGame {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        Deck deck = DeckFactory.create();
        Dealer dealer = new Dealer();
        Players players = setupPlayers();
        playGame(deck, dealer, players);
    }

    private void playGame(Deck deck, Dealer dealer, Players players) {
        initialDraw(deck, dealer, players);
        printInitialStatus(dealer, players);
        if (dealer.isFirstBlackJack()) {
            printResults(dealer, players);
            return;
        }
        players.players().forEach(player -> drawPlayerTurn(deck, player));
        drawDealerTurn(deck, dealer);
        printResults(dealer, players);
    }

    private void printResults(Dealer dealer, Players players) {
        printFinalScore(dealer, players);
        printFinalProfit(dealer, players);
    }

    private Players setupPlayers() {
        List<String> playerNames = NameParser.makeNameList(inputView.getParticipant());
        List<Player> players = new ArrayList<>();
        for (String name : playerNames) {
            String money = inputView.getMoney(name);
            players.add(new Player(name, new Money(money)));
        }
        return new Players(players);
    }

    private void initialDraw(Deck deck, Dealer dealer, Players players) {
        IntStream.range(0, 2).forEach(i -> {
            dealer.drawCard(deck.draw());
            players.players().forEach(player -> player.drawCard(deck.draw()));
        });
    }

    private void printInitialStatus(Dealer dealer, Players players) {
        ParticipantDto dealerDto = ParticipantDto.from(dealer);
        List<ParticipantDto> participants =
                players.players()
                        .stream()
                        .map(ParticipantDto::from)
                        .toList();
        String joinedNames = players.getJoinedNames();
        resultView.printGameStart(new InitialDto(joinedNames, dealerDto, participants));
    }

    private void drawPlayerTurn(Deck deck, Player player) {
        while (player.canDraw() && inputView.getMoreCards(player.getName()).equals("y")) {
            player.drawCard(deck.draw());
            resultView.printParticipantMoreCard(ParticipantDto.from(player));
        }
    }

    private void drawDealerTurn(Deck deck, Dealer dealer) {
        while (dealer.canDraw()) {
            dealer.drawCard(deck.draw());
            resultView.printDealerMoreCard();
        }
    }

    private void printFinalScore(Dealer dealer, Players players) {
        ParticipantScoreDto dealerScoreDto = ParticipantScoreDto.from(dealer);
        List<ParticipantScoreDto> playerScoreDtos =
                players.players()
                        .stream()
                        .map(ParticipantScoreDto::from)
                        .toList();
        resultView.printResult(new FinalScoreDto(dealerScoreDto, playerScoreDtos));
    }

    private void printFinalProfit(Dealer dealer, Players players) {
        int dealerProfit = 0;
        List<PlayerOutcomeDto> outcomeDtos = new ArrayList<>();
        for (Player player : players.players()) {
            Outcome outcome = Outcome.decideWinner(player, dealer);
            Money playerProfit = player.getMoney().multiply(outcome.getRate());
            dealerProfit -= playerProfit.getAmount();
            outcomeDtos.add(PlayerOutcomeDto.of(player, playerProfit));
        }
        resultView.printScore(new MoneyDTO(String.valueOf(dealerProfit), outcomeDtos));
    }
}
