package assignment1;

import java.sql.SQLException;
import java.util.Scanner;

import tool.ConnectionUtil;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("请输入你要输入的类型 a:准考证号 b:身份证号 ");
		//准考证
		if (input.next().charAt(0) == 'a' ) {
			String sql = "SELECT * FROM student WHERE examcard = ?";
			System.out.println("请输入您的准考证号");
			ConnectionUtil.doSql(sql, input.next());
		}
		//身份证号查询
		else {
			String sql = "SELECT * FROM student WHERE idcard = ?";
			System.out.println("请输入您的身份证号");
			ConnectionUtil.doSql(sql, input.next());
		}
	}

}
