package UI;

import Dictionary.Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * UI
 * Created by lddong
 * Date 11/19/2022 - 11:59 AM
 * Description: ...
 */
public class UiFrame implements ActionListener {
    final private Management system = new Management();
    final private JFrame frame = new JFrame("DICTIONARY");
    JList resultSet = null;
    JTextField searchInput = new JTextField(30);
    JButton searchBtn = new JButton("SEARCH");
    JButton backBtn = new JButton("MENU");

    // 4
    JTextField keyInput = new JTextField(30);
    JTextField passInput = new JTextField(30);
    JButton addBtn = new JButton("ADD");

    //5
    JButton submitBtn = new JButton("SUBMIT");

    //9
    int type = 0;
    JButton aBtn = null;
    JButton bBtn = null;
    JButton cBtn = null;
    JButton dBtn = null;
    JLabel question = null;

    int correctAnsIdx = 0;
    String[] ansList = null;

    public UiFrame() throws IOException {
        searchBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        backBtn.addActionListener(this);
        addBtn.setActionCommand("add");
        addBtn.addActionListener(this);
        submitBtn.addActionListener(this);
    }
    private JPanel menuUI() {
        frame.setSize(400,450);

        final JButton btn01 = new JButton("Tìm kiếm theo slang word");
        final JButton btn02 = new JButton("Tìm kiếm theo defination");
        final JButton btn03 = new JButton("Lịch sử tìm kiếm");
        final JButton btn04 = new JButton("Thêm slang word");
        final JButton btn05 = new JButton("Chỉnh sửa slang word");
        final JButton btn06 = new JButton("Xóa slang word");
        final JButton btn07 = new JButton("Reset danh sách slang word gốc");
        final JButton btn08 = new JButton("Random 1 slang word");
        final JButton btn09 = new JButton("Đố vui tìm nghĩa slang word");
        final JButton btn10 = new JButton("Đố vui tìm slang word");
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("DICTIONARY");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn01.setPreferredSize(new Dimension(300,30));
        btn02.setPreferredSize(new Dimension(300,30));
        btn03.setPreferredSize(new Dimension(300,30));
        btn04.setPreferredSize(new Dimension(300,30));
        btn05.setPreferredSize(new Dimension(300,30));
        btn06.setPreferredSize(new Dimension(300,30));
        btn07.setPreferredSize(new Dimension(300,30));
        btn08.setPreferredSize(new Dimension(300,30));
        btn09.setPreferredSize(new Dimension(300,30));
        btn10.setPreferredSize(new Dimension(300,30));

        menu.add(label);
        menu.add(menuBtn(btn01));
        menu.add(menuBtn(btn02));
        menu.add(menuBtn(btn03));
        menu.add(menuBtn(btn04));
        menu.add(menuBtn(btn05));
        menu.add(menuBtn(btn06));
        menu.add(menuBtn(btn07));
        menu.add(menuBtn(btn08));
        menu.add(menuBtn(btn09));
        menu.add(menuBtn(btn10));

        btn01.setActionCommand("1");
        btn01.addActionListener(this);
        btn02.setActionCommand("2");
        btn02.addActionListener(this);
        btn03.setActionCommand("3");
        btn03.addActionListener(this);
        btn04.setActionCommand("4");
        btn04.addActionListener(this);
        btn05.setActionCommand("5");
        btn05.addActionListener(this);
        btn06.setActionCommand("6");
        btn06.addActionListener(this);
        btn07.setActionCommand("7");
        btn07.addActionListener(this);
        btn08.setActionCommand("8");
        btn08.addActionListener(this);
        btn09.setActionCommand("9");
        btn09.addActionListener(this);
        btn10.setActionCommand("10");
        btn10.addActionListener(this);

        return menu;
    }
    private JPanel menuBtn(JButton btn) {
        JPanel _btnPanel = new JPanel();
        _btnPanel.setLayout(new FlowLayout());
        _btnPanel.add(btn);

        return _btnPanel;
    }
    private JPanel findSlangWordsUI() {
        JPanel search = new JPanel();
        search.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        addBackBtn(topPanel, "SEARCH SLANG WORD",50);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        searchBtn.setPreferredSize(new Dimension(95,25));
        searchPanel.add(searchInput);
        searchPanel.add(searchBtn);
        topPanel.add(searchPanel);


        search.add(topPanel, BorderLayout.PAGE_START);
        JScrollPane resultSearch = new JScrollPane(resultSet);
        search.add(resultSearch, BorderLayout.CENTER);


        return search;
    }
    private void addBackBtn(JPanel component, String title, int width) {
        JPanel navigation = new JPanel();
        navigation.setLayout(new BorderLayout());

        JPanel back = new JPanel();
        back.setLayout(new BoxLayout(back, BoxLayout.LINE_AXIS));
        back.add(Box.createRigidArea(new Dimension(6,0)));
        back.add(backBtn);
        back.add(Box.createRigidArea(new Dimension(width,0)));
        back.add(new JLabel(title));
        navigation.add(back, BorderLayout.WEST);

        component.add(navigation);
    }
    private JPanel historyUI() {
        JPanel history = new JPanel();
        history.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.PAGE_AXIS));

        addBackBtn(top, "HISTORY",100);

        String[] list = system.showHistoryOfSearch();

        JList historyList = new JList(system.showHistoryOfSearch());
        JScrollPane _history = new JScrollPane(historyList);

        history.add(top, BorderLayout.PAGE_START);
        history.add(_history, BorderLayout.CENTER);

        if (list.length == 0) {
            displayInfo("Bạn chưa bao giờ tìm kiếm.");
        }


        return history;
    }
    private JPanel addAndEditSlangWordUI(String heading, JButton btn) {
        frame.setSize(400,180);
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.PAGE_AXIS));
        addBackBtn(top, heading,65);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        JPanel key = new JPanel();
        key.setLayout(new FlowLayout());
        JPanel pass = new JPanel();
        pass.setLayout(new FlowLayout());

        JLabel keyLabel = new JLabel("Keyword");
        JLabel passLabel = new JLabel("Meaning");
        keyLabel.setPreferredSize(new Dimension(80, 30));
        passLabel.setPreferredSize(new Dimension(80, 30));
        key.add(keyLabel);
        key.add(keyInput);
        pass.add(passLabel);
        pass.add(passInput);
        center.add(Box.createRigidArea(new Dimension(0,20)));
        center.add(key);
        center.add(pass);

        JPanel bot = new JPanel();
        bot.setLayout(new BoxLayout(bot, BoxLayout.PAGE_AXIS));
        btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bot.add(btn);
        bot.add(Box.createRigidArea(new Dimension(4,0)));


        pane.add(top, BorderLayout.PAGE_START);
        pane.add(center, BorderLayout.CENTER);
        pane.add(bot, BorderLayout.PAGE_END);

        return pane;
    }
    private JPanel deleteSlangWordUI(JButton btn) {
        frame.setSize(400,150);
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.PAGE_AXIS));
        addBackBtn(top, "DELETE SLANG WORD",60);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        JPanel key = new JPanel();
        key.setLayout(new FlowLayout());

        JLabel keyLabel = new JLabel("Keyword");
        keyLabel.setPreferredSize(new Dimension(80, 30));
        key.add(keyLabel);
        key.add(keyInput);
        center.add(Box.createRigidArea(new Dimension(0,20)));
        center.add(key);

        JPanel bot = new JPanel();
        bot.setLayout(new BoxLayout(bot, BoxLayout.PAGE_AXIS));
        btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bot.add(btn);
        bot.add(Box.createRigidArea(new Dimension(4,0)));


        pane.add(top, BorderLayout.PAGE_START);
        pane.add(center, BorderLayout.CENTER);
        pane.add(bot, BorderLayout.PAGE_END);

        return pane;
    }
    private JPanel questionAndAnswer(String ques, String[] ans) {
        frame.setSize(400, 220);
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.PAGE_AXIS));
        addBackBtn(top, "FUNNY QUESTION", 70);
        top.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));

        question = new JLabel(ques);
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(question);

        JPanel firstRow = new JPanel();
        firstRow.setLayout(new FlowLayout());
        JPanel secondRow = new JPanel();
        secondRow.setLayout(new FlowLayout());
        aBtn = new JButton(ans[0]);
        bBtn = new JButton(ans[1]);
        cBtn = new JButton(ans[2]);
        dBtn = new JButton(ans[3]);
        aBtn.setPreferredSize(new Dimension(150, 50));
        bBtn.setPreferredSize(new Dimension(150, 50));
        cBtn.setPreferredSize(new Dimension(150, 50));
        dBtn.setPreferredSize(new Dimension(150, 50));

        firstRow.add(aBtn);
        firstRow.add(bBtn);
        secondRow.add(cBtn);
        secondRow.add(dBtn);

        center.add(Box.createRigidArea(new Dimension(0,5)));
        center.add(firstRow);
        center.add(secondRow);

        pane.add(top,BorderLayout.PAGE_START);
        pane.add(center,BorderLayout.CENTER);

        aBtn.setActionCommand("a");
        bBtn.setActionCommand("b");
        cBtn.setActionCommand("c");
        dBtn.setActionCommand("d");
        aBtn.addActionListener(this);
        bBtn.addActionListener(this);
        cBtn.addActionListener(this);
        dBtn.addActionListener(this);

        return pane;
    }
    private void drawBorder(JTextField panel, Color c) {
        panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(c),
                panel.getBorder()));
    }
    public void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        //Create and set up the content pane.
        frame.setContentPane(menuUI());

        //Display the window.
        frame.setVisible(true);
    }
    private boolean validate(JTextField field){
        StringBuilder errorText = new StringBuilder();

        if (field.getText().length() == 0){
            errorText.append("This field is mandatory\n");
            field.setText("Vui lòng nhập vào ô này");
            drawBorder(field, Color.red);
        }
        else {
            field.setBorder(null);
        }


        // Show the errorText in a message box, or in a label, or ...

        return errorText.length() == 0;
    }
    private void displayInfo(String info) {
        JOptionPane op = new JOptionPane();
        op.showMessageDialog(frame,info);
    }
    private int showOption(String message, String[] options) {
        JOptionPane op = new JOptionPane();
        return op.showOptionDialog(frame, message, "Message", 2, 2, null, options, null);
    }
    private String[] generateAnswerList1(String[] ques) {
        Random r = new Random();
        correctAnsIdx = r.nextInt(0,3);
        ansList = new String[4];
        for (int i = 0; i <= 3; ++i) {
            if (i == correctAnsIdx) ansList[i] = ques[1];
            else ansList[i] = system.randomMeaning();
        }

        return ansList;
    }
    private String[] generateAnswerList2(String[] ques) {
        Random r = new Random();
        correctAnsIdx = r.nextInt(0,3);
        ansList = new String[4];
        for (int i = 0; i <= 3; ++i) {
            if (i == correctAnsIdx) ansList[i] = ques[0];
            else ansList[i] = system.random();
        }

        return ansList;
    }
    private void replayOption(int decision) {
        if (type == 1) {
            if (decision == 0) {
                String[] ans = system.randomQuestion();
                ansList = generateAnswerList1(ans);

                frame.setContentPane(questionAndAnswer(ans[0], ansList));
                frame.setVisible(true);
            } else if (decision == 1) {
                frame.setContentPane(menuUI());
                frame.setVisible(true);
            }
        }
        else {
            if (decision == 0) {
                String[] ans = system.randomQuestion();
                ansList = generateAnswerList2(ans);

                frame.setContentPane(questionAndAnswer(ans[1], ansList));
                frame.setVisible(true);
            } else if (decision == 1) {
                frame.setContentPane(menuUI());
                frame.setVisible(true);
            }
        }
    }
    private void yesConfirm(String[] ansList, int correctAnsIdx, JButton btn) {
        if (btn.getText().equals(ansList[correctAnsIdx])) {
            String[] options = {"Play again", "Back to menu"};
            int decision = showOption("Chức mừng đã bạn đã chính xác.", options);

           replayOption(decision);
        }
        else {
            String[] options = {"Play again", "Back to menu", "Choose again"};
            int decision = showOption("Chúc bạn may mắn lần sau.", options);

            replayOption(decision);
        }
    }
    private void handleAnswer(JButton btn) {
        String fullAns = btn.getText();
        int option = JOptionPane.showOptionDialog(frame, fullAns, "Confirm answer", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        if (option == 0) {
            yesConfirm(ansList, correctAnsIdx, btn);
        }
    }
    public void searchFunction(String[] l) {
        if (l.length == 0) {
            displayInfo("Không tìm thấy!!!");
        }
        else {
            resultSet = new JList(l);
            frame.setContentPane(findSlangWordsUI());
            frame.setVisible(true);
        }
    }
    public void actionPerformed(ActionEvent ae) {
        String _in = "";
        String keyword, meaning;
        switch (ae.getActionCommand()) {
            case "1":
                frame.setContentPane(findSlangWordsUI());
                searchBtn.setActionCommand("s1");
                frame.setVisible(true);
                break;
            case "2":
                frame.setContentPane(findSlangWordsUI());
                searchBtn.setActionCommand("s2");
                frame.setVisible(true);
                break;
            case "3":
                frame.setContentPane(historyUI());
                frame.setVisible(true);
                break;
            case "4":
                frame.setContentPane(addAndEditSlangWordUI("ADD SLANG WORD",addBtn));
                frame.setVisible(true);
                break;
            case "5":
                frame.setContentPane(addAndEditSlangWordUI("EDIT SLANG WORD",submitBtn));
                frame.setVisible(true);
                submitBtn.setActionCommand("edit");
                break;
            case "6":
                frame.setContentPane(deleteSlangWordUI(submitBtn));
                frame.setVisible(true);
                submitBtn.setActionCommand("delete");
                break;
            case "7":
                try {
                    system.reset();
                    displayInfo("Reset thành công.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "8":
                JOptionPane.showMessageDialog(frame, system.randomSlangWord(), "On this day slang word", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "9":
                type = 1;
                String[] ans = system.randomQuestion();
                ansList = generateAnswerList1(ans);

                frame.setContentPane(questionAndAnswer(ans[0],ansList));
                frame.setVisible(true);
                break;
            case "10":
                type = 2;
                String[] ques = system.randomQuestion();
                ansList = generateAnswerList2(ques);

                frame.setContentPane(questionAndAnswer(ques[1],ansList));
                frame.setVisible(true);
                break;
            case "a":
                handleAnswer(aBtn);
                break;
            case "b":
                handleAnswer(bBtn);
                break;
            case "c":
                handleAnswer(cBtn);
                break;
            case "d":
                handleAnswer(dBtn);
                break;
            case "s1":
                _in = searchInput.getText();
                if (validate(searchInput)) {
                    searchFunction(system.findSlangWord(_in));
                }
                break;
            case "s2":
                _in = searchInput.getText();
                if (validate(searchInput)) {
                    searchFunction(system.findSlangWordByDef(_in));
                }
                break;
            case "back":
                resultSet = null;
                searchInput.setText("");
                keyInput.setText("");
                passInput.setText("");

                frame.setContentPane(menuUI());
                frame.setSize(400,450);
                frame.setVisible(true);
                break;
            case "add":
                keyword = keyInput.getText();
                meaning = passInput.getText();
                if (validate(keyInput) && validate(passInput)) {
                    int signal = system.addSlangWord(keyword, meaning);

                    if (signal == 0) {
                        displayInfo("Slang word này đã tồn tại.");
                    }
                    else if (signal == 1) {
                        displayInfo("Thêm thành công.");
                    }
                    else {
                        String[] options = {"Overwrite", "Add meaning", "Cancel"};
                        int decision = showOption("Keyword này đã tồn tại.", options);
                        if (decision == 0) system.overwriteSlangWord(keyword,meaning);
                        else if (decision == 1) system.duplicateSlangWord(keyword,meaning);
                    }
                }
                break;
            case "edit":
                keyword = keyInput.getText();
                meaning = passInput.getText();

                if (validate(keyInput) && validate(passInput)) {
                    if (system.overwriteSlangWord(keyword, meaning) == null)
                        displayInfo("Slang word này không tồn tại.");
                    else displayInfo("Chỉnh sửa thành công.");
                }

                break;
            case "delete":
                keyword = keyInput.getText();

                if (validate(keyInput)) {
                    if (system.deleteSlangWord(keyword) == null)
                        displayInfo("Slang word này không tồn tại.");
                    else displayInfo("Xóa thành công.");
                }

                break;
        }
    }
}
