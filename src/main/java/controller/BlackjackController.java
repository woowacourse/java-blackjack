package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.card.DeckMaker;
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
        Dealer dealer = new Dealer(drawHand(deck), new CasinoDealerHitStrategy());

        List<Player> players = createPlayers(Parser.parse(inputView.readPlayerName()), deck);
        printCards(dealer, players);

        chooseToFillPlayersHandAndPrint(players, deck);
        fillDealerHandAndPrint(dealer, deck, players);
        printAllStatus(dealer, players);
    }

    private void printAllStatus(Dealer dealer, List<Player> players) {
        outputView.showCardsAndScore(PlayerCardsDto.fromEntity(dealer));
        players.forEach(player -> outputView.showCardsAndScore(PlayerCardsDto.fromEntity(player)));
        outputView.showResultStatistics(getStatisticsDtos(players, dealer.getScore()), dealer.getName());
    }

    private void printCards(Dealer dealer, List<Player> players) {
        outputView.drawCard(NamesDto.fromEntity(dealer, players));
        outputView.showOnlyOneCard(PlayerCardsDto.fromEntity(dealer));
        outputView.showPlayersCards(PlayersCardsDto.fromEntities(players));
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

    private void chooseToFillPlayersHandAndPrint(List<Player> players, Deck deck) {
        for (Player player : players) {
            while (player.needToHit() && inputView.readNeedToHit(player.getName())) {
                player.addCard(deck.drawCard());
                outputView.showCards(PlayerCardsDto.fromEntity(player));
            }
        }
    }

    private void fillDealerHandAndPrint(Dealer dealer, Deck deck, List<Player> players) {
        while (dealer.needsToHit(players.stream().map(Player::getScore).toList())) {
            dealer.addCard(deck.drawCard());
            outputView.drawDealer(new DealerDrawDto(dealer.getName(), CasinoDealerHitStrategy.BOUNDARY));
            outputView.showCards(PlayerCardsDto.fromEntity(dealer));
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
