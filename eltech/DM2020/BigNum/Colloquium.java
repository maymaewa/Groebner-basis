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
		/*BigMonom one = new BigMonom(2, "4x1^2x2");
		BigMonom two = new BigMonom(2, "x1");
		System.out.println(two.multiply(two.getMultiplier(one)));*/
		
		Scanner num = new Scanner(System.in);
		System.out.println("\nВведите кол-во незвестных");
		String buffS;
		int amount = num.nextInt();
		int i;
		for(i = 0; i < 2; i++)
		{
			Scanner in = new Scanner(System.in);
			System.out.println("\nВведите полином");
			buffS = in.nextLine();
			value.add( new BigPolinom(amount, buffS.trim()) );
			System.out.println("Введенный полином: " + value.get(i));
			System.out.println("Полином = 0? " + value.get(i).isZero() + "\n\n");
		}
		System.out.println("Сумма: " + value.get(0).add( value.get(1) ));
		System.out.println("Разница: " + value.get(0).subtract( value.get(1) ));
		System.out.println("Произведение: " + value.get(0).multiply( value.get(1) ));
		System.out.println("Частное: " + value.get(0).divide( value.get(1) ));
		System.out.println("Остаток: " + value.get(0).mod( value.get(1) ));
		//System.out.println("НОД: " + value.get(0).gcd( value.get(1) ));
		//buffThis.getHighMonom().getMultiplier(buffOther.getHighMonom());
		//System.out.println("Остаток: " + value.get(0).mod( value.get(1) ));
	}
}