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
	private static ArrayList<BigPolinom> value = new ArrayList<BigPolinom>();
	
	public static void start()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("\nВведите кол-во незвестных и полином через пробел");
		String buffS;
		int amount = in.nextInt();
		buffS = in.nextLine();
		BigPolinom a = new BigPolinom(amount, buffS);
		System.out.println(a);
		System.out.println("Полином = 0? " + a.isZero());
		
		/*int i;
		for(i = 0; i < 1; i++)
		{
			Scanner in = new Scanner(System.in);
			System.out.println("\nВведите кол-во незвестных и полином через пробел");
			String buffS;
			int amount = in.nextInt();
			buffS = in.nextLine();
			value.add( new BigPolinom(amount, buffS.trim()) );
			System.out.println("Введенный полином: " + value.get(i));
			System.out.println("Полином = 0? " + value.get(i).isZero() + "\n\n");
		}
		/*System.out.println("Есть ли в a первый моном из b: " + value.get(0).hasMonom(value.get(1).getFactors().get(0)));
		System.out.println("Сравнение a и b: " + value.get(0).compareTo( value.get(1) ));*/
		//System.out.println(value.get(0).sort());
		//System.out.println(value.get(0));
	}
}