package blackjack.controller;

import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import blackjack.model.game.BlackjackResult;
import blackjack.model.participant.Bet;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Name;
import blackjack.model.participant.Names;
import blackjack.model.participant.Player;
import blackjack.util.RetryUtil;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.DealerScoreDto;
import blackjack.view.dto.PlayerDto;
import blackjack.view.dto.PlayerScoreDto;
import blackjack.view.dto.ResultDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(
            InputView inputView,
            OutputView outputView
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Collection<Player> players = retryOnIllegalArgument(this::readPlayers);
        Dealer dealer = new Dealer();
        Deck deck = Deck.unique();

        initialDeal(players, dealer, deck);
        hit(players, dealer, deck);
        printScore(players, dealer);
        printResult(players, dealer);
    }

    private List<Player> readPlayers() {
        ArrayList<Player> players = new ArrayList<>();

        Names playerNames = inputView.readPlayerNames();
        for (Name playerName : playerNames.get()) {
            Bet bet = inputView.readBet(playerName);
            players.add(new Player(playerName, bet));
        }

        return players;
    }

    private void initialDeal(Collection<Player> players, Dealer dealer, Deck deck) {
        deal(players, dealer, deck);
        deal(players, dealer, deck);

        outputView.printInitialDeal(
                cardsToDtos(dealer.getCards()),
                playersToDots(players)
        );
    }

    private void deal(Collection<Player>players, Dealer dealer, Deck deck) {
        for (Player player : players) {
            player.addCard(deck.draw());
        }
        dealer.addCard(deck.draw());
    }

    private void hit(Collection<Player>players, Dealer dealer, Deck deck) {
        for (Player player : players) {
            retryOnIllegalArgument(() -> playerHit(player, deck));
        }
        outputView.printEmptyLine();

        dealerHit(dealer, deck);
    }

    private void playerHit(Player player, Deck deck) {
        while (!player.isBust() && inputView.askHit(player.getName()).isHit()) {
            player.addCard(deck.draw());
            outputView.printPlayerCards(player.getName(), cardsToDtos(player.getCards()));
        }
    }

    private void dealerHit(Dealer dealer, Deck deck) {
        while (dealer.shouldDraw()) {
            dealer.addCard(deck.draw());
            outputView.printDealerHit();
        }
    }

    private void printScore(Collection<Player>players, Dealer dealer) {
        DealerScoreDto dealerDto = DealerScoreDto.from(dealer);
        List<PlayerScoreDto> playerDtos = players.stream()
                .map(PlayerScoreDto::from)
                .toList();

        outputView.printScore(dealerDto, playerDtos);
    }

    private void printResult(Collection<Player>players, Dealer dealer) {
        List<ResultDto> resultDtos = new ArrayList<>();
        for (Player player : players) {
            BlackjackResult result = player.calculateResult(dealer.getHand());
            resultDtos.add(new ResultDto(player.getName(), result));
        }

        outputView.printResult(resultDtos);
    }

    private <T> T retryOnIllegalArgument(Supplier<T> retryableAction) {
        return RetryUtil.retryOnInvalidInput(retryableAction, outputView::printErrorMessage);
    }

    private void retryOnIllegalArgument(Runnable retryableAction) {
        RetryUtil.retryOnInvalidInput(retryableAction, outputView::printErrorMessage);
    }

    private List<CardDto> cardsToDtos(List<Card> cards) {
        return cards.stream()
                .map(CardDto::from)
                .toList();
    }

    private List<PlayerDto> playersToDots(Collection<Player>players) {
        return players.stream()
                .map(PlayerDto::from)
                .toList();
    }
}
