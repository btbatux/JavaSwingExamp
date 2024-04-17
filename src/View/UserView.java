package View;

import Business.UsersController;
import Entity.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class UserView extends JFrame {
    private JPanel container;
    private JTabbedPane tab_menu;
    private JPanel pnl_user;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private JButton btn_user_new;
    private UsersController usersController;
    private DefaultTableModel model_user;
    private JPopupMenu jPopupMenu;

    public UserView() throws HeadlessException {
        this.add(container);
        this.setTitle("Kullanıcı Yönetimi");
        this.setSize(600, 500);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;        //EKRANDAKİ KONUMU
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;    //EKRANDAKİ KONUMU

        this.setLocation(x, y);
        this.setVisible(true);

        this.usersController = new UsersController();
        this.model_user = new DefaultTableModel();
        this.jPopupMenu = new JPopupMenu(); //sağ click işlemleri için

        //table model
        //kolon başlıkları oluştur.
        Object[] columnUser = {"ID", "Ad", "Tip", "Cinsiyet", "Mail", "Şifre"};
        this.model_user.setColumnIdentifiers(columnUser);

        ArrayList<Users> usersArrayList = this.usersController.findAll();

        for (Users user : usersArrayList) {
            //Tablo Satırları
            Object[] row = {user.getId(), user.getName(), user.getType(), user.getGender(), user.getMail(), user.getPassword()};
            this.model_user.addRow(row);
        }
        //modeli tabloya işle
        this.tbl_user.setModel(this.model_user);
        this.tbl_user.setEnabled(false);
        this.tbl_user.getTableHeader().setReorderingAllowed(false);

        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selectedRow,selectedRow);
            }
        });

        this.jPopupMenu.add("Güncelle").addActionListener(e ->
        {
            int selectedId = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString());
            Users selectedUser = this.usersController.getById(selectedId);

            System.out.println(selectedUser.toString());
            EditView editView = new EditView(selectedUser);


        });

        this.jPopupMenu.add("Sil").addActionListener(e ->{
            int selectedId =Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString());
            System.out.println(selectedId);
        });

        this.tbl_user.setComponentPopupMenu(jPopupMenu);
    }
}
