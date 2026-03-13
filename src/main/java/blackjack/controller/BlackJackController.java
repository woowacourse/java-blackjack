package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.EarningResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    private static final int INIT_DRAW_CARD_COUNT = 2;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        List<String> names = inputView.readNames();
        List<Long> bettingAmounts = readBettingAmount(names);
        Players players = new Players(names, bettingAmounts);
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        initDraw(dealer, players, deck);

        printInitDrawResult(dealer, players);

        playerTurn(players, deck);

        dealerTurn(dealer, deck);

        printFinalCardResult(dealer, players);

        printFinalEarningResult(dealer, players);
    }

    private List<Long> readBettingAmount(List<String> names) {
        List<Long> bettingAmounts = new ArrayList<>();
        for (String name : names) {
            bettingAmounts.add(inputView.readBettingAmount(name));
        }
        return bettingAmounts;
    }

    private ParticipantDto convertToDto(Participant participant) {
        List<String> cardNames = participant.getCards().stream().map(Card::getName).toList();
        return new ParticipantDto(participant.getName(), cardNames, participant.getTotalPoint());
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canHit()) {
            outputView.printDealerDraw();
            dealer.receiveCard(deck.draw());
        }
    }

    private void playerTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            drawCardUntilStop(player, deck);
        }
    }

    private void printInitDrawResult(Dealer dealer, Players players) {
        ParticipantDto dealerDto = convertToDto(dealer);
        List<ParticipantDto> playerDtos = players.getPlayers().stream().map(this::convertToDto).toList();
        outputView.printInitDraw(dealerDto, playerDtos);
    }

    private void printFinalCardResult(Dealer dealer, Players players) {
        ParticipantDto dealerDto = convertToDto(dealer);
        List<ParticipantDto> playerDtos = players.getPlayers().stream().map(this::convertToDto).toList();
        outputView.printFinalCardResult(dealerDto, playerDtos);
    }

    private void drawCardUntilStop(Player player, Deck deck) {
        while (!player.isBust() && inputView.readHitAnswer(player.getName())) {
            player.receiveCard(deck.draw());
            outputView.printCard(convertToDto(player));
        }
    }

    private void initDraw(Dealer dealer, Players players, Deck deck) {
        for (int i = 0; i < INIT_DRAW_CARD_COUNT; i++) {
            players.receiveCard(deck);
            dealer.receiveCard(deck.draw());
        }
    }

    private void printFinalEarningResult(Dealer dealer, Players players) {
        List<EarningResultDto> earningResultDtos = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            earningResultDtos.add(
                    new EarningResultDto(
                            player.getName(),
                            player.calculateEarningAmount(player.compareResult(dealer))
                    )
            );
        }
        outputView.printEarningResult(earningResultDtos);
    }
}
