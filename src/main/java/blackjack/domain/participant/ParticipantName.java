package blackjack.domain.participant;

class ParticipantName {

    private final String name;

    public ParticipantName(String name) {
        validateNameSize(name);

        this.name = name;
    }

    private void validateNameSize(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("참여자의 이름은 공백을 제외한 1글자 이상입니다.");
        }
    }

    public String getValue() {
        return name;
    }
}
