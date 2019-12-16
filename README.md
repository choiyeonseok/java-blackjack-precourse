# 블랙잭 게임

## 기능 요구사항
1. 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임 구현.
2. 플레이어는 게임을 시작할 때 배팅 금액을 정함.
3. 카드의 숫자 계산은 숫자를 기본으로 함. 예외) Ace는 1또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산
4. 게임을 시작 시 플레이어와 딜러는 두 장의 카드 배분 받음.
5. 플레이어는 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이김. 단, 21을 넘지 않은 경우 원하면 계속 카드를 뽑음.
6. 플레이어는 처음 받은 두 장의 카드 숫자 합이 21인 경우 블랙잭이 되면 배팅 금액의 1.5배를 딜러에게 받음. 단, 딜러도 블랙잭이면 배팅금액을 도로 가져옴.
7. 딜러는 처음 받은 두 장의 카드 숫자 합이 16이하이면 17이상이 될 때까지 카드를 계속 뽑아야 함.
8. 딜러가 BUST 될시 남아있는 플레이어는 모두 승리함.

## 기능목록 (역할 + 책임)
1. **Symbol**
    - 카드 별로 숫자와, 점수 저장
2. **Type**
    - 카드의 총 4가지 타입 정의
    - 출력을 위한 한글 단어 저장
3. **Card**
    - 카드에 해당하는 심볼, 타입 및 점수 접근자
4. **Player**
    - 보유한 카드셋에 카드 추가
    - 현재 보유한 카드셋의 총 점수를 리턴
        - ACE 카드 보유 시 1 또는 11로 유연하게 사용가능 (총 점이 21미만이자 21에 가깝게 사용)
    - 현재 보유한 카드의 정보 출력 ⇒ String
5. **Dealer**
    - 보유한 카드셋에 카드 추가
    - 현재 보유한 카드셋의 총 점수를 리턴
        - ACE 카드 보유 시 1 또는 11로 유연하게 사용가능 (총 점이 21미만이자 21에 가깝게 사용)
    - 현재 보유한 카드의 정보 출력 ⇒ String
        - 처음에는 2장 중 1장만 출력
6. **BlackJackGame(UI)**
    1. 플레이어 정보 입력받고, 인스턴스 생성
    2. 딜러 및 게임 참가자의 초기 dealing 이후 현황 출력
    3. 플레이어 별로 순서대로 게임 진행
        - 처음 2장의 점수가 21일 경우 BLACKJACK! ⇒ 배팅머니의 1.5배 수익(단, 딜러도 처음 2장 점수가 21일 경우 무승부 처리 ⇒ 수익 0)
        - 현재 점수가 21 이하일 경우 21에 가까워질때 까지 카드를 뽑을 수 있음.
        - 21이 넘으면 BUST. ⇒ 배팅 머니를 모두 잃게 됨.
    4. 최종 점수 출력
    5. 최종 수익 출력
7. **InputManager(UI)**
    - 플레이어 이름 입력 + **예외처리**
    - 플레이어 배팅 금액 입력 + **예외처리**
        - 금액에서 문자가 입력되었을 경우
        - 금액이 0이하의 수가 입력되었을 경우
    - Hit 또는 Stay 여부 입력받기 + **예외처리**
        - y, n 이외의 문자가 입력되었을 경우
        - 숫자가 입력되었을 경우
8. **CardShoe**
    - 카드 더미 생성
    - 카드 섞기
    - 딜러 및 플레이어에게 한장 씩 배분
9. **RewardCalculator**
    - 블랙잭 리워드 계산
    - 플레이어 버스트 리워드 계산
    - 게임 종료 후 최종 점수를 전달 받아서 최종 수익 계산
        - 딜러 버스트 리워드 계산
        - 플레이어 승리 리워드 계산
        - 플레이어 패배 리워드 계산
    - 리워드 출력 ⇒ String
10. **Main**
    - BlackJackGame 게임 실행

## 클래스 다이어그램
![blackjack2](https://user-images.githubusercontent.com/42382027/70907765-0f54ae80-204d-11ea-918d-931b6c8019cb.JPG)
