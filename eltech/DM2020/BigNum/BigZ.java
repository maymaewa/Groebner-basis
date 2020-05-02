package eltech.DM2020.BigNum;

import java.util.*;
	/**
	* Класс, который позволяет манипулировать с большими целыми числами
	* @version 0.02
	* @author Сычев Александр, Цветков Иван, Хайруллов Айрат, Муродов Ахмад
	*/
public class BigZ
{
	/*Целое число - это натуральное + {0}, но ещё есть знак. Само значение представлено в виде Number, а знак isPositive*/
	private BigN Number;
	private boolean isPositive;
	
	/**
	* Конструктор, с помощью которого можно ввести большое целое число
	* Если строка src пустая или null, то бросит исключение
	*
	* @param String src - представление большого натурального числа в виде строки
	*
	* @version 1
	* @author Сычев Александр
	*/
	public BigZ(String src) throws IllegalArgumentException
	{
		if(src == null)
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть не инициализированной\n");
		if(src.equals(""))
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть пустой\n");
		src = src.trim();
		if (src.charAt(0) == '-')
			isPositive = false;
		else
			isPositive = true;
		Number = new BigN( isPositive ? src : src.substring(1, src.length()) );
		if(!isPositive && Number.compareTo(new BigN("0")) == 0)
			isPositive = true;
	}
	
	/**
	* Конструктор, с помощью которого можно инициализировать большое целое число
	* Если большое натурально число one == null, то бросит исключение
	*
	* @param BigN one - большое натурально число
	*
	* @version 1
	* @author Сычев Александр
	*/
	public BigZ(BigN one) throws IllegalArgumentException
	{
		if(one == null)
			throw new IllegalArgumentException("Неверный аргумент: большое натуральное число должно быть инициализированно\n");
		Number = one.clone();
		isPositive = true;
	}
	
	private BigZ(){}
	
	/**
	* Вывод большого целого числа в виде строки
	*
    * @return Представление числа в виде строки
	*
	* @version 1
	* @author
	*/
	@Override
	public String toString()
	{
		int i;
		String S = "";
		if(!isPositive)
			S += "-";
		S += Number.toString();
		return S;
	}
	
	/**
	* Проверка на положительность
	*
    * @return boolean - если положительное - вернёт true, иначе - false.
	*
	* @version 1
	* @author Буторина Полина
	*/
	public boolean checkPositive()
	{
		return isPositive;
	}
	
    /**
    * Сравнение двух больших целых чисел.
    *
    * @param BigZ other - второе число для сравнения с исходным
    * @return int - 0 если равны, -1 если меньше other, 1 если больше other
    *
    * @version 1
    * @author
    */
    public int compareTo(BigZ other)
    {
		BigZ buff = this.subtract(other);
		if(buff.isZero())
			return 0;
		else if (buff.checkPositive())
			return 1;
		else 
			return -1;
    }
	
	/**
	* Проверка на нуль
	*
    * @return boolean - если нуль - вернёт true, иначе - false.
	*
	* @version 1
	* @author
	*/
	public boolean isZero()
	{
		return this.Number.isZero();
	}
	
	/**
	* Абсолютное значение целого числа
	*
    * @return BigZ - абсолютное значение целого числа
	*
	* @version 1
	* @author Семен Дубенков
	*/
	public BigZ abs()
	{
		BigZ result = this.clone();
		result.isPositive = true;
		return result;
	}
	
	/**
	* Сложение 2-x больших целых чисел. Вернёт при сложении НОВОЕ большое целое число
	*
    * @param BigN src - число, которое прибавляется к исходному
    * @return BigN result - новое число, получающееся в результате сложения
	*
	* @version 1
	* @author Цветков Иван, Хайруллов Айрат, Муродов Ахмад
	*/
	public BigZ add(BigZ src)
	{
		BigZ result = new BigZ();

		if (this.isPositive == src.isPositive)
		{
			result.Number = this.Number.add(src.Number);
			result.isPositive = this.isPositive;
			return result;
		}
		else
		{
			int compared = this.Number.compareTo(src.Number);
			if (compared > 0)
			{
				result.Number = this.Number.subtract(src.Number);
				result.isPositive = this.isPositive; 
			}
			else if (compared < 0)
			{
				result.Number = src.Number.subtract(this.Number);
				result.isPositive = src.isPositive; 
			}
			else
			{
				result.Number = new BigN("0");
				result.isPositive = true;
			}
		}
		return result;
	}
	
	/**
    * Клонирование объекта
	*
    * @return копию BigZ
    *
    * @version 1
    * @author
    */
	@Override
	public BigZ clone() 
	{
		BigZ result = new BigZ();
		result.Number = this.Number.clone();
		result.isPositive = this.isPositive;
		return result;
	}
	
	/**
	* Умножение на -1
	*
    * @return BigZ result - исходное число, умноженное на -1
	*
	* @version 1
	* @author Михаил Сорокин, Семенов Алексей
	*/
	public BigZ multiplyByMinusOne()
	{
		BigZ result = this.clone();
		result.isPositive = !this.isPositive;
		return result;
	}
	

	
	/**
	* Деление нацело целых чисел
	*
    * @param BigZ other - число, на которое делится исходное
    * @return BigZ result - результат деления
	*
	* @version 1
	* @author Петрова Вероника, Семенов Алексей, Сычев Александр
	*/
	public BigZ divide(BigZ other)
	{
		BigZ result = new BigZ( this.Number.divide( other.Number ) );
		result.isPositive = !(this.isPositive ^ other.isPositive);
		return result;
	}
	
	/**
	* Остаток от деления целых чисел
	*
	* Данное вычисление остатка от деления основано на том, 
	* что остаток от деления должен быть положительным, на примерах:
	* 121 = 7*17 + 2
	* 121 = -7*(-17) + 2
	* -121 = 7*(-18) + 5
	* -121 = -7*18 + 5
	*
    * @param BigZ other - число, на которое делится исходное
    * @return BigZ result - остаток от деления
	*
	* @version 1
	* @author Семенов Алексей, Дементьев Дмитрий
	*/
	public BigZ mod(BigZ other)
	{
		BigZ result;
		if(this.checkPositive())
			result = new BigZ( this.Number.mod(other.Number) );
		else
			result = new BigZ( other.Number.subtract(this.Number.mod(other.Number)) );
		return result;
	}
	
    /**
    * НОД
    *
    * @param BigZ other - второе число для нахождения нод
    * @return BigZ result - нод(this; other)
    *
    * @version 1
    * @author Деменьтев Дмитрий
    */
    public BigZ gcd(BigZ other)
    {
		return new BigZ( this.Number.gcd(other.Number) );
    }
	
	/**
	* Умножение целых чисел
	*
    * @param BigZ other - число, на которое делится исходное
    * @return BigZ result - результат деления
	*
	* @version 1
	* @author Пурин Артём, Семенов Алексей
	*/
	public BigZ multiply(BigZ other)
	{
		BigZ result = new BigZ( this.Number.multiply( other.Number ) );
		result.isPositive = !(this.isPositive ^ other.isPositive);
		return result;
	}

	/**
	* Вычитание целых чисел
	*
    * @param BigZ other - число, на которое делится исходное
    * @return BigZ result - результат деления
	*
	* @version 1
	* @author Степовик Виктор, Логинова Алина
	*/
	public BigZ subtract(BigZ other)
	{
		return this.add( other.multiplyByMinusOne() );
	}

	/**
    * Возведение в степень с помощью алгоритма бинарного возведения в степень
    *
    * @param BigN other - число, в степень которого возводится исходное
	*
    * @return BigZ result - результат возведения исходного в степень other
    *
    * @version 1
    * @author
    */
	public BigZ pow(BigN other) 
	{
        BigN TWO = new BigN("2");
        BigZ result = new BigZ("1");
        BigZ base = this.clone();
        BigN b = other.clone();
        while( !b.isZero() )
        {
            if( b.isEven() == false)
                result = result.multiply(base);
            base = base.multiply(base);
            b = b.divide(TWO);
        }
        return result;
    }

	/**
    * Конвертация в BigN
	* Если BigZ отрицательное, то бросает исключение
    *
    * @return BigZ result - целое число
    *
    * @version 1
    * @author Николай Лускарёв
    */
    public BigN toBigN() throws ArithmeticException
    {
		if(checkPositive() == false)
			throw new ArithmeticException("Нельзя перевести отрицательное число в натуральное + {0}\n");
		return this.Number.clone();
    }
	
	/**
    * Конвертация в BigQ
    *
    * @return BigQ result - рациональное число
    *
    * @version 1
    * @author
    */
    public BigQ toBigQ()
    {
		return new BigQ(this.clone(), new BigZ("1"));
    }

	/**
	* Сравнение BigZ, согласно спецификации Java
	*
    * @return эквивалентность
	*
	* @version 1
	* @author
	*/
	@Override
    public boolean equals(Object otherObj) 
	{
		if (otherObj == this) return true; 
		if (otherObj == null) return false;
		if( this.getClass() != otherObj.getClass() ) return false;
		BigZ other = (BigZ)otherObj;
		return this.Number.equals(other.Number) && this.checkPositive() == other.checkPositive();
    } 
	
	public BigN getNumber()
	{
		return Number;
	}
	
	public void setNumber(BigN other)
	{
		this.Number = other;
	}
}






















