package blackjack.controller;

import blackjack.dto.DealerDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.DtoGenerator;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersResultDto;
import blackjack.model.GameRule;
import blackjack.model.card.Card;
import blackjack.model.deck.DeckGenerator;
import blackjack.model.deck.PlayingDeck;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;
import blackjack.model.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck());

    public void run() {
        List<Player> players = registerPlayer();
        playBlackJack(new Dealer(), players);
    }

    private List<Player> registerPlayer() {
        List<String> playersName = InputView.readPlayersName();
        return playersName.stream()
                .map(Player::new)
                .toList();
    }

    private void playBlackJack(Dealer dealer, List<Player> players) {
        initialDraw(dealer, players);
        runPlayerTurn(players);
        runDealerTurn(dealer);
        gameResult(dealer, players);
    }

    private void initialDraw(Dealer dealer, List<Player> players) {
        drawCardToDealer(dealer);
        drawCardToPlayer(players);

        DealerDto dealerDto = DtoGenerator.createInitialDealerDto(dealer.getFirstCard());
        List<PlayerDto> playerDtos = DtoGenerator.createPlayerDtos(players);
        OutputView.printInitialDrawResult(dealerDto, playerDtos);
    }

    private void drawCardToDealer(Dealer dealer) {
        Card firstDealerCard = playingDeck.drawCard();
        Card secondDealerCard = playingDeck.drawCard();

        dealer.receiveCard(firstDealerCard);
        dealer.receiveCard(secondDealerCard);
    }

    private void drawCardToPlayer(List<Player> players) {
        for (Player player : players) {
            Card firstPlayerCard = playingDeck.drawCard();
            Card secondPlayerCard = playingDeck.drawCard();

            player.receiveCard(firstPlayerCard);
            player.receiveCard(secondPlayerCard);
        }
    }

    private void runPlayerTurn(List<Player> players) {
        for (Player player : players) {
            hitOrStand(player);
        }
    }

    private void hitOrStand(Player player) {
        while (player.canHit() && InputView.askPlayerForCard(player)) {
            executeHit(player);
        }
        if (player.canHit()) {
            PlayerDto playerDto = DtoGenerator.createPlayerDto(player);
            OutputView.printPlayerCard(playerDto);
        }
    }

    private void executeHit(Player player) {
        Card card = playingDeck.drawCard();
        player.receiveCard(card);
        PlayerDto playerDto = DtoGenerator.createPlayerDto(player);
        OutputView.printPlayerCard(playerDto);
    }

    private void runDealerTurn(Dealer dealer) {
        if (dealer.canHit()) {
            Card card = playingDeck.drawCard();
            dealer.receiveCard(card);
            OutputView.printDealerHit();
        }
    }

    private void gameResult(Dealer dealer, List<Player> players) {
        showScore(dealer, players);

        GameResult gameResult = new GameResult();
        calculateResult(dealer, players, gameResult);

        PlayersResultDto playerResultDto = DtoGenerator.createPlayerResultDto(players, gameResult);
        DealerResultDto dealerResultDro = DtoGenerator.createDealerResultDro(gameResult);
        OutputView.printResult(dealerResultDro, playerResultDto);
    }

    private void calculateResult(Dealer dealer, List<Player> players, GameResult gameResult) {
        for (Player player : players) {
            GameRule.decideWinner(dealer, player, gameResult);
        }
    }

    private void showScore(Dealer dealer, List<Player> players) {
        DealerDto dealerDto = DtoGenerator.createDealerDto(dealer);
        List<PlayerDto> playerDtos = DtoGenerator.createPlayerDtos(players);

        OutputView.printGamerCardAndScore(dealerDto, playerDtos);
    }
}
