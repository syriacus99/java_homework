package assignment2;

import java.util.Scanner;

import tool.ConnectionUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("���������ΨһID���û��������룬����;����");
		String userInput = input.next();
		String sql = "SELECT"
		ConnectionUtil.doSql(sql, objects);
	}

}
