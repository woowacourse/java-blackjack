package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    public static final int REPEAT_COUNT = 2;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Trump trump = new Trump(new RandomNumberGenerator());
        Players players = generatePlayers(trump);
        Dealer dealer = new Dealer(getInitialCards(trump));
        showCurrentCards(players, dealer);
        playAllPlayer(players, trump);
        playDealer(dealer, trump);

        showFinalCards(players, dealer);
    }

    private Players generatePlayers(final Trump trump) {
        outputView.printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> playerNames = inputView.readPlayerNames();

        Validator validator = Validator.getInstance();
        validator.validatePlayerNames(playerNames);

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName, getInitialCards(trump)));
        }
        return new Players(players);
    }

    private List<Card> getInitialCards(final Trump trump) {
        List<Card> initialCards = new ArrayList<>();
        for (int i = 0; i < REPEAT_COUNT; i++) {
            initialCards.add(trump.getCard());
        }
        return initialCards;
    }

    private void playAllPlayer(Players players, Trump trump) {
        for (Player player : players.getPlayers()) {
            playEachPlayer(player, trump);
        }
    }

    private void playEachPlayer(Player player, Trump trump) {
        boolean isRepeat = true;
        while (player.isAbleToReceive() && isRepeat) {
            isRepeat = ask(player, trump);
        }
    }

    private boolean ask(Player player, Trump trump) {
        String intention = getIntention(player.getName());
        if (intention.equals("y")) {
            player.receiveCard(trump.getCard());
            showEachPlayerCards(player);
            return true;
        }
        return false;
    }

    private String getIntention(String playerName) {
        outputView.printMessage(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String intention = inputView.readPlayerIntention();
        Validator.getInstance().validatePlayerIntention(intention);
        return intention;
    }

    private void playDealer(Dealer dealer, Trump trump) {
        while (dealer.isAbleToReceive()) {
            outputView.printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            dealer.receiveCard(trump.getCard());
        }
    }

    private void showCurrentCards(Players players, Dealer dealer) {
        List<String> playerNames = players.getPlayers().stream().map(Player::getName).collect(Collectors.toList());
        outputView.printInitialCardDistribution(playerNames);
        showDealerCards(dealer);
        players.getPlayers().forEach(this::showEachPlayerCards);
    }

    private void showEachPlayerCards(Player player) {
        List<String> cards = makeCardNames(player);
        outputView.printEachPlayerCards(player.getName(), cards);
    }

    private List<String> makeCardNames(Participant participant) {
        return participant.getCards().stream()
                .map(this::makeCardName)
                .collect(Collectors.toList());
    }

    private void showDealerCards(Dealer dealer) {
        Card openedDealerCard = dealer.getCards().get(0);
        outputView.printDealerCard(makeCardName(openedDealerCard));
    }

    private String makeCardName(Card card) {
        return card.getTrumpNumber().getName() + card.getTrumpShape().getShape();
    }

    private void showFinalCards(Players players, Dealer dealer) {
        outputView.printFinalCards("딜러 ", makeCardNames(dealer), dealer.getScore());
        players.getPlayers().forEach(
                player -> outputView.printFinalCards(player.getName(), makeCardNames(player), player.getScore()));
    }

}
