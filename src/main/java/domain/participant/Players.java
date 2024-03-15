package domain.participant;

import domain.blackjack.HitOption;
import domain.dto.ParticipantDto;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {

    private static final int MIN_PARTICIPANT_COUNT = 2;
    private static final int MAX_PARTICIPANT_COUNT = 8;
    private static final int DEALER_MULTIPLIER = -1;

    private final List<Player> value;

    public Players(final List<Player> players) {
        validate(players);
        this.value = players;
    }

    private static void validate(List<Player> players) {
        if (players.size() < MIN_PARTICIPANT_COUNT || players.size() > MAX_PARTICIPANT_COUNT) {
            throw new IllegalArgumentException(String.format("최소 %d명 최대 %d명까지 입력받을 수 있습니다.", MIN_PARTICIPANT_COUNT, MAX_PARTICIPANT_COUNT));
        }

        Set<Player> distinctNames = Set.copyOf(players);
        if (distinctNames.size() != players.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    public void beginDealing(Dealer dealer) {
        dealer.receiveCard(dealer.draw());
        dealer.receiveCard(dealer.draw());
        for (Player player : value) {
            player.receiveCard(dealer.draw());
            player.receiveCard(dealer.draw());
        }
    }

    public void allPlayerHit(Function<Name, HitOption> isHitOption, Consumer<ParticipantDto> printPlayerHands, Dealer dealer) {
        for (Player player : value) {
            playerHit(isHitOption, printPlayerHands, player, dealer);
        }
    }

    private void playerHit(Function<Name, HitOption> isHitOption, Consumer<ParticipantDto> printPlayerHands, Player player, Dealer dealer) {
        while (player.canHit() && HitOption.isHit(isHitOption.apply(player.getName()))) {
            player.receiveCard(dealer.draw());
            printPlayerHands.accept(ParticipantDto.from(player));
        }
    }

    public double getDealerPayout(Dealer dealer) {
        double total = 0;
        for (Player player : value) {
            total += player.getPayout(dealer);
        }
        return total * DEALER_MULTIPLIER;
    }

    public List<Name> getNames() {
        return value.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getValue() {
        return value;
    }
}
