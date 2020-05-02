package eltech.DM2020.BigNum;

import java.util.*;
import java.math.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

/**
* Класс, который содержит интерфейс
* @version 0.01
* @author Семенов Алексей, Сычев Александр
* Главный SQA - Семенов Алексей
*/
public class Colloquium
{
	private static HashMap<String, Object> nums = new HashMap<String, Object>();
	private static Scanner in = new Scanner(System.in);
	private static final String SintaxisProblem = "Неверный синтаксис комманды (попробуйте ввести help)";
	private static final String NotBigNumInDictProblem = " нет в списке чисел (попробуйте использовать input as)";
	private static final String DifTypeProblem = " должны быть одного типа (попробуйте использовать toBig[N, Z, Q, Polinom])";
	
	public static void start()
	{
		String buffS;
		int amount = in.nextInt();
		buffS = in.nextLine();
		BigPolinom a = new BigPolinom(amount, buffS.trim());
		System.out.println(a);
	}
}