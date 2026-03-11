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
import dto.PlayerCardsDto;
import dto.StatisticsDto;
import java.util.ArrayList;
import java.util.List;
import util.CardsCreator;
import util.Parser;
import view.InputView;
import view.OutputView;
import view.Result;

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

        Dealer dealer = createDealerAndPrintPlayers(deck, playerNames);

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

    private void showGameResultStatistics(Dealer dealer, List<Player> players) {
        List<StatisticsDto> statisticsDtos = getStatisticsDtos(players, dealer);
        outputView.showResultStatistics(statisticsDtos, dealer.getName());
    }

    private List<StatisticsDto> getStatisticsDtos(List<Player> players, Dealer dealer) {
        int dealerTotalScore = dealer.getTotalScore();
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        if (dealer.isBust()) {
            dealerTotalScore = 0;
            judgeResult(players, dealerTotalScore, statisticsDtos);
            return statisticsDtos;
        }
        judgeResult(players, dealerTotalScore, statisticsDtos);
        return statisticsDtos;
    }

    private void judgeResult(List<Player> players, int dealerTotalScore, List<StatisticsDto> statisticsDtos) {
        for (Player player : players) {
            int totalScore = player.getTotalScore();
            Result result;
            result = getResult(dealerTotalScore, totalScore);
            StatisticsDto statisticsDto = new StatisticsDto(player.getName(), result.getDisplayName());
            statisticsDtos.add(statisticsDto);
        }
    }

    private Result getResult(int dealerTotalScore, int totalScore) {
        if (totalScore > Hand.BLACKJACK_MAX_SCORE) {
            return Result.LOSE;
        }
        if (dealerTotalScore > totalScore) {
            return Result.LOSE;
        }
        if (dealerTotalScore == totalScore) {
            return Result.DRAW;
        }
        return Result.WIN;
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
            PlayerCardsDto playerCardsDto = PlayerCardsDto.fromEntity(player);
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
        PlayerCardsDto dealerCardsDto;
        dealerCardsDto = PlayerCardsDto.fromEntity(dealer);
        outputView.showCardsAndScore(dealerCardsDto, dealer.getTotalScore());
        for (Player player : players) {
            PlayerCardsDto playerCardsDto = PlayerCardsDto.fromEntity(player);
            outputView.showCardsAndScore(playerCardsDto, player.getTotalScore());
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
        Card card1 = deck.drawCard();
        Card card2 = deck.drawCard();
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return new Hand(cards);
    }
}
