package controller;

import domain.*;
import dto.DealerDto;
import dto.NamesDto;
import dto.PlayerCardsDto;
import dto.StatisticsDto;
import util.CardsCreator;
import util.Parser;
import view.InputView;
import view.OutputView;
import view.Result;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RandomValueGenerator randomValueGenerator;

    public BlackjackController(InputView inputView, OutputView outputView, RandomValueGenerator randomValueGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.randomValueGenerator = randomValueGenerator;
    }

    public void start() {
        Deck deck = new Deck(CardsCreator.createLinkedCards());
        List<String> playerNames = Parser.parse(inputView.readPlayerName());
        List<Player> players = createPlayers(playerNames, deck);

        Dealer dealer = createDealerAndPrintPlayers(deck, playerNames);

        printPlayersHand(dealer, players);
        chooseToFillPlayersHand(players, deck);
        fillDealerHand(dealer, deck, players);
        printPlayerStatus(dealer, players);
        showGameResultStatistics(dealer, players);
    }

    private void showGameResultStatistics(Dealer dealer, List<Player> players) {
        int dealerTotalScore = dealer.getHand().getTotalScore();
        List<StatisticsDto> statisticsDtos = getStatisticsDtos(players, dealerTotalScore);
        outputView.showResultStatistics(statisticsDtos, dealer.getName());
    }

    private List<StatisticsDto> getStatisticsDtos(List<Player> players, int dealerTotalScore) {
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for (Player player : players) {
            int totalScore = player.getHand().getTotalScore();
            Result result;
            result = getResult(dealerTotalScore, totalScore);
            StatisticsDto statisticsDto = new StatisticsDto(player.getName(), result.getDisplayName());
            statisticsDtos.add(statisticsDto);
        }
        return statisticsDtos;
    }

    private Result getResult(int dealerTotalScore, int totalScore) {
        if(totalScore > Hand.BLACKJACK_MAX_SCORE) return Result.LOSE;
        if (dealerTotalScore > totalScore) return Result.WIN;
        if (dealerTotalScore == totalScore) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private Dealer createDealerAndPrintPlayers(Deck deck, List<String> playerNames) {
        Dealer dealer = new Dealer(getHand(deck));

        NamesDto namesDto = new NamesDto(dealer.getName(), playerNames);
        outputView.drawCard(namesDto);
        return dealer;
    }

    private void printPlayersHand(Dealer dealer, List<Player> players) {
        PlayerCardsDto dealerCardsDto = PlayerCardsDto.dealerFromEntity(dealer);
        outputView.showCard(dealerCardsDto);
        for (Player player : players) {
            PlayerCardsDto playerCardsDto = PlayerCardsDto.fromEntity(player);
            outputView.showCard(playerCardsDto);
        }
    }

    private void chooseToFillPlayersHand(List<Player> players, Deck deck) {
        for (Player player : players) {
            String name = player.getName();

            while (!Hand.isBurst(player.getHand().getTotalScore())
                    && inputView.readNeedToHit(name)) {
                Card card = deck.drawCard(randomValueGenerator.generate(deck.getSize()));
                player.addHand(card);
                PlayerCardsDto playerCardsDto = PlayerCardsDto.fromEntity(player);
                outputView.showCard(playerCardsDto);
            }
        }
    }

    private void fillDealerHand(Dealer dealer, Deck deck, List<Player> players) {
        while(dealer.needsToHit()
                && players.stream().noneMatch(p -> Hand.isBurst(p.getHand().getTotalScore()))){
            Card card = deck.drawCard(randomValueGenerator.generate(deck.getSize()));
            dealer.addHand(card);
            DealerDto dealerDto = DealerDto.FromEntity(dealer);
            outputView.drawDealer(dealerDto);
        }
    }

    private void printPlayerStatus(Dealer dealer, List<Player> players) {
        PlayerCardsDto dealerCardsDto;
        dealerCardsDto = PlayerCardsDto.fromEntity(dealer);
        outputView.showCardsAndScore(dealerCardsDto, dealer.getHand().getTotalScore());
        for (Player player : players) {
            PlayerCardsDto playerCardsDto = PlayerCardsDto.fromEntity(player);
            outputView.showCardsAndScore(playerCardsDto, player.getHand().getTotalScore());
        }
    }

    private List<Player> createPlayers(List<String> playerNames, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Hand hand = getHand(deck);

            Player player = new Player(playerName, hand);
            players.add(player);
        }
        return players;
    }


    private Hand getHand(Deck deck) {
        Card card1 = deck.drawCard(randomValueGenerator.generate(deck.getSize()));
        Card card2 = deck.drawCard(randomValueGenerator.generate(deck.getSize()));
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return new Hand(cards);
    }
}
