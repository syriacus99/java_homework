package assignment1;

import java.sql.SQLException;
import java.util.Scanner;

import tool.ConnectionUtil;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("��������Ҫ��������� a:׼��֤�� b:���֤�� ");
		//׼��֤
		if (input.next().charAt(0) == 'a' ) {
			String sql = "SELECT * FROM student WHERE examcard = ?";
			System.out.println("����������׼��֤��");
			ConnectionUtil.doSql(sql, input.next());
		}
		//���֤�Ų�ѯ
		else {
			String sql = "SELECT * FROM student WHERE idcard = ?";
			System.out.println("�������������֤��");
			ConnectionUtil.doSql(sql, input.next());
		}
	}

}
