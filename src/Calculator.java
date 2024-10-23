import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 윈도우 표준 계산기 만들기를 목적으로 프로그래밍한 간단한 계산기 애플리케이션이다.
 * 기본 산술 연산 및 초기화(C), 삭제(Del), 나머지(%) 기능을 지원하며,
 * 연산 과정과 결과를 화면에 표시한다.
 * Java Swing 컴포넌트를 사용하여 그래픽 사용자 인터페이스를 구현한다.
 * 버튼을 둥근 모서리로 커스터마이징하고, 버튼의 크기를 조정하였다.
 */
public class Calculator extends JFrame implements ActionListener {

    private final JTextField display; // 결과 표시
    private final JTextField processDisplay; // 연산 과정 표시
    private String operator; // 현재 연산자
    private double currentNumber; // 현재까지 계산된 값
    private boolean isOperatorPressed = false; // 연산자가 눌렸는지 확인

    /**
     * 계산기를 생성하고 사용자 인터페이스를 초기화한다.
     * 메인 윈도우, 디스플레이 필드, 버튼을 설정한다.
     */
    public Calculator() {
        setTitle("계산기");
        setSize(335, 535);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("C:\\Users\\USER\\IdeaProjects\\caculator\\src\\images\\caculator.png");
        setIconImage(icon.getImage());

        processDisplay = new JTextField();
        processDisplay.setEditable(false);
        processDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        processDisplay.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        processDisplay.setBackground(Color.WHITE);
        processDisplay.setForeground(Color.BLACK);
        processDisplay.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Malgun Gothic", Font.BOLD, 32));
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JSplitPane topSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, processDisplay, display);
        topSplitPane.setResizeWeight(0.5);
        topSplitPane.setDividerSize(0);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        String[] buttonLabels = {
                "%", "CE", "C", "Del",
                "(1)/(x)", "x²", "√x", "÷",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label) {
                @Override
                public void setUI(ButtonUI ui) {
                    super.setUI(new RoundedButtonUI());
                }
            };
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setFocusPainted(false);
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            button.setPreferredSize(new Dimension(60, 40));
            button.addActionListener(this); // new Calculaor()
            buttonPanel.add(button);
        }

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, buttonPanel);
        mainSplitPane.setResizeWeight(0.3);
        mainSplitPane.setDividerSize(0);

        add(mainSplitPane);
        setVisible(true);
    }

    /**
     * 버튼 클릭 이벤트를 처리하여 계산기 연산을 수행하고,
     * 버튼에 따라 디스플레이를 업데이트한다.
     *
     * @param e 버튼 클릭으로 발생한 ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // 숫자나 소수점 입력 시
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            if (isOperatorPressed) {
                display.setText(command);
                isOperatorPressed = false;
            } else {
                display.setText(display.getText() + command);
            }
        }
        // "=" 버튼을 눌렀을 때 연산 수행
        else if (command.equals("=")) {
            performCalculation();
            operator = null;
            processDisplay.setText("");
        }
        // "C" 버튼을 눌렀을 때 전체 초기화
        else if (command.equals("C")) {
            display.setText("");
            processDisplay.setText("");
            currentNumber = 0;
            operator = null;
        }
        // "CE" 버튼을 눌렀을 때 현재 입력 초기화
        else if (command.equals("CE")) {
            display.setText("");
        }
        // "Del" 버튼을 눌렀을 때 마지막 문자 삭제
        else if (command.equals("Del")) {
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
        // "+/-" 버튼을 눌렀을 때 부호 변경
        else if (command.equals("+/-")) {
            try {
                double value = Double.parseDouble(display.getText());
                value = -value;
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

// "%" 버튼을 눌렀을 때
        else if (command.equals("%")) {
            try {
                double value = Double.parseDouble(display.getText());
                // 비율만 표시하기 위해 value / 100을 계산
                double percentValue = value / 100;

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

        // "1/x" 버튼을 눌렀을 때 역수 계산
        else if (command.equals("(1)/(x)")) {
            try {
                double value = Double.parseDouble(display.getText());
                if (value == 0) {
                    processDisplay.setText("1/(0)");
                    display.setText("오류"); // 0으로 나누기 방지
                    return;
                }

                // 역수 계산
                double result = 1 / value;

                // process
                processDisplay.setText("1/(" + value + ")");

                // display
                display.setText(result + "");

            } catch (NumberFormatException ex) {
                display.setText("오류"); // 숫자가 아닌 값이 입력된 경우 오류 표시
            }
        }
        // "x²" 버튼을 눌렀을 때 제곱 계산
        else if (command.equals("x²")) {
            double value = Double.parseDouble(display.getText());
            processDisplay.setText("sqr(" + value + ")");
            display.setText(String.valueOf(value * value));
            isOperatorPressed = true; // 연산 완료 상태로 설정
        }
        // "√x" 버튼을 눌렀을 때 제곱근 계산
        else if (command.equals("√x")) {
            double value = Double.parseDouble(display.getText());
            processDisplay.setText("√(" + value + ")");
            display.setText(String.valueOf(Math.sqrt(value)));
            isOperatorPressed = true; // 연산 완료 상태로 설정
        }
        // 연산자 버튼을 눌렀을 때
        else {
            // 현재 디스플레이의 숫자를 currentNumber에 저장
            if (operator != null) {
                performCalculation();
            } else {
                currentNumber = Double.parseDouble(display.getText());
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
     * 현재 설정된 연산자에 따라 계산을 수행한다.
     * 연산 결과는 currentNumber 변수에 저장되고, 결과 필드에 표시된다.
     */
    private void performCalculation() {
        double secondNumber = Double.parseDouble(display.getText());

        switch (operator) {
            case "+" -> currentNumber += secondNumber;
            case "-" -> currentNumber -= secondNumber;
            case "x" -> currentNumber *= secondNumber;
            case "÷" -> currentNumber /= secondNumber;
        }

        // 정수로 표현 가능한 경우 소수점을 제거하고 표시
        if (currentNumber == (int) currentNumber) {
            display.setText(String.valueOf((int) currentNumber));
        } else {
            display.setText(String.valueOf(currentNumber));
        }
    }

    /**
     * 프로그램 실행 시 계산기 애플리케이션을 생성한다.
     * @param args 명령줄 인수 (사용되지 않음)
     * @throws Exception UI 설정 시 발생할 수 있는 예외
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        new Calculator();
    }

    /**
     * 버튼을 둥근 모서리로 설정하기 위한 UI 커스터마이징 클래스이다.
     */
    private static class RoundedButtonUI extends BasicButtonUI {
        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
            button.setBorderPainted(false);
            button.setFocusable(false);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
            super.paint(g, c);
        }

        private void paintBackground(Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground());
            g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
        }
    }
}
