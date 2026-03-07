package blackjack.controller;

import blackjack.dto.CardDto;
import blackjack.dto.DealerScoreDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayerScoreDto;
import blackjack.dto.ResultDto;
import blackjack.model.AceAdjustPolicy;
import blackjack.model.Answer;
import blackjack.model.BlackjackResult;
import blackjack.model.BustPolicy;
import blackjack.model.Card;
import blackjack.model.CardsGenerator;
import blackjack.model.Dealer;
import blackjack.model.DealerDrawPolicy;
import blackjack.model.Deck;
import blackjack.model.Player;
import blackjack.model.Players;
import blackjack.model.ResultJudgement;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    private final AceAdjustPolicy aceAdjustPolicy;
    private final DealerDrawPolicy dealerDrawPolicy;
    private final BustPolicy bustPolicy;

    private final CardsGenerator cardsGenerator;
    private final ResultJudgement resultJudgement;

    public BlackjackController(
            InputView inputView,
            OutputView outputView,
            AceAdjustPolicy aceAdjustPolicy,
            DealerDrawPolicy dealerDrawPolicy,
            BustPolicy bustPolicy,
            CardsGenerator cardsGenerator,
            ResultJudgement resultJudgement
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.aceAdjustPolicy = aceAdjustPolicy;
        this.dealerDrawPolicy = dealerDrawPolicy;
        this.bustPolicy = bustPolicy;
        this.cardsGenerator = cardsGenerator;
        this.resultJudgement = resultJudgement;
    }

    public void run() {
        Players players = readPlayers();
        Dealer dealer = new Dealer(aceAdjustPolicy, dealerDrawPolicy);
        Deck deck = Deck.shuffled(cardsGenerator);

        initialDeal(players, dealer, deck);
        hit(players, dealer, deck);
        printScore(players, dealer);
        printResult(players, dealer);
    }

    private Players readPlayers() {
        String rawPlayerNames = inputView.readPlayerNames();

        return Players.from(rawPlayerNames, aceAdjustPolicy);
    }

    private void initialDeal(Players players, Dealer dealer, Deck deck) {
        deal(players, dealer, deck);
        deal(players, dealer, deck);

        outputView.printInitialDeal(
                cardsToDtos(dealer.getCards()),
                playersToDots(players)
        );
    }

    private void deal(Players players, Dealer dealer, Deck deck) {
        for (Player player : players) {
            player.addCard(deck.draw());
        }
        dealer.addCard(deck.draw());
    }

    private void hit(Players players, Dealer dealer, Deck deck) {
        for (Player player : players) {
            playerHit(player, deck);
        }
        dealerHit(dealer, deck);
    }

    private void playerHit(Player player, Deck deck) {
        while (!bustPolicy.isBust(player.getScore()) && askHit(player.getName()) == Answer.YES) {
            player.addCard(deck.draw());
            outputView.printPlayerCards(player.getName(), cardsToDtos(player.getCards()));
        }
    }

    private Answer askHit(String playerName) {
        String answer = inputView.askHit(playerName);

        return Answer.from(answer);
    }

    private void dealerHit(Dealer dealer, Deck deck) {
        if (dealer.shouldDraw()) {
            dealer.addCard(deck.draw());
            outputView.printDealerHit();
        }
    }

    private void printScore(Players players, Dealer dealer) {
        DealerScoreDto dealerDto = DealerScoreDto.from(dealer);
        List<PlayerScoreDto> playerDtos = players.stream()
                .map(PlayerScoreDto::from)
                .toList();

        outputView.printScore(dealerDto, playerDtos);
    }

    private void printResult(Players players, Dealer dealer) {
        List<ResultDto> resultDtos = new ArrayList<>();
        for (Player player : players) {
            BlackjackResult result = resultJudgement.judge(player.getScore(), dealer.getScore());
            resultDtos.add(new ResultDto(player.getName(), result));
        }

        outputView.printResult(resultDtos);
    }

    private List<CardDto> cardsToDtos(List<Card> cards) {
        return cards.stream()
                .map(CardDto::from)
                .toList();
    }

    private List<PlayerDto> playersToDots(Players players) {
        return players.stream()
                .map(PlayerDto::from)
                .toList();
    }
}
