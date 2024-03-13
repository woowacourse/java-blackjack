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

    private final List<Player> value;

    public Players(final List<String> names) {
        validate(names);
        value = names.stream().map(name -> new Player(new Name(name))).toList();
    }

    private static void validate(List<String> names) {
        if (names.size() < MIN_PARTICIPANT_COUNT || names.size() > MAX_PARTICIPANT_COUNT) {
            throw new IllegalArgumentException(String.format("최소 %d명 최대 %d명까지 입력받을 수 있습니다.", MIN_PARTICIPANT_COUNT, MAX_PARTICIPANT_COUNT));
        }

        Set<String> distinctNames = Set.copyOf(names);
        if (distinctNames.size() != names.size()) {
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

    public void playersHit(Function<Name, HitOption> isHitOption, Consumer<ParticipantDto> printPlayerHands, Dealer dealer) {
        for (Player player : value) {
            playerHit(isHitOption, printPlayerHands, player, dealer);
        }
    }

    private void playerHit(Function<Name, HitOption> isHitOption, Consumer<ParticipantDto> printPlayerHands, Player player, Dealer dealer) {
        while (player.canHit() && HitOption.isHit(isHitOption.apply(player.getName()))) {
            player.receiveCard(dealer.draw());
            printPlayerHands.accept(player.getDto());
        }
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
