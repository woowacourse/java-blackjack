package blackjack.domain;

/**
 * 도메인 로직상 여러곳에서 사용되는 상수를 관리하기 위한 책임을 가지고 있습니다
 * <p/>
 * 많은 클래스가 블랙잭이라는 숫자에 대한 정보를 필요로 하기에 제작되었습니다
 */
class BlackJackConstant {

    static final int BLACKJACK = 21;

    private BlackJackConstant() {
    }
}
