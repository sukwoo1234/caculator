import java.awt.*;
import javax.swing.*;

/**
 * {@code LoopListGUI}는 반복문을 사용하여 게시판 리스트를 생성하고 실행하는 클래스.
 *
 * @author seolheun5
 *
 * @creat 2024-10-08
 * @lastModified 2024-10-10
 *
 * @changelog
 * <ul>
 *     <li>메인 패널 생성 및 레이아웃 설정</li>
 *     <li>게시글 UI 구성</li>
 *     <li>{@code posts}, @{code addPost} 메서드 구성\n @{code posts} 메서드에서 게시글 라벨과 내용 순차생성</li>
 * </ul>
 */
public class Study extends JFrame {
    JPanel mainPanel = new JPanel();

    /**
     * @author seolheun5
     */
    Study() {
        setTitle("게시판");
        setLayout(new BorderLayout(10, 10));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        posts();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 500);
        setVisible(true);
    }


    private void addPost(String labelText, String contentText) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel postLabel = new JLabel(labelText);
        JTextArea postTextArea = new JTextArea(contentText);

        postPanel.add(postLabel, BorderLayout.NORTH);
        postPanel.add(postTextArea, BorderLayout.CENTER);

        mainPanel.add(postPanel);
    }

    private void posts() {
        String labelText;
        String contentText;

        for(int i = 1; i <= 10; i++) {
            labelText = "NO." + Integer.toString(i);
            contentText = Integer.toString(i) + "번째 게시글입니다.";

            addPost(labelText, contentText);
        }

        add(mainPanel);
    }

    public static void main(String[] args) {
        new Study();
    }
}

