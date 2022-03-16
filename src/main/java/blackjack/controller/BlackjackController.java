package blackjack.controller;

import blackjack.model.Card;
import blackjack.model.CardDeck;
import blackjack.model.Result;
import blackjack.model.dto.*;
import blackjack.model.player.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void play() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(List.of(cardDeck.selectCard(), cardDeck.selectCard()));
        Gamers gamers = new Gamers(InputView.inputNames(), cardDeck);
        OutputView.printOpenCard(createPlayerDto(dealer, dealer.openCards()), createPlayersDto(gamers));

        takeCards(cardDeck, dealer, gamers);
        OutputView.printTotalScore(createPlayerDto(dealer, dealer.getCards().getEachCard()), createPlayersDto(gamers));

        matchAndDisplayResult(dealer, gamers);
    }

    private PlayerDTO createPlayerDto(Player player, List<Card> playerCards) {
        List<CardDTO> cards = playerCards.stream()
                .map(card -> new CardDTO(card.getRank().name(), card.getSuit().name()))
                .collect(Collectors.toList());
        return new PlayerDTO(player.getName(), player.score().getValue(), cards);
    }

    private PlayersDTO createPlayersDto(Gamers gamers) {
        List<PlayerDTO> players = gamers.getGamers().stream()
                .map(gamer -> createPlayerDto(gamer, gamer.getCards().getEachCard()))
                .collect(Collectors.toList());
        return new PlayersDTO(players);
    }

    private void takeCards(CardDeck cardDeck, Dealer dealer, Gamers gamers) {
        for (Gamer gamer : gamers.getGamers()) {
            takeGamerCard(gamer, cardDeck);
        }
        takeDealerCard(dealer, cardDeck);
    }

    private void takeGamerCard(Gamer gamer, CardDeck cardDeck) {
        while (gamer.isHittable() && isKeepTakeCard(gamer)) {
            gamer.take(cardDeck.selectCard());
            OutputView.printCard(createPlayerDto(gamer, gamer.getCards().getEachCard()));
        }
    }

    private boolean isKeepTakeCard(Player gamer) {
        return InputView.chooseOptions(gamer.getName()).equals("y");
    }

    private void takeDealerCard(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isHittable()) {
            dealer.take(cardDeck.selectCard());
            OutputView.printDealerTakeCardMessage();
        }
    }

    private void matchAndDisplayResult(Dealer dealer, Gamers gamers) {
        Map<String, String> gamersResult = new LinkedHashMap<>();
        for (Gamer gamer : gamers.getGamers()) {
            Result result = dealer.match(gamer.getCards());
            gamersResult.put(gamer.getName(), result.opposite().name());
        }
        OutputView.printResults(gamersResult);
    }
}
