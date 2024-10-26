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
   git clone https://github.com/username/Calculator.git
3. **프로젝트 디렉토리로 이동**:
   ```bash
   cd Calculator
4. **컴파일 및 실행**:
   ```bash
   javac Calculator.java
   java Calculator
## 스크린샷

