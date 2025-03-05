package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardDump;
import blackjack.domain.Dealer;
import blackjack.domain.GameFinalResult;
import blackjack.domain.GameResult;
import blackjack.domain.GameRule;
import blackjack.domain.Player;
import blackjack.dto.DistributedCardDto;
import blackjack.dto.FinalResultDto;
import blackjack.view.InputVIew;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private final InputVIew inputView;
    private final OutputView outputView;
    private final CardDump cardDump;

    public BlackjackController() {
        this.inputView = new InputVIew();
        this.outputView = new OutputView();
        this.cardDump = new CardDump();
    }

    public void run() {
        List<Player> players = distributeCardToPlayers();
        Dealer dealer = distrubuteCardToDealer();
        outputView.displayDistributedCardStatus(DistributedCardDto.from(dealer), players.stream().map(
                DistributedCardDto::from).toList());

        hitExtraCardForPlayers(players);
        hitExtraCardForDealer(dealer);
        outputView.displayFinalCardStatus(FinalResultDto.from(dealer), players.stream().map(
                FinalResultDto::from).toList());

        getGameResultAndDisplay(dealer, players);
    }

    private List<Player> distributeCardToPlayers() {
        String[] playerNames = inputView.readPlayerName();
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            CardDeck cardDeck = new CardDeck();
            cardDeck.add(cardDump.drawCard());
            cardDeck.add(cardDump.drawCard());

            Player player = new Player(playerName, cardDeck, cardDump);
            players.add(player);
        }
        return players;
    }

    private Dealer distrubuteCardToDealer() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(cardDump.drawCard());
        cardDeck.add(cardDump.drawCard());
        return new Dealer(cardDeck, cardDump);
    }

    private void hitExtraCardForPlayers(List<Player> players) {
        for (Player player : players) {
            while (player.canTakeExtraCard()) {
                String answer = inputView.readOneMoreCard(player.getName());
                HitOption option = HitOption.from(answer);
                if (option.isNo()) {
                    break;
                }
                player.addCard();
                outputView.displayCardInfo(DistributedCardDto.from(player));
            }
        }
    }

    private void hitExtraCardForDealer(Dealer dealer) {
        while (dealer.hasTakenExtraCard()) {
            outputView.displayExtraDealerCardStatus();
        }
    }

    private void getGameResultAndDisplay(Dealer dealer, List<Player> players) {
        GameFinalResult gameFinalResult = new GameFinalResult(new GameRule());
        Map<GameResult, Integer> dealerResult = gameFinalResult.getDealerFinalResult(dealer, players);
        outputView.displayDealerResult(dealerResult);
        for (Player player : players) {
            GameResult playerResult = gameFinalResult.getGameResultFromPlayer(player, dealer);
            outputView.displayPlayerResult(player, playerResult);
        }
    }
}
