package Game;

import java.sql.*;
import java.util.*;

// �α��ν� ���̵� ��й�ȣ�� �´��� Ȯ���ϴ� Ŭ����
public class gameDB_login {
	 
    String id = null;
    String pw = null;
 
    Statement stmt = null;
    ResultSet rs = null;
    String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // ����Ŭ ��Ʈ��ȣ1521/@���Ŀ��� IP�ּ�
    String sql = null;
    Properties info = null;
    Connection cnn = null;
 
    int checkIDPW(String id, String pw) {
        this.id = id;
        this.pw = pw;
        int result = 1;
 
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // �˾Ƽ� ����..conn��
            info = new Properties();
            info.setProperty("user", "hr_hongsam");
            info.setProperty("password", "asd950808");
            cnn = DriverManager.getConnection(url, info); // ������ ������ �������ִ� ����̹��Ŵ����� ������
            stmt = cnn.createStatement();
 
            sql = "select * from SHOOTGAME where ID='" + id + "'";
            rs = stmt.executeQuery(sql); // �о���°Ŷ� �ٸ��� ����    //����Ÿ���� ResultSet
 
            if (rs.next() == false || (id.isEmpty()) == true) { // id�� ����x
                result = 1;
            } else {
                sql = "select * from (select * from SHOOTGAME where ID='" + id + "')";
                rs = stmt.executeQuery(sql);
                while (rs.next() == true) {         // ��������
                    if (rs.getString("PW").equals(pw)) {
                    	// pw�� ������ ��
                        result = 0;         // ������ �α��� ����
                    } else {                // ���̵�°��� pw�� �ٸ����
                        result = 1;
                    }
                }
            }
        } catch (Exception ee) {
            System.out.println("��������");
            ee.printStackTrace();
        }
        return result;
    }
}




