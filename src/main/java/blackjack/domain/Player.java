package blackjack.domain;

public class Player extends Participant {

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름에 빈 값이 올 수 없습니다.");
        }
    }

//        private void hasBlankNames(List<String> playerNames) {
//        if(playerNames.stream().anyMatch(x -> x.trim().isEmpty())) {
//            throw new IllegalArgumentException("[ERROR] 이름은 공백만 올 수 없습니다.");
//        }
//    }

    public String getName() {
        return name;
    }
}
