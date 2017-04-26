/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.UserDAO;

/**
 *
 * @author kiett
 */
public class ManageAccountModel {

    String regexVI = "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ_\\s]+";
    String regexNum = "[0-9]+.[0-9]+";
    String regexEN = "^[a-zA-Z0-9]+";

    public byte validateUsername(String username) {
        UserDAO ud = new UserDAO();
        byte flag1 = 0, flag2 = 0, flag = 0;

        if (ud.checkUsername(username) == false) {
            return -1;
        }
        if (username.matches(regexEN) == false) {
            flag1 = 1;
        }
        if (username.length() < 7) {
            flag2 = 2;
        }
        flag = (byte) (flag1 + flag2);

        return flag;
    }

    public boolean validateFullname(String fullname) {
        if (fullname.matches(regexVI) == true) {
            return true;
        }
        return false;
    }

}
