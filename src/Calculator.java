import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Calculator
 * 이 클래스는 간단한 계산기 애플리케이션을 구현합니다.
 * <p>
 * 기본 산술 연산(덧셈, 뺄셈, 곱셈, 나눗셈) 및 초기화(C),
 * 삭제(Del), 나머지(%) 기능을 지원합니다.
 * <p>
 * Java Swing 컴포넌트를 사용하여
 * 그래픽 사용자 인터페이스를 구현하며, 버튼은 둥근 모서리로
 * 커스터마이즈되어 있습니다.
 * </p>
 * @author Shin Suk Woo (tnrdn6712@naver.com)
 * @version 1.0
 * @since 1.0
 *
 * created 2024-10-12
 * lastModified 2024-10-23
 * @see <a href="https://chatgpt.com/">ChatGPT</a>
 * @see <a href="https://wrtn.ai/">wrtn</a>
 * see 교과서
 *
 * changelog
 * <ul>
 *     <li>2024-10-12: 최초 생성 (Shin Suk Woo)</li>
 *     <li>2024-10-28: 연산결과, 연산과정 등 연산과정에 관련된 요소 생성</li>
 * </ul>
 */
public class Calculator extends JFrame implements ActionListener {

    private final JTextField display; // 결과를 표시하는 텍스트 필드
    private final JTextField processDisplay; // 연산 과정을 표시하는 텍스트 필드
    private String operator; // 현재 선택된 연산자
    private double currentNumber; // 현재까지 계산된 값
    private boolean isOperatorPressed = false; // 연산자가 눌렸는지 확인

    /**
     * 계산기 생성자. 사용자 인터페이스를 초기화합니다.
     * <p>
     * 메인 윈도우, 디스플레이 필드, 버튼 패널을 설정합니다.
     * </p>
     * created 2024-10-12
     * lastModified 2024-10-28
     * @see <a href="https://chatgpt.com/">ChatGPT</a>
     * @see <a href="https://wrtn.ai/">wrtn</a>
     * see 교과서
     *
     * changelog
     * <ul>
     *     <li>2024-10-12: 최초 생성(Shin Suk Woo)</li>
     *     <li>2024-10-15: %, DEL기능 추가</li>
     *     <li>2024-10-18: CE기능 추가, 버튼 위치 변경, 크기와 디자인 수정, 계산기 아이콘 추가</li>
     *     <li>2024-10-20: 버튼 테두리 및 크기 변경</li>
     *     <li>2024-10-22: 역수 연산 오류 해결</li>
     *     <li>2024-10-23: +/-버튼 생성, 버튼 위치 조정, %연산자 수정, 계산기 png 경로 수정</li>
     * </ul>
     */
    public Calculator() {
        setTitle("계산기"); // 윈도우 제목 설정
        setSize(335, 535); // 윈도우 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 시 프로그램 종료
        setLocationRelativeTo(null); // 중앙에 위치

        // 아이콘 설정
        ImageIcon icon = new ImageIcon("src/images/caculator.png");
        setIconImage(icon.getImage());

        // 연산 과정 표시 필드 초기화
        processDisplay = new JTextField();
        processDisplay.setEditable(false);
        processDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        processDisplay.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        processDisplay.setBackground(Color.WHITE);
        processDisplay.setForeground(Color.BLACK);
        processDisplay.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // 결과 표시 필드 초기화
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Malgun Gothic", Font.BOLD, 32));
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 상단 스플릿 패널 생성
        JSplitPane topSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, processDisplay, display);
        topSplitPane.setResizeWeight(0.5); // 비율 설정
        topSplitPane.setDividerSize(0); // 분할선 크기 설정

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5)); // 6x4 그리드 레이아웃
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // 버튼 레이블 배열
        String[] buttonLabels = {
                "%", "CE", "C", "Del",
                "(1)/(x)", "x²", "√x", "÷",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };

        // 버튼 생성 및 이벤트 리스너 추가
        for (String label : buttonLabels) {
            JButton button = getjButton(label);
            button.addActionListener(this); // 클릭 이벤트 리스너 추가
            buttonPanel.add(button); // 버튼 패널에 버튼 추가
        }

        // 메인 스플릿 패널 생성
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, buttonPanel);
        mainSplitPane.setResizeWeight(0.3); // 비율 설정
        mainSplitPane.setDividerSize(0); // 분할선 크기 설정

        add(mainSplitPane); // 메인 프레임에 패널 추가
        setVisible(true); // 윈도우 표시
    }

    private static JButton getjButton(String label) {
        JButton button = new JButton(label) {
            @Override
            public void setUI(ButtonUI ui) {
                super.setUI(new RoundedButtonUI()); // 버튼 UI를 둥근 모서리로 설정
            }
        };
        button.setFont(new Font("Arial", Font.PLAIN, 20)); // 버튼 글꼴 설정
        button.setFocusPainted(false); // 포커스 표시 제거
        button.setBackground(Color.WHITE); // 배경색 설정
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // 테두리 설정
        button.setPreferredSize(new Dimension(60, 40)); // 버튼 크기 설정
        return button;
    }

    /**
     * 버튼 클릭 이벤트를 처리하여 계산기 연산을 수행하고,
     * 버튼에 따라 디스플레이를 업데이트합니다.
     *
     * @param e 버튼 클릭으로 발생한 ActionEvent
     * created 2024-10-12
     * lastModified 2024-10-28
     * @see <a href="https://chatgpt.com/">ChatGPT</a>
     * @see <a href="https://wrtn.ai/">wrtn</a>
     * see 교과서
     *
     * changelog
     * <ul>
     *     <li>2024-10-12: 최초 생성(Shin Suk Woo)</li>
     *     <li>2024-10-18: CE기능 추가</li>
     *     <li>2024-10-20: 소수점 변환 업데이트</li>
     *     <li>2024-10-23: 연속 계산에서 오류 수정, 연산과정 오류 수정, 소수점 수정</li>
     * </ul>
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // 클릭된 버튼의 명령어

        // 숫자 또는 소수점 입력 처리
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            if (command.equals(".")) {
                // 현재 display에 숫자가 없고, 소수점이 입력된 경우 무시
                if (display.getText().isEmpty() || display.getText().equals(".")) {
                    return; // 소수점만 입력할 수 없음
                }
            }
            if (isOperatorPressed) {
                display.setText(command); // 새로운 숫자로 대체
                isOperatorPressed = false; // 연산자 눌림 상태 초기화
            } else {
                display.setText(display.getText() + command); // 기존 숫자에 추가
            }
        }
        // "=" 버튼 클릭 시 연산 수행
        else if (command.equals("=")) {
            performCalculation();
            operator = null; // 연산자 초기화
            processDisplay.setText(""); // 연산 과정 초기화
        }
        // "C" 버튼 클릭 시 전체 초기화
        else if (command.equals("C")) {
            display.setText(""); // 결과 표시 초기화
            processDisplay.setText(""); // 연산 과정 초기화
            currentNumber = 0; // 현재 숫자 초기화
            operator = null; // 연산자 초기화
        }
        // "CE" 버튼 클릭 시 현재 입력 초기화
        else if (command.equals("CE")) {
            display.setText(""); // 현재 입력 초기화
        }
        // "Del" 버튼 클릭 시 마지막 문자 삭제
        else if (command.equals("Del")) {
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1)); // 마지막 문자 삭제
            }
        }
        // "+/-" 버튼 클릭 시 부호 변경
        else if (command.equals("+/-")) {
            try {
                double value = Double.parseDouble(display.getText());
                value = -value; // 부호 변경
                // 정수로 표현 가능한 경우 소수점을 제거하고 표시
                if (value == (int) value) {
                    display.setText(String.valueOf((int) value));
                } else {
                    display.setText(String.valueOf(value));
                }
            } catch (NumberFormatException ex) {
                display.setText("오류"); // 숫자가 아닌 값이 입력된 경우 오류 표시
            }
        }

        // "%" 버튼 클릭 시 비율 계산
        else if (command.equals("%")) {
            try {
                double value = Double.parseDouble(display.getText());
                double percentValue = value / 100; // 비율 계산

                // 디스플레이에 비율 표시
                display.setText(String.valueOf(percentValue));

                // 연산 과정 표시 업데이트
                processDisplay.setText(currentNumber + " * " + percentValue + " = ");

                // 현재 연산자 설정 (곱셈)
                operator = "x";
                isOperatorPressed = true; // 연산자 눌림 상태로 설정
            } catch (NumberFormatException ex) {
                display.setText("오류"); // 숫자가 아닌 값이 입력된 경우 오류 표시
            }
        }

        // "1/x" 버튼 클릭 시 역수 계산
        else if (command.equals("(1)/(x)")) {
            try {
                double value = Double.parseDouble(display.getText());
                if (value == 0) {
                    processDisplay.setText("1/(0)"); // 0으로 나누기 방지
                    display.setText("오류"); // 오류 표시
                    return;
                }

                // 역수 계산
                double result = 1 / value;

                // 연산 과정 표시
                processDisplay.setText("1/(" + value + ")");

                // 결과 표시
                display.setText(result + "");

            } catch (NumberFormatException ex) {
                display.setText("오류"); // 숫자가 아닌 값이 입력된 경우 오류 표시
            }
        }
        // "x²" 버튼 클릭 시 제곱 계산
        else if (command.equals("x²")) {
            double value = Double.parseDouble(display.getText());
            processDisplay.setText("sqr(" + value + ")"); // 연산 과정 표시
            display.setText(String.valueOf(value * value)); // 제곱 결과 표시
            isOperatorPressed = true; // 연산 완료 상태로 설정
        }
        // "√x" 버튼 클릭 시 제곱근 계산
        else if (command.equals("√x")) {
            double value = Double.parseDouble(display.getText());
            processDisplay.setText("√(" + value + ")"); // 연산 과정 표시
            display.setText(String.valueOf(Math.sqrt(value))); // 제곱근 결과 표시
            isOperatorPressed = true; // 연산 완료 상태로 설정
        }
        // 연산자 버튼 클릭 시
        else {
            // 현재 디스플레이의 숫자를 currentNumber에 저장
            if (operator != null) {
                performCalculation(); // 이전 연산 계산
            } else {
                currentNumber = Double.parseDouble(display.getText()); // 현재 숫자 저장
            }

            // 연산자 업데이트
            operator = command;
            isOperatorPressed = true;

            // 연산 과정 표시 업데이트
            String processText = (currentNumber == (int) currentNumber) ?
                    String.valueOf((int) currentNumber) : String.valueOf(currentNumber);

            // 연산 과정 업데이트
            processDisplay.setText(processText + " " + operator + " ");

            // 디스플레이 초기화
            display.setText("");
        }
    }

    /**
     * 현재 설정된 연산자에 따라 계산을 수행합니다.
     * <p>
     * 연산 결과는 <code>currentNumber</code> 변수에 저장되고,
     * 결과 필드에 표시됩니다.
     * </p>
     * created 2024-10-12
     * lastModified 2024-10-28
     * @see <a href="https://chatgpt.com/">ChatGPT</a>
     * @see <a href="https://wrtn.ai/">wrtn</a>
     * see 교과서
     *
     * changelog
     * <ul>
     *     <li>2024-10-12: 최초 생성(Shin Suk Woo)</li>
     *     <li>2024-10-20: 소수점 변환</li>
     *     <li>2024-10-23: %연산자 수정</li>
     * </ul>
     *
     */
    private void performCalculation() {
        double secondNumber = Double.parseDouble(display.getText()); // 두 번째 숫자 가져오기

        switch (operator) {
            case "+" -> currentNumber += secondNumber; // 덧셈
            case "-" -> currentNumber -= secondNumber; // 뺄셈
            case "x" -> currentNumber *= secondNumber; // 곱셈
            case "÷" -> currentNumber /= secondNumber; // 나눗셈
        }

        // 정수로 표현 가능한 경우 소수점을 제거하고 표시
        if (currentNumber == (int) currentNumber) {
            display.setText(String.valueOf((int) currentNumber)); // 정수로 표시
        } else {
            display.setText(String.valueOf(currentNumber)); // 실수로 표시
        }
    }

    /**
     * 프로그램 실행 시 계산기 애플리케이션을 생성합니다.
     * @param args 명령줄 인수 (사용되지 않음)
     * @throws Exception UI 설정 시 발생할 수 있는 예외
     * created 2024-10-12
     * lastModified 2024-10-28
     * @see <a href="https://chatgpt.com/">ChatGPT</a>
     * @see <a href="https://wrtn.ai/">wrtn</a>
     * see 교과서
     *
     * changelog
     * <ul>
     *      <li>2024-10-12: 최초 생성(Shin Suk Woo)</li>
     * </ul>
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        new Calculator(); // 계산기 인스턴스 생성
    }

    /**
     * 버튼을 둥근 모서리로 설정하기 위한 UI 커스터마이징 클래스입니다.
     * <p>
     * 버튼의 배경, 테두리 및 포커스 효과를 커스터마이즈합니다.
     * </p>
     * created 2024-10-12
     * lastModified 2024-10-28
     * @see <a href="https://chatgpt.com/">ChatGPT</a>
     * @see <a href="https://wrtn.ai/">wrtn</a>
     * see 교과서
     *
     * changelog
     * <ul>
     *     <li>2024-10-12: 최초 생성(Shin Suk Woo)</li>
     *     <li>2024-10-20: 버튼 테두리 및 크기 변결</li>
     * </ul>
     */
    private static class RoundedButtonUI extends BasicButtonUI {
        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false); // 배경 불투명도 설정
            button.setBorderPainted(false); // 테두리 그리기 설정
            button.setFocusable(false); // 포커스 가능 여부 설정
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, b.getModel().isPressed() ? 2 : 0); // 배경 그리기
            super.paint(g, c); // 기본 버튼 그리기
        }
        /**
         * 버튼 배경을 둥글게 그리는 메서드입니다.
         *
         * @param g 그래픽스 객체4
         * @param c 버튼 컴포넌트
         * @param yOffset 버튼이 눌렸을 때의 오프셋
         * created 2024-10-12
         * lastModified 2024-10-28
         * @see <a href="https://chatgpt.com/">ChatGPT</a>
         * @see <a href="https://wrtn.ai/">wrtn</a>
         * see 교과서
         *
         * changelog
         * <ul>
         *     <li>2024-10-12: 최초 생성(Shin Suk Woo)</li>
         *     <li>2023-10-18: 디자인 수정, 버튼 크기 일정</li>
         *     <li>2023-10-20: 버튼 테두리 및 크기 변경</li>
         * </ul>
         *
         * 
         */

        private void paintBackground(Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground()); // 배경색 설정
            g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10); // 둥근 사각형 그리기
        }
    }
}
