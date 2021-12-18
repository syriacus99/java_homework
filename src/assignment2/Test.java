package assignment2;

import java.util.Scanner;

import tool.ConnectionUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("请输入你的唯一ID，用户名及密码，并以;隔开");
		String userInput = input.next();
		String sql = "SELECT"
		ConnectionUtil.doSql(sql, objects);
	}

}
