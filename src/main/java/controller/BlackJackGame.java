package controller;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.GameResult;
import domain.Outcome;
import domain.Participant;
import domain.Player;
import domain.Players;
import dto.AllOutcomeDto;
import dto.FinalScoreDto;
import dto.InitialDto;
import dto.ParticipantDto;
import dto.ParticipantScoreDto;
import dto.PlayerOutcomeDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
        Deck deck = createDeck();
        Dealer dealer = new Dealer();
        Players players = NameParser.makeNameList(inputView.getParticipant());
        initialDraw(deck, dealer, players);
        printInitialStatus(dealer, players);
        players.getPlayers().forEach(player -> drawPlayerTurn(deck, player));
        drawDealerTurn(deck, dealer);
        printFinalScore(dealer, players);
        printFinalWinner(dealer, players);
    }

    private Deck createDeck() {
        List<Card> cards = IntStream.range(0, 52)
                .mapToObj(Card::new)
                .collect(Collectors.toList());
        return new Deck(cards);
    }

    private void initialDraw(Deck deck, Dealer dealer, Players players) {
        IntStream.range(0, 2).forEach(i -> {
            dealer.drawCard(deck.draw());
            players.getPlayers().forEach(player -> player.drawCard(deck.draw()));
        });
    }

    private void printInitialStatus(Dealer dealer, Players players) {
        ParticipantDto dealerDto = new ParticipantDto(dealer.getName(), getCardNames(dealer));
        List<ParticipantDto> participantDtos = players.getPlayers().stream()
                .map(player -> new ParticipantDto(player.getName(), getCardNames(player)))
                .collect(Collectors.toList());
        String joinedNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        resultView.printGameStart(new InitialDto(joinedNames, dealerDto, participantDtos));
    }

    private void drawPlayerTurn(Deck deck, Player player) {
        while (player.canDraw() && inputView.getMoreCards(player).equals("y")) {
            player.drawCard(deck.draw());
            resultView.printParticipantMoreCard(new ParticipantDto(player.getName(), getCardNames(player)));
        }
    }

    private void drawDealerTurn(Deck deck, Dealer dealer) {
        while (dealer.canDraw()) {
            dealer.drawCard(deck.draw());
            resultView.printDealerMoreCard();
        }
    }

    private void printFinalWinner(Dealer dealer, Players players) {
        Map<Player, Outcome> playerResults = new LinkedHashMap<>();
        List<PlayerOutcomeDto> outcomeDtos = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            Outcome outcome = Outcome.decideWinner(player.getScore(), dealer.getScore());
            playerResults.put(player, outcome);
            outcomeDtos.add(new PlayerOutcomeDto(player.getName(), outcome.getName()));
        }
        GameResult gameResult = new GameResult(playerResults);
        resultView.printWinner(new AllOutcomeDto(gameResult.getDealerResult(), outcomeDtos));
    }

    private void printFinalScore(Dealer dealer, Players players) {
        ParticipantScoreDto dealerScoreDto = createScoreDto(dealer);
        List<ParticipantScoreDto> playerScoreDtos = players.getPlayers().stream()
                .map(this::createScoreDto)
                .collect(Collectors.toList());
        resultView.printResult(new FinalScoreDto(dealerScoreDto, playerScoreDtos));
    }

    private ParticipantScoreDto createScoreDto(Participant participant) {
        return new ParticipantScoreDto(
                participant.getName(),
                getCardNames(participant),
                participant.getScore().getGameScore()
        );
    }

    private List<String> getCardNames(Participant participant) {
        List<String> cardNames = new ArrayList<>();
        for (Card card : participant.getHand().getCards()) {
            cardNames.add(card.getCardName());
        }
        return cardNames;
    }
}