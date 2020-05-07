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
		System.out.println("\nВведите кол-во незвестных");
		String buffS;
		int amount;
		
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