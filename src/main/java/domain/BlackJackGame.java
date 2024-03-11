package domain;

import domain.dto.GameStatus;
import domain.dto.GamerDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    private static final int MAX_PLAYERS_COUNT = 8;
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(List<Player> players) {
        validatePlayerCount(players);
        this.players = players;
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException("플레이어의 수는 최대 8명 입니다.");
        }
    }

    public void initialDealing() {
        deck.shuffle();
        takeTwoCards(dealer);
        players.forEach(this::takeTwoCards);
    }

    private void takeTwoCards(Gamer gamer) {
        gamer.takeCard(deck.draw());
        gamer.takeCard(deck.draw());
    }

    public GameStatus getGameStatusDto() {
        return GameStatus.of(GamerDto.fromDealer(dealer), toGamerDto());
    }

    public List<GamerDto> toGamerDto() {
        List<GamerDto> gamerDtos = new ArrayList<>();
        players.stream()
                .map(GamerDto::fromPlayer)
                .forEach(gamerDtos::add);
        return Collections.unmodifiableList(gamerDtos);
    }

    public void drawCardFromName(String name) {
        players.stream().filter(player -> player.isNameOf(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자 입니다."))
                .takeCard(deck.draw());
    }

    public boolean isBustFromName(String name) {
        return players.stream().filter(player -> player.isNameOf(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자 입니다."))
                .isBust();
    }

    public int drawDealerCard() {
        return dealer.drawAndGetCount(deck);
    }

    public BlackJackResult getGameResult() {
        return new BlackJackResult(
                dealer.getTotalScore(),
                players.stream()
                        .collect(Collectors.toMap(Player::getName, Gamer::getTotalScore)));
    }
}
