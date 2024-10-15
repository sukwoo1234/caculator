import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 윈도우 표준 계산기만들기를 목적으로 프로그래밍한 간단한 계산기 애플리케이션이다.
 * 기본 산술 연산 및 초기화(C), 삭제(Del), 나머지(%) 기능을 지원한다.
 * Java Swing 컴포넌트를 사용하여 그래픽 사용자 인터페이스를 구현한다.
 */
public class Calculator extends JFrame implements ActionListener {

    private final JTextField display;
    private String operator;
    private double firstNumber;
    private double result;

    /**
     * 계산기를 생성하고 사용자 인터페이스를 초기화함.
     * 메인 윈도우, 디스플레이 필드, 버튼을 설정.
     */
    public Calculator() {
        // 윈도우 생성
        setTitle("Windows-like Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // 화면 중앙에 윈도우 배치

        // 디스플레이 필드
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // 버튼 패널 (GridBagLayout 사용)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // 버튼 레이블
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "Del", "%" // 초기화, 삭제, 나머지 기능 버튼
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
        add(buttonPanel, BorderLayout.CENTER);

        // 프레임을 보이도록 설정
        setVisible(true);
    }

    /**
     * 버튼 클릭 이벤트를 처리하여 계산기 연산을 수행.
     * 버튼에 따라 디스플레이를 업데이트.
     *
     * @param e 버튼 클릭으로 발생한 ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            display.setText(display.getText() + command);
        } else if (command.equals("=")) {
            double secondNumber = Double.parseDouble(display.getText());
            switch (operator) {
                case "+" -> result = firstNumber + secondNumber;
                case "-" -> result = firstNumber - secondNumber;
                case "*" -> result = firstNumber * secondNumber;
                case "/" -> result = firstNumber / secondNumber;
                case "%" -> result = firstNumber % secondNumber;
            }
            display.setText(String.valueOf(result));
        } else if (command.equals("C")) {  // 디스플레이 초기화
            display.setText("");
        } else if (command.equals("Del")) {  // 백스페이스 기능
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        } else {
            firstNumber = Double.parseDouble(display.getText());
            operator = command;
            display.setText("");
        }
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
