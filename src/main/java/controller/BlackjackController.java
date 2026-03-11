package controller;

import domain.RandomValueGenerator;
import domain.RandomValueGeneratorImpl;
import domain.card.Card;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Hand;
import domain.player.Player;
import dto.DealerDto;
import dto.NamesDto;
import dto.ParticipantsCardsDto;
import dto.StatisticsDto;
import java.util.ArrayList;
import java.util.List;
import util.CardsCreator;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Deck deck = createDeck();
        List<String> playerNames = Parser.parse(inputView.readPlayerName());
        List<Player> players = createPlayers(playerNames, deck);

        Dealer dealer = createDealerAndPrintPlayers(deck, players);

        printPlayersHand(dealer, players);
        chooseToFillPlayersHand(players, deck);
        fillDealerHand(dealer, deck, players);
        printPlayerStatus(dealer, players);
        showGameResultStatistics(dealer, players);
    }

    private static Deck createDeck() {
        RandomValueGenerator randomValueGenerator = new RandomValueGeneratorImpl();
        return new Deck(CardsCreator.createCards(), randomValueGenerator);
    }

    private List<Player> createPlayers(List<String> playerNames, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            int amount = inputView.readPlayerBettingAmount(playerName);
            Hand hand = getHand(deck);

            Player player = Player.create(playerName, hand, amount);
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

    private Dealer createDealerAndPrintPlayers(Deck deck, List<Player> players) {
        Dealer dealer = new Dealer(getHand(deck));
        List<String> playerNames = new ArrayList<>();
        for (Player player : players) {
            playerNames.add(player.getName());
        }

        NamesDto namesDto = new NamesDto(dealer.getName(), playerNames);
        outputView.drawCard(namesDto);
        return dealer;
    }

    private void printPlayersHand(Dealer dealer, List<Player> players) {
        ParticipantsCardsDto dealerCardsDto = ParticipantsCardsDto.dealerFromEntity(dealer);
        outputView.showCard(dealerCardsDto);
        for (Player player : players) {
            ParticipantsCardsDto playerCardsDto = ParticipantsCardsDto.playerFromEntity(player);
            outputView.showCard(playerCardsDto);
        }
    }

    private void chooseToFillPlayersHand(List<Player> players, Deck deck) {
        for (Player player : players) {
            PlayerTurn(deck, player);
        }
    }

    private void PlayerTurn(Deck deck, Player player) {
        String name = player.getName();
        if (determineBlackjack(player, name)) {
            return;
        }

        while (!player.isBust() && inputView.readNeedToHit(name)) {
            Card card = deck.drawCard();
            player.addHand(card);
            ParticipantsCardsDto playerCardsDto = ParticipantsCardsDto.playerFromEntity(player);
            outputView.showCard(playerCardsDto);
        }
    }

    private boolean determineBlackjack(Player player, String name) {
        if (player.isBlackjack()) {
            outputView.congratulateBlackjack(name);
            return true;
        }
        return false;
    }

    private void fillDealerHand(Dealer dealer, Deck deck, List<Player> players) {
        while (dealer.needsToHit()
                && players.stream().noneMatch(Player::isBust)) {
            Card card = deck.drawCard();
            dealer.addHand(card);
            DealerDto dealerDto = DealerDto.fromEntity(dealer);
            outputView.drawDealer(dealerDto);
        }
    }

    private void printPlayerStatus(Dealer dealer, List<Player> players) {
        ParticipantsCardsDto dealerCardsDto;
        dealerCardsDto = ParticipantsCardsDto.dealerFromEntity(dealer);
        outputView.showCardsAndScore(dealerCardsDto, dealer.getTotalScore());
        for (Player player : players) {
            ParticipantsCardsDto playerCardsDto = ParticipantsCardsDto.playerFromEntity(player);
            outputView.showCardsAndScore(playerCardsDto, player.getTotalScore());
        }
    }

    private void showGameResultStatistics(Dealer dealer, List<Player> players) {
        List<StatisticsDto> statisticsDtos = getStatisticsDtos(players, dealer);
        outputView.showResultStatistics(statisticsDtos, dealer.getName());
    }

    private List<StatisticsDto> getStatisticsDtos(List<Player> players, Dealer dealer) {
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for (Player player : players) {
            int profit = player.calculateProfit(dealer);
            StatisticsDto statisticsDto = new StatisticsDto(player.getName(), profit);
            statisticsDtos.add(statisticsDto);
        }
        return statisticsDtos;
    }
}
