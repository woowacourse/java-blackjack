package blackjack.controller;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.User;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Name;
import blackjack.domain.player.Names;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {
    private Players players;
    private Dealer dealer;

    public void run() {
        getReady();
        giveFirstCards();
        OutputView.printDealerCurrentCards(dealer);
        OutputView.printPlayersCurrentCards(players);
        giveAdditionalCards();
        giveAdditionalCardToDealer();
        OutputView.printResult(dealer, players);
    }

    private void giveAdditionalCards() {
        for (Player player : players.getPlayers()) {
            String answer = InputView.askAdditionalCard(player.getPlayerName());
            giveAdditionalCard(answer, player);
        }
    }

    private static void giveAdditionalCard(String answer, Player player) {
        // TODO : y/n 와 총점이 21이 넘어가는 경우 출력
        while (answer.equals("y") && player.isUnderLimit()) {
            player.updateCardScore(Cards.giveFirstCard());
            OutputView.printPlayerCurrentCards(player);
            System.out.println(player.getTotalScore());
            answer = InputView.askAdditionalCard(player.getPlayerName());
        }
        OutputView.printPlayerCurrentCards(player);
    }

    public void giveAdditionalCardToDealer() {
        while (dealer.isUnderLimit()) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            dealer.updateCardScore(Cards.giveFirstCard());
        }
    }

    private void getReady() {
        List<Name> names = new Names(InputView.inputPeopleNames()).getNames();
        List<Player> players = names.stream().map(Player::new)
                .collect(Collectors.toUnmodifiableList());
        this.players = new Players(players);
        this.dealer = new Dealer();
        OutputView.printReadyMessage(names);
    }

    public void giveFirstCards() {
        Cards.init();
        for (Player player : players.getPlayers()) {
            giveFirstCard(player);
        }
        giveFirstCard(dealer);
    }

    private void giveFirstCard(User user) {
        for (int i = 0; i < 2; i++) {
            user.updateCardScore(Cards.giveFirstCard());
        }
    }
}
