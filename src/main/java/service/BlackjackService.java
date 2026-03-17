package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.Deck;
import domain.Game;
import domain.Player;
import domain.Players;
import domain.dto.BettingResultDto;
import domain.dto.CardContentDto;
import domain.dto.FinalCardDto;
import domain.dto.MatchResultDto;
import domain.enums.MatchCase;
import utils.generator.CardsGenerator;
import utils.generator.ShuffledCardsGenerator;

public class BlackjackService {

    public Deck generateCards() {
        CardsGenerator cardsGenerator = new ShuffledCardsGenerator();
        return new Deck(cardsGenerator);
    }

    public Players createPlayers(List<String> names, Deck deck) {
        List<Player> playerList = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            player.addInitializedCard(deck);
            playerList.add(player);
        }
        return new Players(playerList);
    }

    public Player createDealer(Deck deck) {
        Player dealer = new Player(Game.DEALER_NAME);
        dealer.addInitializedCard(deck);
        return dealer;
    }

    public Game createGame(Deck deck, Players players) {
        Player dealer = createDealer(deck);
        return new Game(deck, players, dealer);
    }

    public CardContentDto getCardContentDto(Player player) {
        return new CardContentDto(player.getName(), player.getCards());
    }

    public List<CardContentDto> getCardContentDtos(Game game) {
        List<CardContentDto> firstCardContents = new ArrayList<>();
        firstCardContents.add(new CardContentDto(Game.DEALER_NAME, List.of(game.getDealerFirstCard())));
        for (Player player : game.getPlayers()) {
            firstCardContents.add(getCardContentDto(player));
        }
        return firstCardContents;
    }

    public List<FinalCardDto> getFinalCardDtos(Game game) {
        List<FinalCardDto> finalCards = new ArrayList<>();
        Player dealer = game.getDealer();
        finalCards.add(new FinalCardDto(dealer.getName(), dealer.getCards(), dealer.getCardsTotalSum()));

        for (Player player : game.getPlayers()) {
            finalCards.add(new FinalCardDto(player.getName(), player.getCards(), player.getCardsTotalSum()));
        }
        return finalCards;
    }

    public MatchResultDto getPlayerResultDto(Game game) {
        Map<String, MatchCase> playerResult = game.calculateMatch();
        Map<MatchCase, Integer> dealerResult = game.calculateDealerMatch(playerResult);
        return new MatchResultDto(dealerResult, playerResult);

    }

    public BettingResultDto getBettingScoreDto(Game game) {
        return new BettingResultDto(-game.getTotalMoney(), game.getBettingScore());
    }

}
