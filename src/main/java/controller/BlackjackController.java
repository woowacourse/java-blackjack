package controller;

import domain.RandomValueGenerator;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.stategy.CasinoDealerHitStrategy;
import domain.stategy.UntilBurstHitStrategy;
import dto.DealerDto;
import dto.NamesDto;
import dto.PlayerCardsDto;
import dto.StatisticsDto;
import util.CardsCreator;
import util.Parser;
import view.InputView;
import view.OutputView;
import domain.Score.Result;

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
        Deck deck = new Deck(CardsCreator.createLinkedCards(), randomValueGenerator);
        Dealer dealer = new Dealer(getHand(deck), new CasinoDealerHitStrategy());
        List<String> playerNames = Parser.parse(inputView.readPlayerName());
        List<Player> players = createPlayers(playerNames, deck);

        printPlayers(dealer, playerNames);

        printParticipantHand(dealer, players);
        chooseToFillPlayersHand(players, deck);
        fillDealerHand(dealer, deck, players);
        printPlayerStatus(dealer, players);
        showGameResultStatistics(dealer, players);
    }

    private void showGameResultStatistics(Dealer dealer, List<Player> players) {
        int dealerTotalScore = dealer.getScore().getValue();
        List<StatisticsDto> statisticsDtos = getStatisticsDtos(players, dealerTotalScore);
        outputView.showResultStatistics(statisticsDtos, dealer.getName());
    }

    private List<StatisticsDto> getStatisticsDtos(List<Player> players, int dealerTotalScore) {
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for (Player player : players) {
            Result result = player.getScore().getResult(dealerTotalScore);
            StatisticsDto statisticsDto = new StatisticsDto(player.getName(), result.getDisplayName());
            statisticsDtos.add(statisticsDto);
        }
        return statisticsDtos;
    }

    private void printPlayers(Dealer dealer, List<String> playerNames) {
        NamesDto namesDto = new NamesDto(dealer.getName(), playerNames);
        outputView.drawCard(namesDto);
    }

    private void printParticipantHand(Dealer dealer, List<Player> players) {
        PlayerCardsDto dealerCardsDto = PlayerCardsDto.fromEntity(dealer);
        outputView.showOnlyOneCard(dealerCardsDto);
        for (Player player : players) {
            PlayerCardsDto playerCardsDto = PlayerCardsDto.fromEntity(player);
            outputView.showCard(playerCardsDto);
        }
    }

    private void chooseToFillPlayersHand(List<Player> players, Deck deck) {
        for (Player player : players) {
            String name = player.getName();
            while (player.needToHit() && inputView.readNeedToHit(name)) {
                Card card = deck.drawCard();
                player.addCard(card);
                PlayerCardsDto playerCardsDto = PlayerCardsDto.fromEntity(player);
                outputView.showCard(playerCardsDto);
            }
        }
    }

    private void fillDealerHand(Dealer dealer, Deck deck, List<Player> players) {
        while (dealer.needsToHit()
                && players.stream().noneMatch(p -> p.getScore().isBurst())) {
            Card card = deck.drawCard();
            dealer.addCard(card);
            DealerDto dealerDto = new DealerDto(dealer.getName(), CasinoDealerHitStrategy.BOUNDARY);
            outputView.drawDealer(dealerDto);
        }
    }

    private void printPlayerStatus(Dealer dealer, List<Player> players) {
        PlayerCardsDto dealerCardsDto;
        dealerCardsDto = PlayerCardsDto.fromEntity(dealer);
        outputView.showCardsAndScore(dealerCardsDto, dealer.getScore().getValue());
        for (Player player : players) {
            PlayerCardsDto playerCardsDto = PlayerCardsDto.fromEntity(player);
            outputView.showCardsAndScore(playerCardsDto, player.getScore().getValue());
        }
    }

    private List<Player> createPlayers(List<String> playerNames, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Hand hand = getHand(deck);

            Player player = new Player(playerName, hand, new UntilBurstHitStrategy());
            players.add(player);
        }
        return players;
    }


    private Hand getHand(Deck deck) {
        Card card1 = deck.drawCard();
        Card card2 = deck.drawCard();
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return new Hand(cards);
    }
}
