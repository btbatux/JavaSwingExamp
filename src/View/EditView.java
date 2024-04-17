package View;

import Business.UsersController;
import Entity.Users;

import javax.swing.*;
import java.awt.*;

public class EditView extends JFrame {
    private JPanel container;
    private JTextField fld_name;
    private JTextField fld_mail;
    private JPasswordField fld_password;
    private JComboBox<Users.Type> cmb_type;
    private JRadioButton radio_female;
    private JRadioButton radio_male;
    private JCheckBox check_agree;
    private JButton btn_save;
    private JLabel lbl_name;
    private JLabel lbl_mail;
    private JLabel lbl_password;
    private JLabel lbl_type;
    private JLabel lbl_gender;
    private Users users;
    private DefaultComboBoxModel<Users.Type> cmdl_type;
    private UsersController usersController;


    public EditView(Users users) {
        this.container = container;
        this.add(container);
        this.setTitle("Users Edit"); //pencere başlığı
        setSize(400, 600);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;     //Ekranın genişliğinin yarısı
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;   //Ekranın yüklekseliğinin yarısı

        this.setLocation(x, y); // pencerenin konumunu parametre olarak gönderdik
        this.setVisible(true);  // pencerenin görünürlüğü açtık

        this.users = users;
        this.usersController = new UsersController();

        this.cmdl_type = new DefaultComboBoxModel<>(Users.Type.values()); //ComboBox'a cinsiyetleri çektik
        this.cmb_type.setModel(this.cmdl_type); //ComboBox'a cinsiyetleri gösterdik

        //Users nesnesi yok ise yeni ekleme user == 0
        //User nesnesi var ise düzenleme    user !== 0

        if (this.users.getId() != 0) //eğer user verisi varsa
        {
            this.fld_name.setText(this.users.getName());
            this.fld_mail.setText(this.users.getMail());
            this.fld_password.setText(this.users.getPassword());
            this.cmb_type.getModel().setSelectedItem(this.users.getType());
            if (this.users.getGender() == Users.Gender.FEMALE) {
                this.radio_female.setSelected(true);
            } else {
                this.radio_male.setSelected(true);

            }
        }


        check_agree.addActionListener(e -> { //check box seçili ise save butonu aktif et
            if (this.check_agree.isSelected()) {
                this.btn_save.setEnabled(true);
            } else {
                this.btn_save.setEnabled(false);
            }

        });

        btn_save.addActionListener(e -> { //save butonu tıklanmasını dinledik
            if (this.fld_name.getText().isEmpty()||
                this.fld_mail.getText().isEmpty() ||
                this.fld_password.getText().isEmpty()){

                JOptionPane.showMessageDialog(null,"Tüm alanları doldurunuz.",
                        "Eksik Veri",JOptionPane.ERROR_MESSAGE);

            }else{ //Eğer alanlar boş değilse, alanlara girilen verileri eski bilgilerin yerine güncelle
                this.users.setName(this.fld_name.getText());
                this.users.setMail(this.fld_mail.getText());
                this.users.setPassword(this.fld_password.getText());
                this.users.setType((Users.Type) this.cmdl_type.getSelectedItem());

                if(this.radio_male.isSelected())
                {
                    this.users.setGender(Users.Gender.MALE);
                }else{
                    this.users.setGender(Users.Gender.FEMALE);

                }
                if(this.usersController.update(users))
                {
                    JOptionPane.showMessageDialog(
                            null,"Veri güncellendi",
                            "Başarılı",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });

    }
}
