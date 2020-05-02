package eltech.DM2020.BigNum;

import java.util.*;

/**
* Класс, который позволяет манипулировать с полиномами с рациональными коэффициентами
* @version 0.01
*/
public class BigPolinom
{
	private ArrayList<BigMonom> factors = new ArrayList<BigMonom>();

	private BigPolinom() {}

	/**
	* Конструктор, с помощью которого можно инициализировать полином
	* Если строка пустая или null, то бросит исключение
	* Может принять строку такого вида:
	* 27x_1^3 + 7x2 + 3
	*
	* @param String src - представление полинома в виде строки
	*
	* @version 2
	* @author Семенов Алексей
	*/
	public BigPolinom(int amount, String src)
	{
		int i;
		String[] str;
		if(src == null)
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть не инициализированной\n");
		if(src.equals(""))
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть пустой\n");
		src = src.trim();
		src = src.replace("*", "");
		src = src.replace(")", "");
		src = src.replace("(", "");
		src = src.replace("-", "+-");
		src = src.replace("++", "+");
		src = src.replace("/+", "/");
		src = src.replace("_", "");
		str = src.split("[+]");
		for(i = 0; i < str.length; i++)
			if(!str[i].equals(""))
				factors.add(new BigMonom(amount, str[i].trim()));
		//сортировка
	}

	/**
	* Вывод полинома в виде строки
	* Выводиться должно так:
	* 27x1^3 + 7x2 + 3
	*
    * @return String - представление полинома в виде строки
	*
	* @version 1
	* @author
	*/
	@Override
	public String toString()
	{
		int i;
		String buffS = "";
		for(i = 0; i < factors.size(); i++)
			buffS += factors.get(i).toString()+"+";
		buffS = buffS.replace("+-", "-");
		return buffS.substring(0, buffS.length() - 1);
	}

	/**
	* Проверяет является ли многочлен нулём
	*
    * @return boolean - true, если числитель многочлен имеет степень 0 и коэффициент при нулевой степени равен нулю; иначе false
	*
	* @version 1
	* @author
	*/
	public boolean isZero()
	{
        return true;
	}

    /**
    * Сравнение двух полиномов
    *
    * @param BigPolinom other - второй полином для сравнения с исходным
    * @return int - 0 если степени полиномов равны, -1 если степень исходного полинома меньше other, иначе 1
    *
    * @version 1
    * @author
    */
    public int compareTo(BigPolinom other)
    {
		BigPolinom result = new BigPolinom();
        return 1;
    }

	/**
    * Клонирование объекта
	*
    * @return копию BigPolinom
    *
    * @version 1
    * @author
    */
	@Override
	public BigPolinom clone()
	{
		BigPolinom result = new BigPolinom();
		return result;
	}

	/**
    * Сложение полиномов
	*
	* @param BigPolinom other - второй полином, который прибавляется
	*
    * @return BigPolinom result - результат сложения
    *
    * @version 
    * @author 
    */
	public BigPolinom add(BigPolinom other)
	{
		BigPolinom result = new BigPolinom();
        return result;
	}

	/**
    * Вычитания полиномов
	*
	* @param BigPolinom other - второй полином, который вычитания
	*
    * @return BigPolinom result - результат вычитания
    *
    * @version 1
    * @author Яловега Никита
    */
	public BigPolinom subtract(BigPolinom other)
	{
        BigPolinom result = new BigPolinom();
        return result;
	}

	/**
    * Умножение полиномов
	*
	* @param BigPolinom other - второй полином, на который умножается исходный
	*
    * @return BigPolinom result - результат вычитания
    *
    * @version 1
    * @author Яловега Никита
    */
	public BigPolinom multiply(BigPolinom other)
	{
        BigPolinom result = new BigPolinom();
        return result;
	}
}
