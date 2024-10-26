# 계산기 (Calculator)

이 프로젝트는 Java Swing을 사용하여 구현한 간단한 계산기 애플리케이션입니다. 덧셈, 뺄셈, 곱셈, 나눗셈과 같은 기본 연산을 지원하며, 추가적으로 제곱, 제곱근, 역수 등의 기능도 제공합니다.

## 기능

- **기본 연산**: 덧셈(+), 뺄셈(-), 곱셈(x), 나눗셈(÷)
- **특수 연산**:
  - 제곱 (x²)
  - 제곱근 (√x)
  - 역수 (1/x)
  - 부호 변경 (+/-)
  - 비율 계산 (%)
- **초기화 기능**:
  - 전체 초기화 (C)
  - 현재 입력 초기화 (CE)
  - 마지막 입력 삭제 (Del)
- **연속 계산 지원**: 이전 연산 결과를 이용하여 연속적으로 계산할 수 있습니다.
- **오류 처리**: 잘못된 입력이나 연산 시 오류 메시지를 표시합니다.

## 프로젝트 구조

- `Calculator.java`: 계산기 애플리케이션의 메인 클래스. GUI와 연산 로직을 포함합니다.
- `src/images/calculator.png`: 계산기 아이콘 이미지 파일.

## 설치 및 실행 방법

1. **Java 설치 확인**: Java Development Kit (JDK) 8 이상이 설치되어 있어야 합니다. [JDK 다운로드](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **프로젝트 클론**:
   ```bash
   git clone https://github.com/sukwoo1234/caculator.git
3. **프로젝트 디렉토리로 이동**:
   ```bash
   cd Calculator
4. **컴파일 및 실행**:
   ```bash
   javac Calculator.java
   java Calculator
## 스크린샷
![github1]([복사한 링크 주소](https://github.com/sukwoo1234/caculator/blob/main/github1.png))
![github2]([복사한 링크 주소](https://github.com/sukwoo1234/caculator/blob/main/github2.png))
![github3]([복사한 링크 주소](https://github.com/sukwoo1234/caculator/blob/main/github3.png))

## 사용 라이브러리
- Java Swing: 그래픽 사용자 인터페이스(GUI) 구현을 위해 사용.
- AWT (Abstract Window Toolkit): UI 구성 요소 및 이벤트 처리를 위해 사용.

## 변경 이력 (Changelog)
- 2024-10-15: %, Del 기능 추가.
- 2024-10-18: CE 기능 추가 및 디자인 수정.
- 2024-10-20: 버튼 테두리 및 크기 변경.
- 2024-10-22: 역수 연산 오류 해결.
- 2024-10-23: +/- 버튼 추가, % 연산자 수정.
- 2024-10-28: 연산 결과 및 연산 과정 표시 기능 개선.

##기여 방법
- 이 저장소를 포크합니다.
- 새 브랜치를 생성합니다 (git checkout -b feature/새기능).
- 변경 사항을 커밋합니다 (git commit -am '새 기능 추가').
- 브랜치에 푸시합니다 (git push origin feature/새기능).
- 풀 리퀘스트를 생성합니다.

## Contact
***
- Mail : tnrdn6712@naver.com
- GitHub : https://github.com/sukwoo1234
