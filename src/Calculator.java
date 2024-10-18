import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 윈도우 표준 계산기 만들기를 목적으로 프로그래밍한 간단한 계산기 애플리케이션이다.
 * 기본 산술 연산 및 초기화(C), 삭제(Del), 나머지(%) 기능을 지원하며,
 * 연산 과정과 결과를 화면에 표시한다.
 * Java Swing 컴포넌트를 사용하여 그래픽 사용자 인터페이스를 구현한다.
 */
public class Calculator extends JFrame implements ActionListener {

    private final JTextField display; // 결과 표시
    private final JTextField processDisplay; // 연산 과정 표시
    private String operator;
    private double currentNumber;
    private boolean isOperatorPressed = false;

    /**
     * 계산기를 생성하고 사용자 인터페이스를 초기화한다.
     * 메인 윈도우, 디스플레이 필드, 버튼을 설정한다.
     */
    public Calculator() {
        // 윈도우 생성
        setTitle("Windows-Calculator");
        setSize(350, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // 화면 중앙에 윈도우 배치

        // 연산 과정을 표시할 필드
        processDisplay = new JTextField();
        processDisplay.setEditable(false);
        processDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        processDisplay.setFont(new Font("Arial", Font.PLAIN, 18));
        processDisplay.setBackground(Color.LIGHT_GRAY);
        processDisplay.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(processDisplay, BorderLayout.NORTH);

        // 결과를 표시할 필드
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.CENTER);

        // 버튼 패널 설정 (GridBagLayout 사용)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // 버튼 레이블 설정
        String[] buttonLabels = {
                "C", "CE", "Del", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                ".", "0", "%", "=" // 초기화, CE, 삭제, 나머지 기능 버튼
        };

        // 버튼 패널에 추가
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5); // 버튼 간격 설정

        int row = 0, col = 0;
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setFocusPainted(false);
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            button.addActionListener(this);

            gbc.gridx = col;
            gbc.gridy = row;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            buttonPanel.add(button, gbc);

            col++;
            if (col == 4) { // 한 행에 4개 버튼
                col = 0;
                row++;
            }
        }

        // 패널을 프레임에 추가
        add(buttonPanel, BorderLayout.SOUTH);

        // 프레임을 보이도록 설정
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

        // 숫자나 소수점 입력
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            if (isOperatorPressed) {
                display.setText(command); // 새로운 숫자 입력 시 기존 값을 지우고 시작
                isOperatorPressed = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("=")) {
            // 현재 연산을 수행하고 결과를 표시
            performCalculation();
            operator = null; // 연산자 초기화
            processDisplay.setText(""); // "=" 이후에는 새로운 연산을 시작하므로 과정 초기화
        } else if (command.equals("C")) {
            // 디스플레이와 변수 초기화
            display.setText("");
            processDisplay.setText("");
            currentNumber = 0;
            operator = null;
        } else if (command.equals("CE")) {
            // 현재 입력 중인 숫자만 초기화
            display.setText("");
        } else if (command.equals("Del")) {
            // 마지막 한 글자 삭제
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else { // 연산자 버튼
            if (operator != null) {
                performCalculation(); // 이전 연산 수행
            } else {
                currentNumber = Double.parseDouble(display.getText()); // 현재 숫자 저장
            }
            operator = command; // 새로운 연산자 설정
            isOperatorPressed = true;

            // 연산 기호와 함께 연산 과정을 화면에 표시
            processDisplay.setText(processDisplay.getText() + display.getText() + " " + operator + " ");
        }
    }

    /**
     * 현재까지 저장된 숫자와 디스플레이에 나타난 값을 이용하여
     * 연산을 수행하고 결과를 디스플레이에 나타낸다.
     */
    private void performCalculation() {
        double secondNumber = Double.parseDouble(display.getText());

        switch (operator) {
            case "+" -> currentNumber += secondNumber;
            case "-" -> currentNumber -= secondNumber;
            case "*" -> currentNumber *= secondNumber;
            case "/" -> currentNumber /= secondNumber;
            case "%" -> currentNumber %= secondNumber;
        }

        display.setText(String.valueOf(currentNumber));
    }

    /**
     * 계산기 애플리케이션을 실행하는 메인 메서드.
     *
     * @param args 명령줄 인수 (사용하지 않음)
     * @throws Exception Look and Feel 설정 실패 시 예외 발생
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        new Calculator();
    }
}
