package blackjack.domain.participant;

import java.util.List;

public record PlayerNicknames(List<String> nicknames) {
    
    public PlayerNicknames {
        if (nicknames == null || nicknames.isEmpty()) {
            throw new IllegalArgumentException("한 명 이상의 플레이어 닉네임을 입력해주세요,");
        }
        if (nicknames.stream().anyMatch(String::isBlank)) {
            throw new IllegalArgumentException("플레이어 이름은 공백이 될 수 없습니다.");
        }
        if (nicknames.stream().distinct().count() != nicknames.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }
    
    public static PlayerNicknames from(List<Player> players) {
        return new PlayerNicknames(
                players.stream()
                        .map(Player::getNickname)
                        .toList()
        );
    }
}
