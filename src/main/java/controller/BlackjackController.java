package controller;

import domain.bet.Betting;
import domain.card.Deck;
import domain.card.DeckMaker;
import domain.hitStrategy.CasinoDealerHitStrategy;
import domain.participants.Dealer;
import domain.participants.Hand;
import domain.participants.Player;
import dto.DealerDrawDto;
import dto.NamesDto;
import dto.PlayerCardsDto;
import dto.PlayerCreateDto;
import dto.PlayersCardsDto;
import dto.StatisticsDto;
import java.util.ArrayList;
import java.util.List;
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

    public void start(final DeckMaker deckMaker) {
        Deck deck = Deck.createFromDeckMaker(deckMaker);
        Dealer dealer = Dealer.createDefaultStrategy(Hand.createFromDeck(deck));
        List<Player> players = readPlayersInfo().stream()
                .map(player -> player.toDefaultStrategyPlayer(Hand.createFromDeck(deck)))
                .toList();

        printCards(dealer, players);
        drawPlayerHandAndPrint(players, deck);
        drawDealerHandAndPrint(dealer, deck);
        printAllStatus(dealer, players);
    }

    private List<PlayerCreateDto> readPlayersInfo() {
        List<String> playerNames = Parser.parse(inputView.readPlayerName());
        List<PlayerCreateDto> playerCreateDtos = new ArrayList<>();
        for (String playerName : playerNames) {
            Integer betting = Parser.toInteger(inputView.readBettingCost(playerName));
            playerCreateDtos.add(new PlayerCreateDto(playerName, new Betting(betting)));
        }

        return playerCreateDtos;
    }

    private void drawPlayerHandAndPrint(List<Player> players, Deck deck) {
        for (Player player : players) {
            while (player.canDraw()) {
                if (!inputView.readNeedToHit(player.getName())) {
                    player.stay();
                    break;
                }
                player.drawCard(deck.drawCard());
                outputView.showCards(PlayerCardsDto.fromParticipant(player));
            }
        }
    }

    private void printAllStatus(Dealer dealer, List<Player> players) {
        outputView.showCardsAndScore(PlayerCardsDto.fromParticipant(dealer));
        players.forEach(player -> outputView.showCardsAndScore(PlayerCardsDto.fromParticipant(player)));
        outputView.showResultStatistics(getStatisticsDtos(dealer, players), dealer.getName());
    }

    private void printCards(Dealer dealer, List<Player> players) {
        outputView.drawCard(NamesDto.fromDealerAndPlayers(dealer, players));
        outputView.showOnlyOneCard(PlayerCardsDto.fromParticipant(dealer));
        outputView.showPlayersCards(PlayersCardsDto.fromPlayers(players));
    }

    private List<StatisticsDto> getStatisticsDtos(Dealer dealer, List<Player> players) {
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for (Player player : players) {
            String playerName = player.getName();
            StatisticsDto statisticsDto = new StatisticsDto(playerName, player.getProfit(dealer));
            statisticsDtos.add(statisticsDto);
        }

        return statisticsDtos;
    }

    private void drawDealerHandAndPrint(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.drawCard(deck.drawCard());
            outputView.drawDealer(
                    new DealerDrawDto(dealer.getName(), CasinoDealerHitStrategy.BOUNDARY));
            outputView.showCards(PlayerCardsDto.fromParticipant(dealer));
        }
    }
}
