package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.MatchResult;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerDto;
import blackjack.domain.user.ResultDTO;
import blackjack.domain.user.User;
import blackjack.domain.user.WinningResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {
    private static final String ANSWER_YES = "y";

    private final User dealer;
    private final List<User> players;
    private final Deck deck;

    public Game(List<User> players) {
        this.dealer = new Dealer();
        this.players = players;
        this.deck = new Deck();
    }

    public void initialCards() {
        dealer.initialHands(deck.pickInitialCards());
        players.forEach(player -> player.initialHands(deck.pickInitialCards()));
    }

    public void askDrawToPlayer(PlayerDto playerDto, String askIfMoreCard) {
        players.stream()
            .filter(player -> player.getName().equals(playerDto.getName()))
            .forEach(player -> drawCardOrChangeStatus(askIfMoreCard, player));
    }

    private void drawCardOrChangeStatus(String askIfMoreCard, User player) {
        if (askIfMoreCard.equals(ANSWER_YES)) {
            player.draw(deck.pickSingleCard());
            return;
        }
        player.setStatusToStay();
    }

    public boolean askDrawToDealer() {
        if (!dealer.isHit()) {
            return false;
        }
        drawDealerHand();
        return true;
    }

    public void drawDealerHand() {
        while (dealer.isHit()) {
            dealer.draw(deck.pickSingleCard());
        }
    }

    public User getDealer() {
        return dealer;
    }

    public List<User> getPlayers() {
        return players;
    }

    public boolean isAnyPlayerHit() {
        return players.stream().anyMatch(User::isHit);
    }

    public PlayerDto getAnyHitPlayerDto() {
        Optional<User> first = players.stream()
            .filter(User::isHit)
            .findFirst();
        if (!first.isPresent()) {
            throw new IllegalArgumentException("Hit 이면서 주어진 이름과 같은 플레이어가 존재하지 않습니다.");
        }
        return new PlayerDto(first.get());
    }

    public List<ResultDTO> getResultDTOs() {
        List<ResultDTO> resultDTOS = new ArrayList<>();
        resultDTOS.add(dealer.getResultDTO());
        players.forEach(player -> resultDTOS.add(player.getResultDTO()));

        return resultDTOS;
    }

    public List<WinningResultDTO> getWinningResultDTOs() {
        return players.stream().map(player ->
            new WinningResultDTO(player.getName(), ((Player)player).getMoney(), MatchResult.calculateResult(player, dealer)))
            .collect(Collectors.toList());
    }
}
