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
	private static Basis base = new Basis();
	
	public static void start()
	{
		/*BigPolinom test = new BigPolinom(1, "x1^6-4x1^5+2x1^4+5x1^3+2x1^2-4x1-8");
		BigPolinom test2 = new BigPolinom(1, "x1^5-x1^4-x1^3+x1^2-4x1-4");
		System.out.println(test.gcd(test2));
		System.out.println(test.lcm(test2));
		System.out.println(test.multiply(test2));*/
		System.out.println("\nВведите кол-во незвестных");
		String buffS;
		int amount,i;
		
		do
		{
			Scanner num = new Scanner(System.in);
			amount = num.nextInt();
			if(amount > 0)
				base.setMaxPower(amount);
		} while(amount < 1);
		
		do
		{
			Scanner in = new Scanner(System.in);
			System.out.print("\nВведите полином: ");
			buffS = in.nextLine();
			if(!buffS.equals(""))
				base.addBasis(buffS);
		} while(!buffS.equals(""));
		
		base.doActions();
	}
}