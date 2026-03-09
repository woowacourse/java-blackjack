package controller;

import domain.RandomValueGenerator;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import domain.hitStrategy.CasinoDealerHitStrategy;
import domain.hitStrategy.UntilBurstHitStrategy;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.score.Result;
import domain.score.Score;
import dto.DealerDrawDto;
import dto.NamesDto;
import dto.PlayerCardsDto;
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
    private final RandomValueGenerator randomValueGenerator;

    public BlackjackController(InputView inputView, OutputView outputView, RandomValueGenerator randomValueGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.randomValueGenerator = randomValueGenerator;
    }

    public void start() {
        Deck deck = new Deck(CardsCreator.createLinkedCards(), randomValueGenerator);
        Dealer dealer = new Dealer(drawHand(deck), new CasinoDealerHitStrategy());
        List<String> playerNames = Parser.parse(inputView.readPlayerName());
        List<Player> players = createPlayers(playerNames, deck);

        printPlayers(dealer, playerNames);
        printParticipantHand(dealer, players);
        chooseToFillPlayersHand(players, deck);
        fillDealerHand(dealer, deck, players);  //전부 버스트나면 안뽑게해야 하나?
        printPlayerStatus(dealer, players);
        showGameResultStatistics(dealer, players);
    }

    private void showGameResultStatistics(Dealer dealer, List<Player> players) {
        Score dealerScore = dealer.getScore();
        List<StatisticsDto> statisticsDtos = getStatisticsDtos(players, dealerScore);
        outputView.showResultStatistics(statisticsDtos, dealer.getName());
    }

    private List<StatisticsDto> getStatisticsDtos(List<Player> players, Score dealerScore) {
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for (Player player : players) {
            Result result = player.getScore().getResult(dealerScore);
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
        while (dealer.needsToHit(players.stream().map(Player::getScore).toList())
                && players.stream().noneMatch(p -> p.getScore().isBurst())) {
            Card card = deck.drawCard();
            dealer.addCard(card);
            DealerDrawDto dealerDrawDto = new DealerDrawDto(dealer.getName(), CasinoDealerHitStrategy.BOUNDARY);
            outputView.drawDealer(dealerDrawDto);
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
            Hand hand = drawHand(deck);

            Player player = new Player(playerName, hand, new UntilBurstHitStrategy());
            players.add(player);
        }
        return players;
    }


    private Hand drawHand(Deck deck) {
        Card card1 = deck.drawCard();
        Card card2 = deck.drawCard();
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        return new Hand(cards);
    }
}
